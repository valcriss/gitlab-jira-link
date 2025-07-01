package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.APIResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Path("/api/logs")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Logs", description = "Access synchronization logs")
public class LogResource {

    @Inject
    LogService service;

    @GET
    @Operation(summary = "List logs", description = "Return synchronization log entries")
    @APIResponse(responseCode = "200", description = "List of log entries")
    public List<LogEntry> list() {
        return service.list();
    }
}
