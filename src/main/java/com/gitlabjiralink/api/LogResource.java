package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/logs")
@Produces(MediaType.APPLICATION_JSON)
public class LogResource {

    @Inject
    LogService service;

    @GET
    public List<LogEntry> list() {
        return service.list();
    }
}
