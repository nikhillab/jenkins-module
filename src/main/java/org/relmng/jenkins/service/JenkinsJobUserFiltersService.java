package org.relmng.jenkins.service;

import org.relmng.jenkins.repository.JenkinsJobUserFiltersRepository;
import org.springframework.stereotype.Service;

@Service
public class JenkinsJobUserFiltersService {
	private final JenkinsJobUserFiltersRepository jenkinsJobUserFiltersRepository;

	/**
	 * @param jenkinsJobUserFiltersRepository
	 */
	public JenkinsJobUserFiltersService(JenkinsJobUserFiltersRepository jenkinsJobUserFiltersRepository) {
		this.jenkinsJobUserFiltersRepository = jenkinsJobUserFiltersRepository;
	}
}
