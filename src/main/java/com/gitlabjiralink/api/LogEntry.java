package com.gitlabjiralink.api;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.Instant;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Synchronization log entry")
public class LogEntry extends PanacheEntity {
    @Schema(description = "Time of the log entry")
    public Instant timestamp;
    @Schema(description = "Log message")
    public String message;
}
