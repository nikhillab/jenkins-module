package org.relmng.jenkins.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jenkins_jobs")
public class JenkinsJob {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pkJobId;
	private String name;
	private String url;
	private String fullName;
	private String type;
	private String description;
	private String displayName;

	private long jenkinsServerDetailsId;

	@OneToMany(mappedBy = "jenkinsJobId", fetch = FetchType.LAZY)
	private Set<JenkinsJobBuildDetails> jenkinsJobBuildDetails;

	private boolean isBuildable;
	private boolean isActive;

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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the jenkinsServerDetails
	 */
	public long getJenkinsServerDetailsId() {
		return jenkinsServerDetailsId;
	}

	/**
	 * @param jenkinsServerDetails the jenkinsServerDetails to set
	 */
	public void setJenkinsServerDetailsId(long jenkinsServerDetailsId) {
		this.jenkinsServerDetailsId = jenkinsServerDetailsId;
	}

	/**
	 * @return the jenkinsJobBuildDetails
	 */
	public Set<JenkinsJobBuildDetails> getJenkinsJobBuildDetails() {
		return jenkinsJobBuildDetails;
	}

	/**
	 * @param jenkinsJobBuildDetails the jenkinsJobBuildDetails to set
	 */
	public void setJenkinsJobBuildDetails(Set<JenkinsJobBuildDetails> jenkinsJobBuildDetails) {
		this.jenkinsJobBuildDetails = jenkinsJobBuildDetails;
	}

	/**
	 * @return the pkJobId
	 */
	public long getPkJobId() {
		return pkJobId;
	}

	/**
	 * @param pkJobId the pkJobId to set
	 */
	public void setPkJobId(long pkJobId) {
		this.pkJobId = pkJobId;
	}

	/**
	 * @return the isBuildable
	 */
	public boolean isBuildable() {
		return isBuildable;
	}

	/**
	 * @param isBuildable the isBuildable to set
	 */
	public void setBuildable(boolean isBuildable) {
		this.isBuildable = isBuildable;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
