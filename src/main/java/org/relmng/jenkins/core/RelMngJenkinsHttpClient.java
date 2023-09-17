package org.relmng.jenkins.core;

import java.net.URI;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.relmng.jenkins.interceptors.JenkinsAPIRequestInterceptors;

import com.offbytwo.jenkins.client.JenkinsHttpClient;

final class RelMngJenkinsHttpClient extends JenkinsHttpClient {

	public RelMngJenkinsHttpClient(URI uri, CloseableHttpClient client) {
		super(uri, client);
	}

	public RelMngJenkinsHttpClient(URI uri, HttpClientBuilder builder) {
		this(uri, builder.build());
	}

	public RelMngJenkinsHttpClient(URI uri) {
		this(uri, HttpClientBuilder.create());
	}

	public RelMngJenkinsHttpClient(URI uri, String username, String password) {
		this(uri, HttpClientBuilder.create().addInterceptorLast(new JenkinsAPIRequestInterceptors()), username,
				password);
	}

	public RelMngJenkinsHttpClient(URI uri, HttpClientBuilder builder, String username, String password) {
		super(uri, builder, username, password);
	}

}
