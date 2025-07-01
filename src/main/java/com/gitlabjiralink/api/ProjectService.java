package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class ProjectService {
    private final Map<Long, ProjectMapping> projects = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<ProjectMapping> list() {
        return new ArrayList<>(projects.values());
    }

    public ProjectMapping add(ProjectMapping mapping) {
        long id = counter.getAndIncrement();
        ProjectMapping stored = new ProjectMapping(id, mapping.gitlabProject(), mapping.jiraProject());
        projects.put(id, stored);
        return stored;
    }

    public ProjectMapping update(Long id, ProjectMapping mapping) {
        if (!projects.containsKey(id)) {
            return null;
        }
        ProjectMapping updated = new ProjectMapping(id, mapping.gitlabProject(), mapping.jiraProject());
        projects.put(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        return projects.remove(id) != null;
    }

    public ProjectMapping find(Long id) {
        return projects.get(id);
    }
}
