package com.example.searchengine.services;

import com.example.searchengine.entities.InvertedIndex;
import com.example.searchengine.repositories.InvertedIndexRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

@Service
public class IndexBuilderService {

    private final InvertedIndexRepository repo;
    private final ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>> index
            = new ConcurrentHashMap<>();

    public IndexBuilderService(InvertedIndexRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void buildIndex() throws InterruptedException {
        File folder = new File("pages");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) return;

        int threads = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (File file : files) {
            executor.execute(() -> processFile(file));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);


        saveToDatabase();
    }

    private void processFile(File file) {

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {

            String firstLine = reader.readLine();
            if (firstLine == null) return;
            String url = firstLine.startsWith("URL: ") ? firstLine.substring(5).trim() : firstLine.trim();


            String line;
            while ((line = reader.readLine()) != null) {
                for (String word : line.split("\\s+")) {
                    word = word.toLowerCase().replaceAll("[^a-zأ-ي0-9]", "");
                    if (word.length() >= 2) {
                        index.computeIfAbsent(word, k -> new ConcurrentHashMap<>())
                                .computeIfAbsent(url, k -> new AtomicInteger(0))
                                .incrementAndGet();
                    }
                }
            }
        } catch (IOException ignored) {}
    }

    private void saveToDatabase() {
        index.forEach((word, urlMap) -> {
            urlMap.forEach((url, count) -> {
                InvertedIndex entry = new InvertedIndex();
                entry.setWord(word);
                entry.setUrl(url);
                entry.setCount(count.get());
                repo.save(entry);
            });
        });
    }

}
