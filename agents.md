# AGENTS.md

## Project Overview
This project is a synchronization bridge between a self-hosted GitLab instance (only accessible from a local network) and Atlassian JIRA Cloud. The goal is to ensure seamless bidirectional synchronization between GitLab issues and JIRA tickets.

The project is built using **Quarkus**, with a PostgreSQL database for persistent state, and exposes a REST API to allow frontend interfaces to manage and monitor synchronization settings.

---

## Functional Objectives

### GitLab to JIRA Synchronization (Triggered via GitLab Webhooks)
- When a GitLab issue is created, a corresponding JIRA "Bug" issue is created.
- When a comment is added to a GitLab issue, it is also added to the corresponding JIRA issue.
- When a GitLab issue is closed:
  - A comment is added to the related JIRA issue.
  - The JIRA ticket is transitioned to a "Done" state (if permitted by the workflow).
- If a GitLab issue is marked as invalid or deleted:
  - A comment is added to the JIRA ticket.
  - The ticket is transitioned to a closed state if allowed.

### JIRA to GitLab Synchronization (Performed via Periodic Polling)
- When a comment is added to a JIRA issue, it is added as an internal comment in the corresponding GitLab issue.
- When a JIRA issue is transitioned to "Done":
  - A comment is added to the GitLab issue.
  - A customizable label (e.g., `jira-synced`) is applied to the GitLab issue.
- If a JIRA issue is invalidated or deleted:
  - An internal comment is added to the corresponding GitLab issue.

---

## Additional Business Rules
- No synchronization is done for title or description updates.
- A link to the related issue is inserted in the initial description/comment.
- Multiple GitLab issues linked to a single JIRA issue (or vice versa) is **not supported**.
- Each GitLab ↔ JIRA project mapping is stored and configurable.

---

## Technical Stack
- **Backend:** Quarkus (Java)
- **Database:** PostgreSQL
- **REST API:** Exposes endpoints for:
  - Managing project mappings
  - Viewing synchronization logs
  - Triggering manual resync
- **JIRA Polling:** Implemented using `@Scheduled` jobs to poll changes regularly (e.g., every 2 minutes)
- **GitLab Integration:** Webhook endpoint to handle incoming events (issue created, commented, closed)
- **Security:** Runs in the same local network as the GitLab server; authenticated API calls to both GitLab and JIRA

---

## Persistence
- Table for GitLab ↔ JIRA project mappings
- Table for issue links (GitLab issue ID ↔ JIRA issue key)
- Log table to track all sync operations with timestamps and status

---

## REST API Endpoints (Planned)
| Method | Endpoint               | Description                             |
|--------|------------------------|-----------------------------------------|
| GET    | `/api/projects`        | List linked GitLab ↔ JIRA projects      |
| POST   | `/api/projects`        | Add a new project mapping               |
| PUT    | `/api/projects/{id}`   | Update an existing mapping              |
| DELETE | `/api/projects/{id}`   | Remove a mapping                        |
| GET    | `/api/issues`          | List currently linked issues            |
| GET    | `/api/logs`            | View synchronization logs               |

---

## Project Requirements
- All commits **must** be validated with a linter corresponding to the project language (Java).
  - No warnings or errors must be present after linting.
- **Tests must be run by running "mvn test" command**
- **Test coverage must be 100%** .
  - Line coverage
  - Branch coverage
  - Method coverage
- **Builds must pass successfully**:
  - The application must compile, run scheduled tasks, and expose the REST API without runtime errors.

This ensures stability, reliability, and ease of maintainability for both contributors and operations.

