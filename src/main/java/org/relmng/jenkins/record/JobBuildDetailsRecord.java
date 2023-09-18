/**
 * 
 */
package org.relmng.jenkins.record;

/**
 * 
 */
public record JobBuildDetailsRecord(long jobBuildId, int number, int queueId, String url, long jenkinsJobId,
		String parameters, String results, String displayName, long timeStamp, String artifacts, long duration,
		long estimatedDuration, String fullDisplayName, long id, String result, String consoleOutputText,
		String consoleOutputHtml, String changeSets, String culprits, String causes) {

}
