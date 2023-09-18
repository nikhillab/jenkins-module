package org.relmng.jenkins.controller;

import java.util.Set;

import org.relmng.jenkins.record.JobRecord;
import org.relmng.jenkins.service.JenkinsJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private final JenkinsJobService jenkinsJobService;

	/**
	 * @param jenkinsJobService
	 */
	public JenkinsJobController(JenkinsJobService jenkinsJobService) {
		this.jenkinsJobService = jenkinsJobService;
	}


	@GetMapping("/{serverId}")
	public ResponseEntity<Set<JobRecord>> getAllJobsByEnvironment(@PathVariable Long serverId) {
		return ResponseEntity.status(HttpStatus.OK).body(jenkinsJobService.getAllJobsByServer(serverId));
	}
}
