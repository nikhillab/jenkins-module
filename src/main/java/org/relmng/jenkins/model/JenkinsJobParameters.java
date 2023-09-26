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
 * @author nikhil
 */
@Entity
@Table(name = "jenkins_job_parameters")
public class JenkinsJobParameters {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobParametersId;
	private Long jenkinsJobId;
	private String name;
	private String description;
	private String defaultValue;
	private String type;

	/**
	 * @return the jobParametersId
	 */
	public Long getJobParametersId() {
		return jobParametersId;
	}

	/**
	 * @param jobParametersId the jobParametersId to set
	 */
	public void setJobParametersId(Long jobParametersId) {
		this.jobParametersId = jobParametersId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
