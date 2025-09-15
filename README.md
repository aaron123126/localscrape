# LocalScape

A tool to create a local, browsable copy of a website.

## Features

*   **Web Scraper:** A resilient tool that crawls a website to discover all its URLs.
*   **Content Downloader:** A parallel tool that downloads the content of the discovered URLs.
*   **High Compression:** Downloaded content is compressed using Gzip to save space.
*   **Local Web Server:** A Node.js server that allows you to browse the downloaded website locally in your browser.
*   **Resilient:** The scraper and downloader can be stopped and restarted without losing progress.

## Prerequisites

To run this project, you will need the following software installed on your system:

*   **Java:** Version 11 or higher.
*   **Apache Maven:** To build the Java projects.
*   **Node.js:** To run the web server.

## How to Run

### 1. The Scraper (Java)

The scraper is responsible for finding all the URLs of a website.

1.  **Build the project:**
    ```bash
    cd scraper
    mvn package
    ```
2.  **Run the scraper:**
    ```bash
    java -jar target/scraper-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
3.  When you run it for the first time, it will ask you for a seed URL to start scraping.

### 2. The Downloader (Java)

The downloader runs in parallel to the scraper and downloads the content of the URLs found by the scraper.

1.  **Build the project:**
    ```bash
    cd downloader
    mvn package
    ```
2.  **Run the downloader:**
    ```bash
    java -jar target/downloader-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

### 3. The Server (Node.js)

The server serves the downloaded content.

1.  **Install dependencies:**
    ```bash
    cd server
    npm install
    ```
2.  **Run the server:**
    ```bash
    node server.js
    ```
3.  You can then open your browser and navigate to `http://localhost:3000` to browse the local copy of the website.

## Current Project Status

*   **Scraper & Downloader:** The code for the scraper and downloader has been written. However, there is a known issue where the `mvn` command is not found, which prevents them from being built and run. To resolve this, please ensure that Apache Maven is installed and that its `bin` directory is in your system's `PATH`.
*   **Server:** The Node.js server has been fully implemented and is ready to be used. Once the scraper and downloader have run and there is content in the `content` directory, the server will be able to serve it.
