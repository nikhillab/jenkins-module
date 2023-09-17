package org.relmng.jenkins.record;

import com.offbytwo.jenkins.model.BuildWithDetails;

/**
 * @author nikhil
 *
 */
public record BuildRecord(int number, int queueId, String url, long jenkinsJobId, BuildWithDetails details) {
}
