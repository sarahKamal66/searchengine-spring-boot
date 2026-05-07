package com.example.searchengine.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "inverted_index")
public class InvertedIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private int count;
    private String word;

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
