package org.relmng.jenkins.controller;

import java.util.List;
import java.util.Set;

import org.relmng.core.record.EnvironmentDetailsRecord;
import org.relmng.jenkins.record.JobRecord;
import org.relmng.jenkins.service.JenkinsJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nikhil
 *
 */
@RestController
@RequestMapping("/jobs")
public class JenkinsJobController {

	@Autowired
	private JenkinsJobService jenkinsJobService;

	@GetMapping("/{jobName}")
	public List<Object> getJobDetails(@PathVariable String jobName) {
		return null;
	}

	@GetMapping("/{serverId}")
	public Set<JobRecord> getAllJobsByEnvironment(@PathVariable Long serverId) {
		return jenkinsJobService.getAllJobsByServer(serverId);
	}
}
