package com.example.searchengine.repositories;
import com.example.searchengine.entities.InvertedIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvertedIndexRepository extends JpaRepository<InvertedIndex, Long> {

    List<InvertedIndex> findByWordOrderByCountDesc(String word);
}