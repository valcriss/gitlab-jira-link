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
        long before = repository.count();
        ProjectMapping mapping = new ProjectMapping();
        mapping.gitlabProject = "g1";
        mapping.jiraProject = "j1";
        ProjectMapping stored = service.add(mapping);
        assertNotNull(stored.id);
        assertEquals(before + 1, repository.count());

        ProjectMapping update = new ProjectMapping();
        update.gitlabProject = "g2";
        update.jiraProject = "j2";
        ProjectMapping updated = service.update(stored.id, update);
        assertNotNull(updated);
        assertEquals("g2", updated.gitlabProject);

        assertTrue(service.delete(stored.id));
        assertEquals(before, repository.count());
    }

    @Test
    void testUpdateMissing() {
        ProjectMapping update = new ProjectMapping();
        update.gitlabProject = "x";
        update.jiraProject = "y";
        assertNull(service.update(999L, update));
    }

    @Test
    void testFind() {
        ProjectMapping mapping = new ProjectMapping();
        mapping.gitlabProject = "g";
        mapping.jiraProject = "j";
        ProjectMapping stored = service.add(mapping);
        ProjectMapping found = service.find(stored.id);
        assertNotNull(found);
        assertEquals("g", found.gitlabProject);
        assertNull(service.find(999L));
    }
}
