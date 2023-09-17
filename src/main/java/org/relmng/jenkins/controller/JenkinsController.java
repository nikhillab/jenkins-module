package org.relmng.jenkins.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.ComputerSet;

/**
 * @author nikhil
 *
 */
@RestController
public class JenkinsController {

	@Autowired
	private JenkinsServer jenkinsServer;

	@GetMapping
	public ComputerSet getServerDetails() throws IOException {
		return jenkinsServer.getComputerSet();
	}

	public void addStringParam() throws Exception {
		jenkinsServer.addStringParam(null, null, null, null);
	}

	public void createFolder() throws Exception {
		jenkinsServer.createFolder(null);
	}

	public void createJob() throws Exception {
		jenkinsServer.createJob(null, null);
	}

	public void createView() throws Exception {
		jenkinsServer.createView(null, null);
	}

	public void getVersion() throws Exception {
		jenkinsServer.getVersion();
	}
}
