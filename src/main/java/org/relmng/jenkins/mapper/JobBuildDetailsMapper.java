/**
 * 
 */
package org.relmng.jenkins.mapper;

import org.relmng.jenkins.model.JenkinsJobBuildDetails;
import org.relmng.jenkins.record.BuildRecord;
import org.springframework.beans.BeanUtils;

import com.offbytwo.jenkins.model.BuildWithDetails;

/**
 * @author nikhil
 */
public class JobBuildDetailsMapper {

	public static BuildRecord mapJobBuildDetailsToRecord(JenkinsJobBuildDetails jobBuildDetails) {
		BuildRecord buildRecord = new BuildRecord(jobBuildDetails.getNumber(), jobBuildDetails.getQueueId(),
				jobBuildDetails.getUrl(), jobBuildDetails.getJenkinsJobId(),
				copyBeansProperties(jobBuildDetails, new BuildWithDetails()));
		return buildRecord;
	}

	private static <T, R> T copyBeansProperties(R source, T target) {
		BeanUtils.copyProperties(source, target);
		return target;
	}
}
