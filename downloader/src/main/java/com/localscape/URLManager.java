package com.localscape;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class URLManager {
    private final Queue<String> toVisit = new LinkedList<>();
    private final Set<String> visited = new HashSet<>();
    private final String stateFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public URLManager(String stateFile) {
        this.stateFile = stateFile;
        loadState();
    }

    public synchronized void addUrl(String url) {
        if (url != null && !url.isEmpty() && !visited.contains(url) && !toVisit.contains(url)) {
            toVisit.add(url);
        }
    }

    public synchronized String getNextUrl() {
        if (toVisit.isEmpty()) {
            return null;
        }
        String url = toVisit.poll();
        visited.add(url);
        return url;
    }

    public synchronized boolean hasNextUrl() {
        return !toVisit.isEmpty();
    }

    @SuppressWarnings("unchecked")
    private void loadState() {
        File file = new File(stateFile);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<URLManagerState>() {}.getType();
                URLManagerState state = gson.fromJson(reader, type);
                if (state != null) {
                    toVisit.addAll(state.toVisit);
                    visited.addAll(state.visited);
                    System.out.println("Loaded " + toVisit.size() + " URLs to visit and " + visited.size() + " visited URLs.");
                }
            } catch (IOException e) {
                System.err.println("Error loading state: " + e.getMessage());
            }
        }
    }

    public synchronized void saveState() {
        try (FileWriter writer = new FileWriter(stateFile)) {
            URLManagerState state = new URLManagerState(new LinkedList<>(toVisit), new HashSet<>(visited));
            gson.toJson(state, writer);
            System.out.println("Saved " + toVisit.size() + " URLs to visit and " + visited.size() + " visited URLs.");
        } catch (IOException e) {
            System.err.println("Error saving state: " + e.getMessage());
        }
    }

    // Inner class to hold the state for JSON serialization
    private static class URLManagerState {
        private final Queue<String> toVisit;
        private final Set<String> visited;

        public URLManagerState(Queue<String> toVisit, Set<String> visited) {
            this.toVisit = toVisit;
            this.visited = visited;
        }
    }
}
