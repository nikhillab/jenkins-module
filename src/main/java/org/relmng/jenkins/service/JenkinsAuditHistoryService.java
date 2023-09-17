package org.relmng.jenkins.service;

import org.relmng.jenkins.repository.JenkinsAuditHistoryRepository;
import org.springframework.stereotype.Service;
@Service
public class JenkinsAuditHistoryService {
	private final JenkinsAuditHistoryRepository jenkinsAuditHistoryRepository;

	/**
	 * @param jenkinsAuditHistoryRepository
	 */
	public JenkinsAuditHistoryService(JenkinsAuditHistoryRepository jenkinsAuditHistoryRepository) {
		this.jenkinsAuditHistoryRepository = jenkinsAuditHistoryRepository;
	}
}
