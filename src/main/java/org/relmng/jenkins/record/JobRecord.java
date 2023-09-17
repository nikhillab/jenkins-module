package org.relmng.jenkins.record;

/**
 * @author nikhil
 *
 */
public record JobRecord(long pkJobId, String name, String url, String fullName, String type, String description,
		String displayName, long jenkinsServerDetailsId, boolean isBuildable, boolean isActive) {
}
