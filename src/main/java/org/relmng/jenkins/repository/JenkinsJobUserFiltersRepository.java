package org.relmng.jenkins.repository;

import org.relmng.jenkins.model.JenkinsJobUserFilters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenkinsJobUserFiltersRepository extends JpaRepository<JenkinsJobUserFilters, Long> {}
