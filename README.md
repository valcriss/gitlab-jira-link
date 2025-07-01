# GitLab JIRA Link

This project synchronizes issues between GitLab and JIRA using a Quarkus backend. It exposes a REST API to manage project mappings and synchronization logs.

The project structure follows a standard Quarkus application layout and requires Java 21 and Maven.

## Current API

- `GET /api/projects` – list configured project mappings
- `POST /api/projects` – add a new project mapping
