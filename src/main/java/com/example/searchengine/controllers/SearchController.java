package com.example.searchengine.controllers;

import com.example.searchengine.entities.InvertedIndex;
import org.springframework.web.bind.annotation.*;

import com.example.searchengine.repositories.InvertedIndexRepository;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SearchController {

    private final InvertedIndexRepository repo;
    public SearchController(InvertedIndexRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/search")
    List<String> search(@RequestParam String word)
    {
        List<InvertedIndex> invertedIndexList=repo.findByWordOrderByCountDesc(word);
        List<String> urls=new ArrayList<>();

        for(InvertedIndex i:invertedIndexList)
        {
            urls.add(i.getUrl());
        }
        return urls;
    }

}
