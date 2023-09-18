package org.relmng.jenkins.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import org.relmng.core.config.CoreConfigs;
import org.relmng.jenkins.core.RelMngJenkinsServer;
import org.relmng.jenkins.core.RelMngJenkinsServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nikhil
 *
 */
@Configuration
@EntityScan(basePackages = { "${packages.entity}" })
@EnableJpaRepositories(basePackages = { "${packages.repository}" })
//@EnableScheduling
@Import(value = { CoreConfigs.class })
public class JenkinsConfigs {

	@Autowired
	private Environment environment;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper;
	}

	@Bean
	public RelMngJenkinsServer relMngJenkinsServer() {
		return RelMngJenkinsServerFactory.of(environment.getProperty("jenkins.url"),
				environment.getProperty("jenkins.user.name"), environment.getProperty("jenkins.user.token"));
	}

	

}
