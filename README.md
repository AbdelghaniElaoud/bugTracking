# Bug Tracking

**File a bug, attach the proof, and keep it on the project it belongs to.**

![Java 17](https://img.shields.io/badge/Java-17-orange)
![Spring Boot 3.1.2](https://img.shields.io/badge/Spring%20Boot-3.1.2-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-JPA-blue)

A small tracker you run yourself. Create a project, write the bug, set how bad it is, and drop in a screenshot or a log. The list, the details, and the file download stay in one Bootstrap app.

```
localhost:8080
      |
      +-- Project  (name, description)
      |
      +-- Bug  (title, description, priority, file)
                |
                +-- MySQL          the record
                +-- a local folder the attachment
```

## Why this exists

A bug report in a chat thread loses the screenshot. A shared spreadsheet loses the file that proves it. A full hosted tracker is a lot of product when you need projects, a priority, and the attachment next to the title.

Bug Tracking is that board, on your machine. Spring Boot serves the pages, MySQL keeps the projects and the bugs, and each upload lands in a folder you choose.

## Quick start

Java 17 and a local MySQL server. The Maven wrapper is in the repo.

```bash
git clone https://github.com/AbdelghaniElaoud/bugTracking.git && cd bugTracking
```

Point `src/main/resources/application.properties` at your own database URL, username, and password. Hibernate is set to `update`, so it creates the tables in that database. In `BugController`, set `UPLOAD_DIR` to a folder on your machine. That is where attachments are saved.

```bash
./mvnw spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080).

1. **Create Project.** Name it and describe it.
2. **Create Bug.** Title, description, a priority from Low to Emergency, the project, and a file.
3. **View Bugs.** Open the row, download the attachment, or update the title, the priority, and the file.

The home page also links the project list. Sign-in is a page only: Spring Security is not turned on, so these screens are open on the machine that runs the app.

## What you get

- A project list, so every bug hangs off the product it broke.
- A bug form with five priorities: Low, Medium, High, Critical, Emergency.
- The screenshot or log stored beside the bug, then downloaded from the list or the details page.
- An update screen for the title, the description, the priority, the project, and a replacement file.
- Tables created from the entities. No separate schema script.
- Uploads up to the 1 GB limit set in `application.properties`.

Each saved file goes in a timestamped folder, under a unique name, and the path is stored on the bug.

## Make it yours

The app is a standard Spring Boot project (`com.example.bugreporting`, artifact `BugReporting`). Controllers live under `src/main/java`, pages under `src/main/resources/templates`. Fork it, wire the login, or add a status field, and open a pull request.

If this replaced a spreadsheet for you, [star Bug Tracking](https://github.com/AbdelghaniElaoud/bugTracking). [Star history](https://star-history.com/#AbdelghaniElaoud/bugTracking&Date).

The repository does not ship a license file yet.
