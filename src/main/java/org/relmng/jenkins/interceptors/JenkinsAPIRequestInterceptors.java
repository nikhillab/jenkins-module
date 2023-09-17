package org.relmng.jenkins.interceptors;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nikhil
 *
 */
public class JenkinsAPIRequestInterceptors implements HttpRequestInterceptor {
	Logger logger = LoggerFactory.getLogger(JenkinsAPIRequestInterceptors.class);

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		logRequest(request);
	}

	private void logRequest(HttpRequest request) {
		logger.info("Request is " + request.getRequestLine().getUri());
	}
}
