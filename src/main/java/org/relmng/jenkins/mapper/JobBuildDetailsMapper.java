/**
 * 
 */
package org.relmng.jenkins.mapper;

import org.relmng.jenkins.model.JenkinsJobBuildDetails;
import org.relmng.jenkins.record.BuildRecord;
import org.relmng.jenkins.record.JobBuildDetailsRecord;
import org.springframework.beans.BeanUtils;

import com.offbytwo.jenkins.model.BuildWithDetails;

/**
 * @author nikhil
 */
public class JobBuildDetailsMapper {

	public static BuildRecord mapJobBuildDetailsToRecord(JenkinsJobBuildDetails jobBuildDetails) {
		var buildRecord = new BuildRecord(jobBuildDetails.getNumber(), jobBuildDetails.getQueueId(),
				jobBuildDetails.getUrl(), jobBuildDetails.getJenkinsJobId(),
				copyBeansProperties(jobBuildDetails, new BuildWithDetails()));
		return buildRecord;
	}

	public static JobBuildDetailsRecord mapJobBuildDetailsToBuildDetailsRecord(JenkinsJobBuildDetails jobBuildDetails) {
		var buildRecord = new JobBuildDetailsRecord(jobBuildDetails.getJobBuildId(), jobBuildDetails.getNumber(),
				jobBuildDetails.getQueueId(), jobBuildDetails.getUrl(), jobBuildDetails.getJenkinsJobId(),
				jobBuildDetails.getParameters(), jobBuildDetails.getResults(), jobBuildDetails.getDisplayName(),
				jobBuildDetails.getTimeStamp(), jobBuildDetails.getArtifacts(), jobBuildDetails.getDuration(),
				jobBuildDetails.getEstimatedDuration(), jobBuildDetails.getFullDisplayName(), jobBuildDetails.getId(),
				jobBuildDetails.getResult(), jobBuildDetails.getConsoleOutputText(),
				jobBuildDetails.getConsoleOutputHtml(), jobBuildDetails.getChangeSets(), jobBuildDetails.getCulprits(),
				jobBuildDetails.getCauses());
		return buildRecord;
	}

	private static <T, R> T copyBeansProperties(R source, T target) {
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
