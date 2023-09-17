package org.relmng.jenkins.record;

import java.util.List;

import com.offbytwo.jenkins.model.QueueItem;

/**
 * @author nikhil
 *
 */
public record JobDetailRecord(String description, String displayName, boolean buildable, List<BuildRecord> builds,
		BuildRecord firstBuild, BuildRecord lastBuild, BuildRecord lastCompletedBuild, BuildRecord lastFailedBuild,
		BuildRecord lastStableBuild, BuildRecord lastSuccessfulBuild, BuildRecord lastUnstableBuild,
		BuildRecord lastUnsuccessfulBuild, int nextBuildNumber, boolean inQueue, QueueItem queueItem,
		List<JobRecord> downstreamProjects, List<JobRecord> upstreamProjects) {
}
