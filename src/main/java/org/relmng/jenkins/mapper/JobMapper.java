package org.relmng.jenkins.mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.relmng.jenkins.model.JenkinsJob;
import org.relmng.jenkins.record.BuildRecord;
import org.relmng.jenkins.record.JobRecord;

import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.JobWithDetails;

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

//	public static List<JobRecord> mapToJobs(Map<String, Job> jobsMap) {
//		return jobsMap.values().stream().map(JobMapper::jobToJobDto).toList();
//	}
//
//	public static JobRecord jobToJobDto(Job job) {
//		return new JobRecord(job.getName(), job.getUrl(), job.getFullName(),
//				job.get_class().substring(job.get_class().lastIndexOf(".") + 1));
//	}

//	public static BuildRecord buildToBuildDto(Build build) throws IOException {
//		return new BuildRecord(build.getNumber(), build.getQueueId(), build.getUrl(), build.details());
//	}

	public static List<Object> jobWithDetailsToJobDetailsDto(JobWithDetails jobDetails) throws Exception {

		System.out.println("getDisplayName-> " + jobDetails.getDisplayName());
		System.out.println("getLastUnstableBuild-> " + jobDetails.getLastUnstableBuild());
		System.out.println("hasLastSuccessfulBuildRun-> " + jobDetails.hasLastSuccessfulBuildRun());
		System.out.println("getDownstreamProjects-> " + jobDetails.getDownstreamProjects());
		System.out.println("getLastSuccessfulBuild-> " + jobDetails.getLastSuccessfulBuild());
		System.out.println("hasLastUnstableBuildRun-> " + jobDetails.hasLastUnstableBuildRun());
		System.out.println("hasLastCompletedBuildRun-> " + jobDetails.hasLastCompletedBuildRun());
		System.out.println("hasLastFailedBuildRun-> " + jobDetails.hasLastFailedBuildRun());
		System.out.println("hasLastStableBuildRun-> " + jobDetails.hasLastStableBuildRun());
		System.out.println("getUpstreamProjects-> " + jobDetails.getUpstreamProjects());
		System.out.println("getLastUnsuccessfulBuild-> " + jobDetails.getLastUnsuccessfulBuild());
		System.out.println("hasLastUnsuccessfulBuildRun-> " + jobDetails.hasLastUnsuccessfulBuildRun());
		System.out.println("getLastCompletedBuild-> " + jobDetails.getLastCompletedBuild());
		System.out.println("isInQueue-> " + jobDetails.isInQueue());
		System.out.println("hasFirstBuildRun-> " + jobDetails.hasFirstBuildRun());
		System.out.println("getDescription-> " + jobDetails.getDescription());
		System.out.println("hasDescription-> " + jobDetails.hasDescription());
		System.out.println("getFirstBuild-> " + jobDetails.getFirstBuild());
		System.out.println("isBuildable-> " + jobDetails.isBuildable());
		System.out.println("getBuilds-> " + jobDetails.getBuilds());
		System.out.println("getAllBuilds-> " + jobDetails.getAllBuilds());
		System.out.println("getAllBuilds-> " + jobDetails.getAllBuilds());
		System.out.println("getNextBuildNumber-> " + jobDetails.getNextBuildNumber());
		System.out.println("getLastBuild-> " + jobDetails.getLastBuild());
		System.out.println("hasLastBuildRun-> " + jobDetails.hasLastBuildRun());
		System.out.println("getLastFailedBuild-> " + jobDetails.getLastFailedBuild());
		System.out.println("getQueueItem-> " + jobDetails.getQueueItem());
		System.out.println("getBuildByNumber-> " + jobDetails.getBuildByNumber(24));
		System.out.println("getLastStableBuild-> " + jobDetails.getLastStableBuild());

		return Arrays.asList(

				jobDetails.getDisplayName()

		);
	}

}
