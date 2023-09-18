/**
 * 
 */
package org.relmng.jenkins.mapper;

import org.relmng.jenkins.model.JenkinsServerDetails;
import org.relmng.jenkins.record.JenkinsServerDetailsRecord;

/**
 * @author nikhil
 *
 */
public class JenkinsServerDetailsMapper {
	public static JenkinsServerDetailsRecord mapToJenkinsServerDetailsRecord(
			JenkinsServerDetails jenkinsServerDetails) {
		return new JenkinsServerDetailsRecord(jenkinsServerDetails.getPkId(), jenkinsServerDetails.getName(),
				jenkinsServerDetails.getBassUrl(), jenkinsServerDetails.getUserName(), jenkinsServerDetails.getToken(),
				jenkinsServerDetails.getEnvironment(), jenkinsServerDetails.isActive());
	}

	public static JenkinsServerDetails mapRecordToJenkinsServerDetails(
			JenkinsServerDetailsRecord jenkinsServerDetailsRecord) {
		JenkinsServerDetails jenkinsServerDetails = new JenkinsServerDetails();
		jenkinsServerDetails.setPkId(jenkinsServerDetailsRecord.pkId());
		jenkinsServerDetails.setName(jenkinsServerDetailsRecord.name());
		jenkinsServerDetails.setActive(jenkinsServerDetailsRecord.isActive());
		jenkinsServerDetails.setToken(jenkinsServerDetailsRecord.token());
		jenkinsServerDetails.setUserName(jenkinsServerDetailsRecord.userName());
		jenkinsServerDetails.setEnvironment(jenkinsServerDetailsRecord.environment());
		jenkinsServerDetails.setBassUrl(jenkinsServerDetailsRecord.bassUrl());
		return jenkinsServerDetails;
	}
}
