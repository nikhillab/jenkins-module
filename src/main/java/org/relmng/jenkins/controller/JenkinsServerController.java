/**
 * 
 */
package org.relmng.jenkins.controller;

import java.util.List;

import org.relmng.jenkins.record.JenkinsServerDetailsRecord;
import org.relmng.jenkins.service.JenkinsServerDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/server")
public class JenkinsServerController {

	private final JenkinsServerDetailsService jenkinsServerDetailsService;

	/**
	 * @param jenkinsServerDetailsService
	 */
	public JenkinsServerController(JenkinsServerDetailsService jenkinsServerDetailsService) {
		this.jenkinsServerDetailsService = jenkinsServerDetailsService;
	}

	/**
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<JenkinsServerDetailsRecord>> getAllServer() {
		var allServer = jenkinsServerDetailsService.getAllServer();
		return ResponseEntity.status(HttpStatus.OK).body(allServer);
	}

	/**
	 * @return
	 */
	@GetMapping("/{name}")
	public ResponseEntity<JenkinsServerDetailsRecord> getServer(@PathVariable String name) {
		var allServer = jenkinsServerDetailsService.getServerByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(allServer);
	}

	/**
	 * @param jenkinsServerDetailsRecord
	 * @return
	 */
	@PostMapping
	public ResponseEntity<JenkinsServerDetailsRecord> saveJenkinsServerDetails(
			@RequestBody JenkinsServerDetailsRecord jenkinsServerDetailsRecord) {
		jenkinsServerDetailsRecord = jenkinsServerDetailsService.saveJenkinsServerDetails(jenkinsServerDetailsRecord);
		return ResponseEntity.status(HttpStatus.CREATED).body(jenkinsServerDetailsRecord);
	}

	/**
	 * @param jenkinsServerDetailsRecord
	 * @return
	 */
	@PutMapping
	public ResponseEntity<JenkinsServerDetailsRecord> updateJenkinsServerDetails(
			@RequestBody JenkinsServerDetailsRecord jenkinsServerDetailsRecord) {
		jenkinsServerDetailsRecord = jenkinsServerDetailsService.updateJenkinsServerDetails(jenkinsServerDetailsRecord);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(jenkinsServerDetailsRecord);
	}

}
