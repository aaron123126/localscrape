# Gemini Bootstrap Prompt: LocalScape Project

You are an AI assistant that has been brought in to continue the development of the "LocalScape" project. The previous AI has left you this file to get you up to speed. Your goal is to continue the project, following the established plan and conventions.

## Project Goal

The overall goal of the project is to create a set of tools (a Java scraper/downloader and a Node.js server) to produce and serve a complete, local, and browsable copy of a website.

## File System State

The project is structured as follows:

```
C:\Users\aaron\Windows_Linux\Code\localscape\
├───README.md
├───.git\
├───downloader\
│   ├───pom.xml
│   └───src\
│       └───main\
│           └───java\
│               └───com\
│                   └───localscape\
│                       ├───Downloader.java
│                       └───URLManager.java
├───scraper\
│   ├───pom.xml
│   └───src\
│       └───main\
│           └───java\
│               └───com\
│                   └───localscape\
│                       ├───Scraper.java
│                       └───URLManager.java
└───server\
    ├───.gitignore
    ├───package-lock.json
    ├───package.json
    ├───server.js
    └───node_modules\
```

## Git Commit History

The following commits have been made to the project:

- docs: Rewrite README from user's perspective
- docs: Update README with detailed project status
- feat: Implement file decompression and serving in server
- feat: Initial implementation of the downloader
- feat: Initial setup of Node.js server
- feat: Initial implementation of the scraper
- Add README.md with project description

## Project Plan

The project is being developed in the following phases:

1.  [DONE] Implement the initial code for the Java scraper.
2.  [DONE] Implement the initial code for the Node.js server.
3.  [DONE] Implement the initial code for the Java downloader.
4.  [DONE] Enhance the Node.js server with file serving and decompression logic.
5.  [BLOCKED] Compile the `scraper` and `downloader` Java projects. This is blocked because the `mvn` command is not found.
6.  [TODO] Once the Maven issue is resolved, compile the Java projects.
7.  [TODO] Run the scraper to begin populating the URL queue.
8.  [TODO] Run the downloader to fetch and store website content.
9.  [TODO] Run the Node.js server and test the end-to-end functionality.

## User Instructions

The user has provided the following instructions and feedback:

*   **Commit all changes:** All created files and changes must be committed to Git as development progresses.
*   **User-centric README:** The `README.md` file should be written from the user's perspective and include clear instructions on how to run the project.
*   **The `mvn` issue:** The user is aware of the `mvn` command not being found and has instructed to "just go on". The `README.md` has been updated to explain this issue to the user.

Your task is to continue the development of the project, following the project plan and the user's instructions. The next step is to wait for the user to resolve the Maven issue. Once the issue is resolved, you should proceed with compiling and running the Java components.
