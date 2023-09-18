/**
 * 
 */
package org.relmng.jenkins.ingestser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.relmng.jenkins.core.RelMngJenkinsServerFactory;
import org.relmng.jenkins.record.BuildRecord;
import org.relmng.jenkins.record.JenkinsServerDetailsRecord;
import org.relmng.jenkins.record.JobRecord;
import org.relmng.jenkins.record.RelMngAllBuilds;
import org.relmng.jenkins.service.JenkinsJobBuildDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.JobWithDetails;

/**
 * @author nikhil
 *
 */
@Component
public class RelMngJenkinsJobBuildDetailsIngester {
	Logger logger = LoggerFactory.getLogger(RelMngJenkinsJobBuildDetailsIngester.class);

	private final JenkinsJobBuildDetailsService jobBuildDetailsService;

	/**
	 * @param jobBuildParamsIngester
	 */
	public RelMngJenkinsJobBuildDetailsIngester(JenkinsJobBuildDetailsService jobBuildDetailsService) {
		this.jobBuildDetailsService = jobBuildDetailsService;
	}

	/**
	 * @param serverRecord
	 * @param job
	 * @param jobWithDetails
	 */
	public void updateBuildDetails(JenkinsServerDetailsRecord serverRecord, JobRecord job,
			JobWithDetails jobWithDetails) {
		try {
			// get the new build details
			var lastBuild = jobWithDetails.getLastBuild();
			if (lastBuild.getNumber() != -1) {
				// get the last job details from db
				var lastBuildDetailsOnServer = jobBuildDetailsService.getLastBuildDetails(job.pkJobId());
				if (lastBuildDetailsOnServer.isPresent()) {
					logger.info(jobWithDetails.getFullName() + " On Server " + lastBuildDetailsOnServer.get().number()
							+ " From Jenkins " + lastBuild.getNumber());
					if (lastBuildDetailsOnServer.get().number() == lastBuild.getNumber()) {
						// skip nothing to do
						return;
					} else {
						// save the new details
						var buildRecords = getBuildDetails(serverRecord, job, jobWithDetails,
								lastBuildDetailsOnServer.get().number());
						jobBuildDetailsService.saveBuildDetails(buildRecords);
					}
				} else {
					// new job details need to save all
					var buildRecords = getBuildDetails(serverRecord, job, jobWithDetails, 0);
					jobBuildDetailsService.saveBuildDetails(buildRecords);
				}
			} else {
				logger.info("There is no build details for " + jobWithDetails.getFullName());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param serverRecord
	 * @param job
	 * @param jobWithDetails
	 * @param lastBuildNumber
	 * @return
	 * @throws IOException
	 */
	private List<BuildRecord> getBuildDetails(JenkinsServerDetailsRecord serverRecord, JobRecord job,
			JobWithDetails jobWithDetails, int lastBuildNumber) throws IOException {
		logger.info("Form Range " + lastBuildNumber + " URL " + job.fullName());
		try (var jenkinsServer = RelMngJenkinsServerFactory.of(serverRecord.bassUrl(), serverRecord.userName(),
				serverRecord.token())) {
			var allLatestBuilds = jenkinsServer.getAllBuilds(job, new TypeReference<RelMngAllBuilds>() {
			});
//			logger.info("allLatestBuilds is " + allLatestBuilds.allBuilds().size());

			var buildRecords = new ArrayList<BuildRecord>(allLatestBuilds.allBuilds().size());
			for (Build build : allLatestBuilds.allBuilds()) {
				if (build.getNumber() > lastBuildNumber) {
					build.setClient(jobWithDetails.getClient());
					buildRecords.add(new BuildRecord(build.getNumber(), build.getQueueId(), build.getUrl(),
							job.pkJobId(), build.details()));
				}
			}
			return buildRecords;
		}

	}

}
