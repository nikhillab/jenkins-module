/**
 * 
 */
package org.relmng.jenkins.controller;

import java.util.List;

import org.relmng.jenkins.record.JobBuildDetailsRecord;
import org.relmng.jenkins.service.JenkinsJobBuildDetailsService;
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
@RequestMapping("/details")
public class JenkinsJobBuildDetailsController {

	private final JenkinsJobBuildDetailsService buildDetailsService;

	/**
	 * @param buildDetailsService
	 */
	public JenkinsJobBuildDetailsController(JenkinsJobBuildDetailsService buildDetailsService) {
		this.buildDetailsService = buildDetailsService;
	}

	@GetMapping("/{jobId}")
	public ResponseEntity<List<JobBuildDetailsRecord>> getJobDetails(@PathVariable long jobId) {
		return ResponseEntity.status(HttpStatus.OK).body(buildDetailsService.getBuildDetails(jobId));
	}
}
