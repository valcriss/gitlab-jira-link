package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class IssueLink extends PanacheEntity {
    public Long gitlabIssueId;
    public String jiraIssueKey;
}
