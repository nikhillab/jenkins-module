/**
 * 
 */
package org.relmng.jenkins.record;

/**
 * @author nikhil
 *
 */
public record JenkinsServerDetailsRecord(Long pkId, String name, String bassUrl, String userName, String token,
		Long environment, boolean isActive) {

}
