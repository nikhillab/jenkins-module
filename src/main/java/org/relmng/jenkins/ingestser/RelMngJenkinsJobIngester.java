/**
 * 
 */
package org.relmng.jenkins.ingestser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.relmng.jenkins.core.RelMngJenkinsServerFactory;
import org.relmng.jenkins.core.RelMngJenkinsService;
import org.relmng.jenkins.record.JenkinsServerDetailsRecord;
import org.relmng.jenkins.record.JobRecord;
import org.relmng.jenkins.service.JenkinsJobService;
import org.relmng.jenkins.service.JenkinsServerDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.offbytwo.jenkins.model.JobWithDetails;

/**
 * @author nikhil
 *
 */
@Component
public class RelMngJenkinsJobIngester {
	private Logger logger = LoggerFactory.getLogger(RelMngJenkinsJobIngester.class);
	private final JenkinsJobService jenkinsJobService;
	private final JenkinsServerDetailsService jenkinsServerDetailsService;
	private final RelMngJenkinsService relMngJenkinsService;
	private final RelMngJenkinsJobBuildDetailsIngester jobBuildDetailsIngester;

	/**
	 * @param jenkinsJobService
	 * @param jenkinsServerDetailsService
	 * @param relMngJenkinsService
	 * @param jobBuildDetailsIngester
	 * @param jobConfigIngester
	 */
	public RelMngJenkinsJobIngester(JenkinsJobService jenkinsJobService,
			JenkinsServerDetailsService jenkinsServerDetailsService, RelMngJenkinsService relMngJenkinsService,
			RelMngJenkinsJobBuildDetailsIngester jobBuildDetailsIngester) {
		this.jenkinsJobService = jenkinsJobService;
		this.jenkinsServerDetailsService = jenkinsServerDetailsService;
		this.relMngJenkinsService = relMngJenkinsService;
		this.jobBuildDetailsIngester = jobBuildDetailsIngester;
	}

	/**
	 * Scheduled job to update job details from servers. CronExpression should be
	 * set while doing the setup in properties file or DB
	 * 
	 */
	@Scheduled(cron = "0 0/3 * * * *")
	public void init() {
		jenkinsServerDetailsService.getAllServer().forEach(this::check);
	}

	/**
	 * @param serverName
	 */
	public void updateJobDetails(String serverName) {
		var serverRecord = jenkinsServerDetailsService.getServerByName(serverName);
		this.check(serverRecord);

	}

	/**
	 * @param serverRecord
	 */
	private void check(JenkinsServerDetailsRecord serverRecord) {
		// create the client to get all the job details
		// we have and save
		try (var jenkinsServer = RelMngJenkinsServerFactory.of(serverRecord.bassUrl(), serverRecord.userName(),
				serverRecord.token())) {
			var jobsOnJenkinsServer = relMngJenkinsService.getAllJobs(jenkinsServer);

			// check if server is having jobs or not
			if (!jobsOnJenkinsServer.isEmpty()) {
				var jobs = jenkinsJobService.getAllJobsByServer(serverRecord.pkId());
				var exitingJobs = jobs.stream().collect(Collectors.toMap(job -> job.fullName(), job -> job));

				// old jobs that are there in DB
				var existingList = jobsOnJenkinsServer.keySet().stream().filter(name -> exitingJobs.containsKey(name))
						.toList();

				logger.info("existingList is " + existingList);

				updateAllDetailsOfServer(serverRecord, existingList, jobsOnJenkinsServer, exitingJobs, false);

				// new jobs that is not there in DB
				var newList = jobsOnJenkinsServer.keySet().stream().filter(name -> !exitingJobs.containsKey(name))
						.toList();
				logger.info("newList is " + newList);

				updateAllDetailsOfServer(serverRecord, newList, jobsOnJenkinsServer, exitingJobs, true);

				// TO-DO handle deleted job and make it inactive
				var inActiveJobList = exitingJobs.keySet().stream()
						.filter(name -> !jobsOnJenkinsServer.containsKey(name))
						.map(jobName -> exitingJobs.get(jobName).pkJobId()).toList();
				logger.info("inActiveJobList is " + inActiveJobList);
				jenkinsJobService.markJobAsInActive(inActiveJobList);
			} else {

			}

		}
	}

	/**
	 * @param serverRecord
	 * @param jobList
	 * @param serverJobs
	 * @param exitingJobs
	 * @param isNew
	 */
	private void updateAllDetailsOfServer(JenkinsServerDetailsRecord serverRecord, List<String> jobList,
			Map<String, JobWithDetails> serverJobs, Map<String, JobRecord> exitingJobs, boolean isNew) {
		// job -> details
		if (isNew) {
			// get all the details from the servers and save it in aggregate manner.
			List<JobRecord> saveJobsDetails = jobList.parallelStream().map(jobName -> {
				JobWithDetails jobWithDetails = serverJobs.get(jobName);
				return new JobRecord(0, jobWithDetails.getName(), jobWithDetails.getUrl(), jobWithDetails.getFullName(),
						jobWithDetails.get_class(), jobWithDetails.getDescription(), jobWithDetails.getDisplayName(),
						serverRecord.pkId(), jobWithDetails.isBuildable(), true);
			}).toList();
			saveJobsDetails = jenkinsJobService.saveJob(saveJobsDetails);
			aggregateJobBuildDetails(serverRecord, saveJobsDetails, serverJobs);
		} else {
			// update or save the list details in aggregate manner
			// match the details of the jobs for update
			List<JobRecord> updateJobsDetails = jobList.parallelStream().map(jobName -> {
				var jobRecord = exitingJobs.get(jobName);
				var jobWithDetails = serverJobs.get(jobName);
				jobRecord = new JobRecord(jobRecord.pkJobId(), jobWithDetails.getName(), jobWithDetails.getUrl(),
						jobWithDetails.getFullName(), jobWithDetails.get_class(), jobWithDetails.getDescription(),
						jobWithDetails.getDisplayName(), serverRecord.pkId(), jobWithDetails.isBuildable(),
						jobRecord.isActive());

				return jobRecord;
			}).toList();
			updateJobsDetails = jenkinsJobService.saveJob(updateJobsDetails);
			aggregateJobBuildDetails(serverRecord, updateJobsDetails, serverJobs);
		}
	}

	/**
	 * @param serverJobs
	 * @param savedJobsDetails
	 * 
	 */
	private void aggregateJobBuildDetails(JenkinsServerDetailsRecord serverRecord, List<JobRecord> savedJobsDetails,
			Map<String, JobWithDetails> serverJobs) {
		savedJobsDetails.forEach(job -> {
			if (job.isBuildable())
				jobBuildDetailsIngester.updateBuildDetails(serverRecord, job, serverJobs.get(job.fullName()));
		});
	}

}
