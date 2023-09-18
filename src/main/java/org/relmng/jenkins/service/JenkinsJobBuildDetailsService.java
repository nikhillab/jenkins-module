package org.relmng.jenkins.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.relmng.jenkins.mapper.JobBuildDetailsMapper;
import org.relmng.jenkins.model.JenkinsJobBuildDetails;
import org.relmng.jenkins.record.BuildRecord;
import org.relmng.jenkins.record.JobBuildDetailsRecord;
import org.relmng.jenkins.repository.JenkinsJobBuildDetailsRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;

@Service
public class JenkinsJobBuildDetailsService {
	private final JenkinsJobBuildDetailsRepository jenkinsJobBuildDetailsRepository;
	private ObjectMapper objectMapper;

	/**
	 * @param jenkinsJobBuildDetailsRepository
	 */
	public JenkinsJobBuildDetailsService(JenkinsJobBuildDetailsRepository jenkinsJobBuildDetailsRepository,
			ObjectMapper objectMapper) {
		this.jenkinsJobBuildDetailsRepository = jenkinsJobBuildDetailsRepository;
		this.objectMapper = objectMapper;
	}

	/**
	 * @param buildRecord
	 */
	public void saveBuildDetails(BuildRecord buildRecord) {
		jenkinsJobBuildDetailsRepository.save(null);
	}

	/**
	 * @param pkJobId
	 */
	public Optional<BuildRecord> getLastBuildDetails(long pkJobId) {
		var jenkinsJobBuildDetails = jenkinsJobBuildDetailsRepository.findByJenkinsJobId(pkJobId);
		if (!jenkinsJobBuildDetails.isEmpty()) {
			Collections.sort(jenkinsJobBuildDetails,
					Comparator.comparingLong(JenkinsJobBuildDetails::getId).reversed());
			return Optional.of(JobBuildDetailsMapper.mapJobBuildDetailsToRecord(jenkinsJobBuildDetails.get(0)));
		}
		return Optional.empty();

	}

	/**
	 * @param buildRecords
	 * @return
	 * @throws IOException
	 */
	public List<BuildRecord> saveBuildDetails(List<BuildRecord> buildRecords) throws IOException {

		// on hold for now where to place
//		List actions = details.getActions(); // we can ignore this
//		TestReport testReport = details.getTestReport();
//		TestResult testResult = details.getTestResult();

		var jenkinsJobBuildDetailsList = new ArrayList<JenkinsJobBuildDetails>(buildRecords.size());
		for (var buildRecord : buildRecords) {
			BuildWithDetails details = buildRecord.details();
			JenkinsJobBuildDetails jenkinsJobBuildDetails = new JenkinsJobBuildDetails();

			jenkinsJobBuildDetails.setNumber(buildRecord.number());
			jenkinsJobBuildDetails.setUrl(buildRecord.url());
			jenkinsJobBuildDetails.setQueueId(buildRecord.queueId());
			jenkinsJobBuildDetails.setJenkinsJobId(buildRecord.jenkinsJobId());

			jenkinsJobBuildDetails.setId(Long.valueOf(details.getId()));
			jenkinsJobBuildDetails.setDisplayName(details.getDisplayName());
			jenkinsJobBuildDetails.setTimeStamp(details.getTimestamp());
			jenkinsJobBuildDetails.setDuration(details.getDuration());
			jenkinsJobBuildDetails.setEstimatedDuration(details.getEstimatedDuration());
			jenkinsJobBuildDetails.setFullDisplayName(details.getFullDisplayName());

			if (details.isBuilding()) {
				jenkinsJobBuildDetails.setResult(BuildResult.BUILDING.toString());
			} else {
				jenkinsJobBuildDetails.setResult(details.getResult().toString());
			}

			// for now save json data
			jenkinsJobBuildDetails.setParameters(convertToJson(details.getParameters()));
//			jenkinsJobBuildDetails.setConsoleOutputText(details.getConsoleOutputText().substring(0, 20));
//			jenkinsJobBuildDetails.setConsoleOutputHtml(details.getConsoleOutputHtml().substring(0, 20));
			jenkinsJobBuildDetails.setArtifacts(convertToJson(details.getArtifacts()));
			jenkinsJobBuildDetails.setCauses(convertToJson(details.getCauses()));
			jenkinsJobBuildDetails.setCulprits(convertToJson(details.getCulprits()));
			jenkinsJobBuildDetails.setChangeSets(convertToJson(details.getChangeSets()));

			jenkinsJobBuildDetailsList.add(jenkinsJobBuildDetails);
		}

		List<JenkinsJobBuildDetails> buildDetails = jenkinsJobBuildDetailsRepository
				.saveAll(jenkinsJobBuildDetailsList);

//		buildDetails.stream().map(null)

		return buildRecords;
	}

	/**
	 * @param jobId
	 * @return 
	 */
	public List<JobBuildDetailsRecord> getBuildDetails(long jobId) {
		return jenkinsJobBuildDetailsRepository.findByJenkinsJobId(jobId).stream().map(JobBuildDetailsMapper::mapJobBuildDetailsToBuildDetailsRecord).toList();

	}

	private <T> String convertToJson(T value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException jsonProcessingException) {
			return jsonProcessingException.getMessage();
		}
	}

}
