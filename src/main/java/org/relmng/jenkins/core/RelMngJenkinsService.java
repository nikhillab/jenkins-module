/**
 * 
 */
package org.relmng.jenkins.core;

import static org.relmng.jenkins.constants.Constants.API_JSON;
import static org.relmng.jenkins.constants.Constants.FOLDER;
import static org.relmng.jenkins.constants.Constants.JOBS;
import static org.relmng.jenkins.constants.Constants.WORKFLOW_MULTI_BRANCH_PROJECT;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.FolderJob;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

/**
 * @author nikhil
 *
 */

@Component
public class RelMngJenkinsService {

	private final ObjectMapper mapper;

	/**
	 * @param mapper
	 */
	public RelMngJenkinsService(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public Map<String, JobWithDetails> getAllJobs(JenkinsServer jenkinsServer) {
		try {
			var results = new HashMap<String, JobWithDetails>();
			var jobs = jenkinsServer.getJobs();
			for (var job : jobs.values()) {
				extractJobDetails(jenkinsServer, job, false, false, null, results);
			}
			return results;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void extractJobDetails(JenkinsServer jenkinsServer, final Job job, boolean isFolder, boolean isMultiBranch,
			final FolderJob folderJob, Map<String, JobWithDetails> results) throws IOException {

		Optional<JobWithDetails> jobWithDetails = Optional.empty();
		if (!isFolder && !isMultiBranch) {
			jobWithDetails = Optional.of(jenkinsServer.getJob(job.getName()));
		} else if (isFolder) {
			jobWithDetails = Optional.of(jenkinsServer.getJob(folderJob, job.getName()));
		} else if (isMultiBranch) {
			// for WorkflowMultiBranchProject
			extractWorkflowMultiBranchProjectJobs(job, results);
		}
		if (jobWithDetails.isPresent()) {
			JobWithDetails details = jobWithDetails.get();
			if (!details.isBuildable() && details.get_class().equals(WORKFLOW_MULTI_BRANCH_PROJECT)) {
				// do it manually as library is not supporting it now.
				extractJobDetails(jenkinsServer, job, false, true, null, results);
			} else if (details.get_class().equals(FOLDER)) {
				// do it manually as library is not supporting it now.
				extractFolderJobs(jenkinsServer, job, results);
			}
			// need to return it
			results.put(details.getFullName(), details);
		}

	}

	public void extractWorkflowMultiBranchProjectJobs(final Job job, Map<String, JobWithDetails> results)
			throws IOException {
		String string = job.getClient().get(job.getUrl() + API_JSON);
		String jsonNode = mapper.readTree(string).get(JOBS).toPrettyString();
		var readValue = mapper.readValue(jsonNode.getBytes(), new TypeReference<List<Job>>() {
		});
		for (var jobs : readValue) {
			try {
				jobs.setClient(job.getClient());
				// need to return it
				JobWithDetails jobWithDetails = jobs.details();
//				Class<Job> classJobWithDetails = Job.class;
//				Field field = classJobWithDetails.getDeclaredField("name");
//				field.setAccessible(true);
//				String replaceAll = jobWithDetails.getFullName().replaceAll("/", "/job/");
//				System.out.println(replaceAll);
//				ReflectionUtils.setField(field, jobWithDetails, replaceAll);
				results.put(jobWithDetails.getFullName(), jobWithDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void extractFolderJobs(JenkinsServer jenkinsServer, final Job job, Map<String, JobWithDetails> results)
			throws IOException {
		com.google.common.base.Optional<FolderJob> folderJobOptional = jenkinsServer.getFolderJob(job);
		if (folderJobOptional.isPresent() && folderJobOptional.get().isFolder()) {
			FolderJob folderJob = folderJobOptional.get();
			for (var jobName : folderJob.getJobs().values()) {
				extractJobDetails(jenkinsServer, jobName, true, false, folderJob, results);
			}
		}

	}

}
