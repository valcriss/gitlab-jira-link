package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Mapping between a GitLab project and a JIRA project")
public class ProjectMapping extends PanacheEntity {
    @Schema(description = "GitLab project identifier")
    public String gitlabProject;
    @Schema(description = "JIRA project key")
    public String jiraProject;
}
