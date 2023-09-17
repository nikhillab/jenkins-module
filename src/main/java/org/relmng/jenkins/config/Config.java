package org.relmng.jenkins.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import org.relmng.core.file.RelMngFileManager;
import org.relmng.core.file.RelMngFileManagerType;
import org.relmng.jenkins.core.RelMngJenkinsServer;
import org.relmng.jenkins.core.RelMngJenkinsServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nikhil
 *
 */
@Configuration
@EntityScan(basePackages = { "${entity.packages}" })
@EnableJpaRepositories(basePackages = { "${repository.packages}" })
//@EnableScheduling
public class Config {

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

	@Bean
	public RelMngFileManager relMngFileManager(@Value("${env.type}") String type) {
		return RelMngFileManager.getFileManager(RelMngFileManagerType.valueOf(type));
	}

}
