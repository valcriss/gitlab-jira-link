package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Link between a GitLab issue and a JIRA issue")
public class IssueLink extends PanacheEntity {
    @Schema(description = "Identifier of the GitLab issue")
    public Long gitlabIssueId;
    @Schema(description = "Key of the JIRA issue")
    public String jiraIssueKey;
}
