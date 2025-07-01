package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.Instant;

@Entity
public class LogEntry extends PanacheEntity {
    public Instant timestamp;
    public String message;
}
