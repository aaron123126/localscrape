package com.localscape;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class Downloader {

    private final URLManager urlManager;
    private final String contentDir = "content";

    public Downloader(String stateFile) {
        this.urlManager = new URLManager(stateFile);
        try {
            Files.createDirectories(Paths.get(contentDir));
        } catch (IOException e) {
            System.err.println("Could not create content directory: " + e.getMessage());
        }
    }

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(urlManager::saveState));

        while (true) {
            String url = urlManager.getNextUrl();
            if (url == null) {
                try {
                    // Wait for more URLs to be added by the scraper
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                continue;
            }

            System.out.println("Downloading: " + url);

            try {
                Connection.Response response = Jsoup.connect(url).ignoreContentType(true).execute();
                byte[] body = response.bodyAsBytes();
                String contentType = response.contentType();

                Path path = getPathFromUrl(url);
                if (path == null) continue;

                Files.createDirectories(path.getParent());

                // Save content gzipped
                try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(path.toString() + ".gz"))) {
                    out.write(body);
                }

                // Save metadata (content type)
                try (FileWriter writer = new FileWriter(path.toString() + ".meta")) {
                    writer.write(contentType);
                }

                Thread.sleep(1000);

            } catch (IOException e) {
                System.err.println("Error downloading " + url + ": " + e.getMessage());
            } catch (InterruptedException e) {
                System.err.println("Downloader was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private Path getPathFromUrl(String urlString) {
        try {
            URI uri = new URI(urlString);
            String host = uri.getHost();
            String path = uri.getPath();
            if (host == null) {
                return null;
            }
            if (path.isEmpty() || path.equals("/")) {
                path = "/index.html";
            }
            return Paths.get(contentDir, host, path);
        } catch (URISyntaxException e) {
            System.err.println("Invalid URL syntax: " + urlString);
            return null;
        }
    }

    public static void main(String[] args) {
        Downloader downloader = new Downloader("url_manager_state.json");
        downloader.start();
    }
}
