package com.example.service;

import com.example.model.ProjectMapping;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class ProjectService {
    private final List<ProjectMapping> mappings = new CopyOnWriteArrayList<>();

    public List<ProjectMapping> list() {
        return Collections.unmodifiableList(mappings);
    }

    public ProjectMapping add(ProjectMapping mapping) {
        mappings.add(mapping);
        return mapping;
    }
}
