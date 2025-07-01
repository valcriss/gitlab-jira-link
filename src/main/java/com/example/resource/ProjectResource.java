package com.example.resource;

import com.example.model.ProjectMapping;
import com.example.service.ProjectService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    @Inject
    ProjectService service;

    @GET
    public List<ProjectMapping> list() {
        return service.list();
    }

    @POST
    public ProjectMapping add(ProjectMapping mapping) {
        return service.add(new ProjectMapping(mapping.getGitlabProjectId(), mapping.getJiraProjectKey()));
    }
}
