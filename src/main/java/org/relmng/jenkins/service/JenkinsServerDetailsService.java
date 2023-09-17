package org.relmng.jenkins.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.relmng.core.mapper.EnvironmentDetailsMapper;
import org.relmng.jenkins.mapper.JenkinsServerDetailsMapper;
import org.relmng.jenkins.model.JenkinsServerDetails;
import org.relmng.jenkins.record.JenkinsServerDetailsRecord;
import org.relmng.jenkins.repository.JenkinsServerDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class JenkinsServerDetailsService {
	private final JenkinsServerDetailsRepository jenkinsServerDetailsRepository;

	private final Map<String, JenkinsServerDetailsRecord> serverDetailsCache = new ConcurrentHashMap<>();

	/**
	 * @param jenkinsServerDetailsRepository
	 */
	public JenkinsServerDetailsService(JenkinsServerDetailsRepository jenkinsServerDetailsRepository) {
		this.jenkinsServerDetailsRepository = jenkinsServerDetailsRepository;
	}

	/**
	 * @return List
	 */
	public List<JenkinsServerDetailsRecord> getAllServer() {
		// map it to record and return
		if (serverDetailsCache.isEmpty()) {
			jenkinsServerDetailsRepository.findAll().stream()
					.map(JenkinsServerDetailsMapper::mapToJenkinsServerDetailsRecord).toList()
					.forEach(job -> serverDetailsCache.put(job.name(), job));
		}
		return serverDetailsCache.values().stream().toList();

	}

	/**
	 * @param serverName
	 * @return JenkinsServerDetailsRecord
	 */
	public JenkinsServerDetailsRecord getServerByName(String serverName) {
		if (!serverDetailsCache.containsKey(serverName)) {
			Optional<JenkinsServerDetails> serverDetails = jenkinsServerDetailsRepository.findByName(serverName);
			if (!serverDetails.isPresent()) {
				// thrown exception that not found
			}
			serverDetailsCache.put(serverName,
					JenkinsServerDetailsMapper.mapToJenkinsServerDetailsRecord(serverDetails.get()));

		}
		return serverDetailsCache.get(serverName);
	}

	/**
	 * @param jenkinsServerDetailsRecord
	 * @return jenkinsServerDetailsRecord
	 */
	public JenkinsServerDetailsRecord saveJenkinsServerDetails(JenkinsServerDetailsRecord jenkinsServerDetailsRecord) {

		var jenkinsServerDetails = JenkinsServerDetailsMapper
				.mapRecordToJenkinsServerDetails(jenkinsServerDetailsRecord);

		jenkinsServerDetails = jenkinsServerDetailsRepository.save(jenkinsServerDetails);

		jenkinsServerDetailsRecord = JenkinsServerDetailsMapper.mapToJenkinsServerDetailsRecord(jenkinsServerDetails);

		serverDetailsCache.put(jenkinsServerDetailsRecord.name(), jenkinsServerDetailsRecord);

		return jenkinsServerDetailsRecord;
	}

	/**
	 * @param jenkinsServerDetailsRecord
	 * @return jenkinsServerDetailsRecord
	 */
	public JenkinsServerDetailsRecord updateJenkinsServerDetails(
			JenkinsServerDetailsRecord jenkinsServerDetailsRecord) {

		Optional<JenkinsServerDetails> jenkinsServerDetailsOptional = jenkinsServerDetailsRepository
				.findById(jenkinsServerDetailsRecord.pkId());

		if (jenkinsServerDetailsOptional.isPresent()) {
			JenkinsServerDetails jenkinsServerDetails = jenkinsServerDetailsOptional.get();
			jenkinsServerDetails.setPkId(jenkinsServerDetailsRecord.pkId());
			jenkinsServerDetails.setName(jenkinsServerDetailsRecord.name());
			jenkinsServerDetails.setActive(jenkinsServerDetailsRecord.isActive());
			jenkinsServerDetails.setToken(jenkinsServerDetailsRecord.token());
			jenkinsServerDetails.setUserName(jenkinsServerDetailsRecord.userName());
			jenkinsServerDetails.setEnvironment(
					EnvironmentDetailsMapper.mapRecordToEnvironmentDetails(jenkinsServerDetailsRecord.environment()));
			jenkinsServerDetails.setBassUrl(jenkinsServerDetailsRecord.bassUrl());

			jenkinsServerDetails = jenkinsServerDetailsRepository.save(jenkinsServerDetails);
			jenkinsServerDetailsRecord = JenkinsServerDetailsMapper
					.mapToJenkinsServerDetailsRecord(jenkinsServerDetails);
			serverDetailsCache.put(jenkinsServerDetailsRecord.name(), jenkinsServerDetailsRecord);
		}

		return jenkinsServerDetailsRecord;
	}
}
