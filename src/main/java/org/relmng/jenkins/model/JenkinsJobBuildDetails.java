package org.relmng.jenkins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jenkins_job_build_details")
public class JenkinsJobBuildDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobBuildId;
	private int number;
	private int queueId;
	private String url;

	private long jenkinsJobId;

	private String parameters; // json
	private String results;
	private String displayName;
	private long timeStamp;
	private String artifacts; // files paths
	private long duration;
	private long estimatedDuration;
	private String fullDisplayName;
	private long id;
	private String result;
	private String consoleOutputText; // text file path
	private String consoleOutputHtml; // text file path
	private String changeSets; // json
	private String culprits;
	private String causes;

	/**
	 * @return the jobBuildId
	 */
	public long getJobBuildId() {
		return jobBuildId;
	}

	/**
	 * @param jobBuildId the jobBuildId to set
	 */
	public void setJobBuildId(long jobBuildId) {
		this.jobBuildId = jobBuildId;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the queueId
	 */
	public int getQueueId() {
		return queueId;
	}

	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(int queueId) {
		this.queueId = queueId;
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
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the results
	 */
	public String getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(String results) {
		this.results = results;
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
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the artifacts
	 */
	public String getArtifacts() {
		return artifacts;
	}

	/**
	 * @param artifacts the artifacts to set
	 */
	public void setArtifacts(String artifacts) {
		this.artifacts = artifacts;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return the estimatedDuration
	 */
	public long getEstimatedDuration() {
		return estimatedDuration;
	}

	/**
	 * @param estimatedDuration the estimatedDuration to set
	 */
	public void setEstimatedDuration(long estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	/**
	 * @return the fullDisplayName
	 */
	public String getFullDisplayName() {
		return fullDisplayName;
	}

	/**
	 * @param fullDisplayName the fullDisplayName to set
	 */
	public void setFullDisplayName(String fullDisplayName) {
		this.fullDisplayName = fullDisplayName;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the consoleOutputText
	 */
	public String getConsoleOutputText() {
		return consoleOutputText;
	}

	/**
	 * @param consoleOutputText the consoleOutputText to set
	 */
	public void setConsoleOutputText(String consoleOutputText) {
		this.consoleOutputText = consoleOutputText;
	}

	/**
	 * @return the consoleOutputHtml
	 */
	public String getConsoleOutputHtml() {
		return consoleOutputHtml;
	}

	/**
	 * @param consoleOutputHtml the consoleOutputHtml to set
	 */
	public void setConsoleOutputHtml(String consoleOutputHtml) {
		this.consoleOutputHtml = consoleOutputHtml;
	}

	/**
	 * @return the changeSets
	 */
	public String getChangeSets() {
		return changeSets;
	}

	/**
	 * @param changeSets the changeSets to set
	 */
	public void setChangeSets(String changeSets) {
		this.changeSets = changeSets;
	}

	/**
	 * @return the culprits
	 */
	public String getCulprits() {
		return culprits;
	}

	/**
	 * @param culprits the culprits to set
	 */
	public void setCulprits(String culprits) {
		this.culprits = culprits;
	}

	/**
	 * @return the causes
	 */
	public String getCauses() {
		return causes;
	}

	/**
	 * @param causes the causes to set
	 */
	public void setCauses(String causes) {
		this.causes = causes;
	}

	/**
	 * @return the jenkinsJobId
	 */
	public long getJenkinsJobId() {
		return jenkinsJobId;
	}

	/**
	 * @param jenkinsJobId the jenkinsJobId to set
	 */
	public void setJenkinsJobId(long jenkinsJobId) {
		this.jenkinsJobId = jenkinsJobId;
	}

}
