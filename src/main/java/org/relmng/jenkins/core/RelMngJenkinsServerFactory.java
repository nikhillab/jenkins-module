/**
 * 
 */
package org.relmng.jenkins.core;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author nikhil
 * 
 *         to be done for better use of Http clients
 *
 */
public class RelMngJenkinsServerFactory {

	/**
	 * @param bassUrl
	 * @param userName
	 * @param token
	 * @return
	 */
	public static RelMngJenkinsServer of(String bassUrl, String userName, String token) {
		try {
			return RelMngJenkinsServer.of(new URI(bassUrl), userName, token);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
