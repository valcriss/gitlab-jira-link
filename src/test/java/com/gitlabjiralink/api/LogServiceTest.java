package com.gitlabjiralink.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class LogServiceTest {

    @Inject
    LogService service;
    @Inject
    LogRepository repository;

    @Test
    void testLog() {
        assertEquals(0, repository.count());
        service.log("hello");
        assertEquals(1, repository.count());
        LogEntry entry = repository.listAll().get(0);
        assertEquals("hello", entry.message);
        assertNotNull(entry.timestamp);
    }
}
