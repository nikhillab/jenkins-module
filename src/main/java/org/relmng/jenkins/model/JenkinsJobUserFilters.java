package org.relmng.jenkins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jenkins_job_user_filters")
public class JenkinsJobUserFilters {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long filterId;
}
