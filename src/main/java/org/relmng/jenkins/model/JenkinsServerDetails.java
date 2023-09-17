package org.relmng.jenkins.model;

import java.util.Set;

import org.relmng.core.model.EnvironmentDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author nikhil
 *
 */
@Entity
@Table(name = "jenkins_server_details")
public class JenkinsServerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pkId;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "bassUrl", unique = true, nullable = false)
	private String bassUrl;

	private String userName;
	private String token;
	private boolean isActive;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ENVIRONMENT_ID", nullable = false, updatable = false)
	private EnvironmentDetails environment;

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
	 * @return the bassUrl
	 */
	public String getBassUrl() {
		return bassUrl;
	}

	/**
	 * @param bassUrl the bassUrl to set
	 */
	public void setBassUrl(String bassUrl) {
		this.bassUrl = bassUrl;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
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

	/**
	 * @return the pkId
	 */
	public long getPkId() {
		return pkId;
	}

	/**
	 * @param pkId the pkId to set
	 */
	public void setPkId(long pkId) {
		this.pkId = pkId;
	}

	/**
	 * @return the environment
	 */
	public EnvironmentDetails getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(EnvironmentDetails environment) {
		this.environment = environment;
	}

}
