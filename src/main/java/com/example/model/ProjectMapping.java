package com.example.model;

import jakarta.json.bind.annotation.JsonbProperty;

public class ProjectMapping {
    private static long idCounter = 0;

    private long id;
    @JsonbProperty("gitlab_project_id")
    private String gitlabProjectId;
    @JsonbProperty("jira_project_key")
    private String jiraProjectKey;

    public ProjectMapping() {
        // Default constructor for JSON-B
    }

    public ProjectMapping(String gitlabProjectId, String jiraProjectKey) {
        this.id = ++idCounter;
        this.gitlabProjectId = gitlabProjectId;
        this.jiraProjectKey = jiraProjectKey;
    }

    public long getId() {
        return id;
    }

    public String getGitlabProjectId() {
        return gitlabProjectId;
    }

    public void setGitlabProjectId(String gitlabProjectId) {
        this.gitlabProjectId = gitlabProjectId;
    }

    public String getJiraProjectKey() {
        return jiraProjectKey;
    }

    public void setJiraProjectKey(String jiraProjectKey) {
        this.jiraProjectKey = jiraProjectKey;
    }
}
