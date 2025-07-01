package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/api/issues")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Issues", description = "View issue links")
public class IssueResource {

    @Inject
    IssueService service;

    @GET
    @Operation(summary = "List issues", description = "Return all linked issues")
    @APIResponse(responseCode = "200", description = "List of issue links")
    public List<IssueLink> list() {
        return service.list();
    }
}
