package org.relmng.jenkins.repository;

import java.util.Optional;

import org.relmng.jenkins.model.JenkinsServerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenkinsServerDetailsRepository extends JpaRepository<JenkinsServerDetails, Long> {

	/**
	 * @param serverName
	 * @return Optional<JenkinsServerDetailsRecord>
	 */
	public Optional<JenkinsServerDetails> findByName(String serverName);
}
