package com.gitlabjiralink.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
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
    public Response add(ProjectMapping mapping) {
        ProjectMapping stored = service.add(mapping);
        return Response.created(URI.create("/api/projects/" + stored.id())).entity(stored).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, ProjectMapping mapping) {
        ProjectMapping updated = service.update(id, mapping);
        if (updated == null) {
            throw new NotFoundException();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!service.delete(id)) {
            throw new NotFoundException();
        }
        return Response.noContent().build();
    }
}
