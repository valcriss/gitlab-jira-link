package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/issues")
@Produces(MediaType.APPLICATION_JSON)
public class IssueResource {

    @Inject
    IssueService service;

    @GET
    public List<IssueLink> list() {
        return service.list();
    }
}
