package com.gitlabjiralink.api;

import java.time.Instant;

public record LogEntry(Instant timestamp, String message) {}
