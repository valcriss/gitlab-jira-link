package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ProjectMapping extends PanacheEntity {
    public String gitlabProject;
    public String jiraProject;
}
