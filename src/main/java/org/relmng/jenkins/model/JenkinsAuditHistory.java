package org.relmng.jenkins.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jenkins_audit_history")
public class JenkinsAuditHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long auditId;
	private LocalDateTime eventTime;
	private String type;
	private String details;

	/**
	 * @return the eventTime
	 */
	public LocalDateTime getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the auditId
	 */
	public long getAuditId() {
		return auditId;
	}

}
