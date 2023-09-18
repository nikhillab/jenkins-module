package org.relmng.jenkins.core;

import java.io.IOException;
import java.net.URI;

import org.relmng.jenkins.config.JenkinsApplicationContext;
import org.relmng.jenkins.constants.Constants;
import org.relmng.jenkins.record.JobRecord;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpConnection;
import com.offbytwo.jenkins.model.BaseModel;

/**
 * @author nikhil
 */
public final class RelMngJenkinsServer extends JenkinsServer {
	/**
	 * 
	 */
	private final JenkinsHttpConnection client;

	/**
	 * @param serverUri
	 */
	private RelMngJenkinsServer(URI serverUri) {
		this(new RelMngJenkinsHttpClient(serverUri));
	}

	/**
	 * @param serverUri
	 * @param username
	 * @param passwordOrToken
	 */
	private RelMngJenkinsServer(URI serverUri, String username, String passwordOrToken) {
		this(new RelMngJenkinsHttpClient(serverUri, username, passwordOrToken));
	}

	/**
	 * @param client
	 */
	private RelMngJenkinsServer(final JenkinsHttpConnection client) {
		super(client);
		this.client = client;
	}

	/**
	 * @param serverUri
	 * @return
	 */
	static RelMngJenkinsServer of(URI serverUri) {
		return new RelMngJenkinsServer(serverUri);
	}

	/**
	 * @param serverUri
	 * @param username
	 * @param passwordOrToken
	 * @return
	 */
	static RelMngJenkinsServer of(URI serverUri, String username, String passwordOrToken) {
		return new RelMngJenkinsServer(serverUri, username, passwordOrToken);
	}

	/**
	 * @param client
	 * @return
	 */
	static RelMngJenkinsServer of(final JenkinsHttpConnection client) {
		return new RelMngJenkinsServer(client);
	}

	/**
	 * @param <T>
	 * @param path
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	public <T extends BaseModel> T getJobByUrl(String path, Class<T> cls) throws IOException {
		return client.get(path, cls);
	}

	/**
	 * @param <T>
	 * @param job
	 * @param valueTypeRef
	 * @return
	 * @throws IOException
	 */
	public <T> T getAllBuilds(JobRecord job, TypeReference<T> valueTypeRef) throws IOException {
		var objectMapper = JenkinsApplicationContext.getBean(ObjectMapper.class);
		var resultJson = client.get(job.url().substring(job.url().indexOf("/job")) + Constants.GET_BUILD_PARAMS);
		return objectMapper.readValue(resultJson, valueTypeRef);
	}
}
