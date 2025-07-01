package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class LogService {

    @Inject
    LogRepository repository;

    public List<LogEntry> list() {
        return repository.listAll();
    }

    @Transactional
    public void log(String message) {
        LogEntry entry = new LogEntry();
        entry.timestamp = Instant.now();
        entry.message = message;
        repository.persist(entry);
    }
}
