package org.relmng.jenkins.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.relmng.core.record.EnvironmentDetailsRecord;
import org.relmng.jenkins.mapper.JobMapper;
import org.relmng.jenkins.model.JenkinsJob;
import org.relmng.jenkins.record.JobRecord;
import org.relmng.jenkins.repository.JenkinsJobRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class JenkinsJobService {
	private final JenkinsJobRepository jenkinsJobRepository;

	/**
	 * @param jenkinsJobRepository
	 */
	public JenkinsJobService(JenkinsJobRepository jenkinsJobRepository) {
		this.jenkinsJobRepository = jenkinsJobRepository;
	}

	/**
	 * count of active jobs
	 * 
	 * @return long
	 */
	public long getAllJobsDetailsCount() {
		return jenkinsJobRepository.count();
	}

	/**
	 * @return Set
	 * 
	 */
	public Set<JobRecord> getAllJobsByServer(Long serverId) {
		return jenkinsJobRepository.findAll(Example.of(new JenkinsJob() {
			{
				setJenkinsServerDetailsId(serverId);
			}
		})).stream().map(JobMapper::mapToJobRecord).collect(Collectors.toSet());
	}

	public JobRecord saveJob(JobRecord jobRecord) {
		JenkinsJob jenkinsJob = JobMapper.mapRecordToJenkinsJob(jobRecord);
		jenkinsJob = jenkinsJobRepository.save(jenkinsJob);
		return JobMapper.mapToJobRecord(jenkinsJob);
	}

	public List<JobRecord> saveJob(List<JobRecord> jobRecord) {
		List<JenkinsJob> list = jobRecord.stream().map(JobMapper::mapRecordToJenkinsJob).toList();
		list = jenkinsJobRepository.saveAll(list);
		return list.stream().map(JobMapper::mapToJobRecord).toList();
	}

	public void markJobAsInActive(List<Long> jobsIds) {
		jenkinsJobRepository.markJobAsInActive(jobsIds);
	}

}
