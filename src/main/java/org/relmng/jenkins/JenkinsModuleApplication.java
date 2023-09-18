package org.relmng.jenkins;

import org.relmng.core.crypto.RelMngSecretKeyGenerator;
import org.relmng.core.model.EnvironmentDetails;
import org.relmng.core.record.EnvironmentDetailRecord;
import org.relmng.core.record.RelMngAESConfigRecord;
import org.relmng.core.service.EnvironmentDetailService;
import org.relmng.jenkins.record.JenkinsServerDetailsRecord;
import org.relmng.jenkins.service.JenkinsServerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nikhil
 *
 */
@SpringBootApplication
public class JenkinsModuleApplication implements CommandLineRunner {

	@Autowired
	RelMngSecretKeyGenerator generator;
	@Autowired
	Environment environment;
	@Autowired
	EnvironmentDetailService environmentDetailService;
	@Autowired
	JenkinsServerDetailsService detailsService;

	public static void main(String[] args) {
		SpringApplication.run(JenkinsModuleApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		RelMngAESConfigRecord generateMasterKey = generator.generateMasterKey(
				environment.getRequiredProperty("crypto.key.bits", Integer.class),
				environment.getRequiredProperty("crypto.key.type"), "RELMNG_MASTER.txt");

		EnvironmentDetails environmentDetails = new EnvironmentDetails();
		environmentDetails.setName("UAT_1");
		environmentDetails.setActive(true);

		EnvironmentDetailRecord environmentDetailRecord = environmentDetailService
				.save(new EnvironmentDetailRecord(0l, "UAT_3", generateMasterKey.pkId(), true));


//		JenkinsVersion version = jenkinsServer.getVersion();
//		System.out.println(version);

//		jenkinsJobIngester.init();

	}

}
