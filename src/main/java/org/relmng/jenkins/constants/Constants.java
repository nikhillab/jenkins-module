package org.relmng.jenkins.constants;

public interface Constants {
	public static final String FOLDER = "com.cloudbees.hudson.plugins.folder.Folder";

	public static final String WORKFLOW_MULTI_BRANCH_PROJECT = "org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject";

	public static final String MAVEN_PROJECT = "hudson.maven.MavenModuleSet";

	public static final String PIPELINE = "PIPELINE";

	public static final String MULTI_BRANCH_PIPELINE = "MULTI-BRANCH-PIPELINE";

	public static final String API_JSON = "/api/json";

	public static final String JOBS = "jobs";

	public static final String GET_BUILD_PARAMS = "?tree=allBuilds[number[*],url[*],queueId[*]]";
}
