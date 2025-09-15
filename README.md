# LocalScape

A tool to create a local, browsable copy of a website.

This project consists of three components:

1.  **Scraper (Java):** A tool that crawls a website to find all its URLs.
2.  **Downloader (Java):** A tool that downloads the content of the URLs found by the scraper and compresses it.
3.  **Server (Node.js):** A web server that serves the downloaded content, allowing you to browse the local copy of the website.

## Project Status

### What's Added & Working

-   **Node.js Server:** The web server has been implemented using Node.js and Express. It is designed to serve content that has been downloaded and compressed by the other tools. It handles on-the-fly decompression of Gzipped content.
-   **Git Repository:** The entire project is under version control with Git. All progress has been committed.

### What's Added (But Not Working)

-   **Java Scraper:** The code for the scraper has been written. It's designed to be resilient, saving its state so it can be stopped and restarted.
-   **Java Downloader:** The code for the downloader has been written. It's designed to run in parallel to the scraper, download content, and compress it using Gzip.

### What's Not Working and How to Fix It

-   **Java Components Compilation:** The primary issue is that the Java-based scraper and downloader cannot be compiled or run.
-   **The Cause:** The `mvn` (Maven) command is not found by the system. This prevents the build process for the Java projects.
-   **The Solution:** To fix this, you need to:
    1.  **Install Apache Maven:** If you don't have it, download and install it from the official website.
    2.  **Configure the PATH variable:** Ensure that the `bin` directory of your Maven installation is included in your system's `PATH` environment variable. This will allow the `mvn` command to be executed from any directory.

Once the `mvn` command is available, I can proceed with compiling and running the scraper and downloader to complete the project.
