package org.relmng.jenkins.mapper;

import org.relmng.jenkins.model.JenkinsJob;
import org.relmng.jenkins.record.JobRecord;

/**
 * @author nikhil
 *
 */
public final class JobMapper {

	public static JobRecord mapToJobRecord(JenkinsJob jenkinsJob) {
		return new JobRecord(jenkinsJob.getPkJobId(), jenkinsJob.getName(), jenkinsJob.getUrl(),
				jenkinsJob.getFullName(), jenkinsJob.getType(), jenkinsJob.getDescription(),
				jenkinsJob.getDisplayName(), jenkinsJob.getJenkinsServerDetailsId(), jenkinsJob.isBuildable(),
				jenkinsJob.isActive());
	}

	public static JenkinsJob mapRecordToJenkinsJob(JobRecord jobRecord) {
		JenkinsJob jenkinsJob = new JenkinsJob();
		jenkinsJob.setName(jobRecord.name());
		jenkinsJob.setJenkinsServerDetailsId(jobRecord.jenkinsServerDetailsId());
		jenkinsJob.setFullName(jobRecord.fullName());
		jenkinsJob.setActive(jobRecord.isActive());
		jenkinsJob.setDisplayName(jobRecord.displayName());
		jenkinsJob.setPkJobId(jobRecord.pkJobId());
		jenkinsJob.setType(jobRecord.type());
		jenkinsJob.setUrl(jobRecord.url());
		jenkinsJob.setDescription(jobRecord.url());
		jenkinsJob.setBuildable(jobRecord.isBuildable());
		return jenkinsJob;
	}

}
