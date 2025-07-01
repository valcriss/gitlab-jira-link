package com.gitlabjiralink.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class IssueServiceTest {

    @Inject
    IssueService service;
    @Inject
    IssueRepository repository;

    @Test
    void testAddAndList() {
        assertEquals(0, repository.count());
        IssueLink link = new IssueLink();
        link.gitlabIssueId = 1L;
        link.jiraIssueKey = "J-1";
        service.add(link);
        assertEquals(1, repository.count());
        assertEquals(1, service.list().size());
    }
}
