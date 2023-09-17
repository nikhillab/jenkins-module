/**
 * 
 */
package org.relmng.jenkins.record;

import org.relmng.core.record.EnvironmentDetailsRecord;

/**
 * @author nikhil
 *
 */
public record JenkinsServerDetailsRecord(long pkId, String name, String bassUrl, String userName, String token,
		EnvironmentDetailsRecord environment,boolean isActive) {

}
