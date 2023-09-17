package org.relmng.jenkins;

import org.relmng.core.model.EnvironmentDetails;
import org.relmng.core.repository.EnvironmentDetailsRepository;
import org.relmng.jenkins.core.RelMngJenkinsServer;
import org.relmng.jenkins.core.RelMngJenkinsService;
import org.relmng.jenkins.ingestser.RelMngJenkinsJobIngester;
import org.relmng.jenkins.model.JenkinsServerDetails;
import org.relmng.jenkins.repository.JenkinsServerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.offbytwo.jenkins.helper.JenkinsVersion;

/**
 * @author nikhil
 *
 */
@SpringBootApplication
public class JenkinsModuleApplication implements CommandLineRunner {
	@Autowired
	EnvironmentDetailsRepository environmentDetailsRepository;
	@Autowired
	JenkinsServerDetailsRepository serverDetailsRepository;
	@Autowired
	RelMngJenkinsJobIngester jenkinsJobIngester;
	@Autowired
	RelMngJenkinsServer jenkinsServer;
	@Autowired
	RelMngJenkinsService mngJenkinsService;
	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(JenkinsModuleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		EnvironmentDetails environmentDetails = new EnvironmentDetails();
		environmentDetails.setName("UAT_1");
		environmentDetails.setActive(true);
		environmentDetailsRepository.saveAndFlush(environmentDetails);



//		JenkinsVersion version = jenkinsServer.getVersion();
//		System.out.println(version);

		jenkinsJobIngester.init();

	}

}
