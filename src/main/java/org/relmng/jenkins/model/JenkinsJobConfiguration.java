/**
 * 
 */
package org.relmng.jenkins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "jenkins_job_configuration")
public class JenkinsJobConfiguration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobConfigurationId;
	private Long jenkinsJobId;
	private String trigger;
	private String properties;

	/**
	 * @return the jobConfigurationId
	 */
	public Long getJobConfigurationId() {
		return jobConfigurationId;
	}

	/**
	 * @param jobConfigurationId the jobConfigurationId to set
	 */
	public void setJobConfigurationId(Long jobConfigurationId) {
		this.jobConfigurationId = jobConfigurationId;
	}

	/**
	 * @return the jenkinsJobId
	 */
	public Long getJenkinsJobId() {
		return jenkinsJobId;
	}

	/**
	 * @param jenkinsJobId the jenkinsJobId to set
	 */
	public void setJenkinsJobId(Long jenkinsJobId) {
		this.jenkinsJobId = jenkinsJobId;
	}

	/**
	 * @return the trigger
	 */
	public String getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	/**
	 * @return the properties
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}

}
