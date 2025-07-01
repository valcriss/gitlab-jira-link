package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LogRepository implements PanacheRepository<LogEntry> {
}
