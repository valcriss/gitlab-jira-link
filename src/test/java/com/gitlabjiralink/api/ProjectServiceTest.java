package com.gitlabjiralink.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProjectServiceTest {

    @Inject
    ProjectService service;
    @Inject
    ProjectRepository repository;

    @Test
    void testAddUpdateDelete() {
        assertEquals(0, repository.count());
        ProjectMapping mapping = new ProjectMapping();
        mapping.gitlabProject = "g1";
        mapping.jiraProject = "j1";
        ProjectMapping stored = service.add(mapping);
        assertNotNull(stored.id);
        assertEquals(1, repository.count());

        ProjectMapping update = new ProjectMapping();
        update.gitlabProject = "g2";
        update.jiraProject = "j2";
        ProjectMapping updated = service.update(stored.id, update);
        assertNotNull(updated);
        assertEquals("g2", updated.gitlabProject);

        assertTrue(service.delete(stored.id));
        assertEquals(0, repository.count());
    }

    @Test
    void testUpdateMissing() {
        ProjectMapping update = new ProjectMapping();
        update.gitlabProject = "x";
        update.jiraProject = "y";
        assertNull(service.update(999L, update));
    }
}
