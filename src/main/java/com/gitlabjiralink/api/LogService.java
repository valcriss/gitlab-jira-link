package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class LogService {
    private final List<LogEntry> logs = new CopyOnWriteArrayList<>();

    public List<LogEntry> list() {
        return new ArrayList<>(logs);
    }

    public void log(String message) {
        logs.add(new LogEntry(Instant.now(), message));
    }
}
