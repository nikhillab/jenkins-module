package org.relmng.jenkins.repository;

import org.relmng.jenkins.model.JenkinsAuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenkinsAuditHistoryRepository extends JpaRepository<JenkinsAuditHistory, Long> {}
