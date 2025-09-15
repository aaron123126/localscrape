package com.localscape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Scraper {

    private final URLManager urlManager;

    public Scraper(String stateFile) {
        this.urlManager = new URLManager(stateFile);
    }

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(urlManager::saveState));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (!urlManager.hasNextUrl()) {
                    System.out.print("No URLs to scrape. Please enter a seed URL: ");
                    String seedUrl = scanner.nextLine();
                    urlManager.addUrl(seedUrl);
                }

                String url = urlManager.getNextUrl();
                if (url == null) {
                    continue;
                }

                System.out.println("Scraping: " + url);

                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements links = doc.select("a[href]");

                    for (Element link : links) {
                        String absoluteUrl = link.absUrl("href");
                        urlManager.addUrl(absoluteUrl);
                    }

                    // Be a good citizen and don't hammer the server
                    Thread.sleep(1000);

                } catch (IOException e) {
                    System.err.println("Error scraping " + url + ": " + e.getMessage());
                } catch (InterruptedException e) {
                    System.err.println("Scraper was interrupted.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // The state file will be shared between the scraper and the downloader
        Scraper scraper = new Scraper("url_manager_state.json");
        scraper.start();
    }
}
