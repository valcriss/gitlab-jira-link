package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    @Inject
    ProjectRepository repository;

    public List<ProjectMapping> list() {
        return repository.listAll();
    }

    @Transactional
    public ProjectMapping add(ProjectMapping mapping) {
        repository.persist(mapping);
        return mapping;
    }

    @Transactional
    public ProjectMapping update(Long id, ProjectMapping mapping) {
        ProjectMapping existing = repository.findById(id);
        if (existing == null) {
            return null;
        }
        existing.gitlabProject = mapping.gitlabProject;
        existing.jiraProject = mapping.jiraProject;
        return existing;
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    public ProjectMapping find(Long id) {
        return repository.findById(id);
    }
}
