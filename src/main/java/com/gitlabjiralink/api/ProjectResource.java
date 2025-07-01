package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Projects", description = "Manage GitLab/JIRA project mappings")
public class ProjectResource {

    @Inject
    ProjectService service;

    @GET
    @Operation(summary = "List mappings", description = "Return all configured project mappings")
    @APIResponse(responseCode = "200", description = "List of project mappings")
    public List<ProjectMapping> list() {
        return service.list();
    }

    @POST
    @Operation(summary = "Add mapping", description = "Create a new GitLab/JIRA project mapping")
    @APIResponse(responseCode = "201", description = "Mapping created",
            content = @Content(schema = @Schema(implementation = ProjectMapping.class)))
    public Response add(@RequestBody(description = "Mapping to create", required = true) ProjectMapping mapping) {
        ProjectMapping stored = service.add(mapping);
        return Response.created(URI.create("/api/projects/" + stored.id)).entity(stored).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Update mapping", description = "Update an existing project mapping")
    @APIResponse(responseCode = "200", description = "Updated mapping",
            content = @Content(schema = @Schema(implementation = ProjectMapping.class)))
    @APIResponse(responseCode = "404", description = "Mapping not found")
    public Response update(@Parameter(description = "Mapping identifier") @PathParam("id") Long id,
                           @RequestBody(description = "Updated mapping", required = true) ProjectMapping mapping) {
        ProjectMapping updated = service.update(id, mapping);
        if (updated == null) {
            throw new NotFoundException();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete mapping", description = "Delete an existing project mapping")
    @APIResponse(responseCode = "204", description = "Mapping deleted")
    @APIResponse(responseCode = "404", description = "Mapping not found")
    public Response delete(@Parameter(description = "Mapping identifier") @PathParam("id") Long id) {
        if (!service.delete(id)) {
            throw new NotFoundException();
        }
        return Response.noContent().build();
    }
}
