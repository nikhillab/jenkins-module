package org.relmng.jenkins.repository;

import java.util.List;

import org.relmng.jenkins.model.JenkinsJobBuildDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenkinsJobBuildDetailsRepository extends JpaRepository<JenkinsJobBuildDetails, Long> {

	/**
	 * @param pkJobId
	 * @return
	 */
	public abstract List<JenkinsJobBuildDetails> findByJenkinsJobId(long pkJobId);
}
