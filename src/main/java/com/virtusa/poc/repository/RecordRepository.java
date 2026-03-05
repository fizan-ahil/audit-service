package com.virtusa.poc.repository;

import com.virtusa.poc.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Repository layer acts as the direct link to your PostgreSQL database.
 * It uses Spring Data JPA to automatically generate SQL queries.
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    /**
     * Partial Search: Used for the GET /search API.
     * It finds records where the title contains the search string (like SQL 'LIKE %word%').
     * 'IgnoreCase' ensures it finds 'Tech' even if you search for 'tech'.
     */
    List<Record> findByTitleContainingIgnoreCase(String title);

    /**
     * Exact Search: Used for the NEW Delete by Title logic.
     * This finds records that match the title exactly as stored in the database.
     * Spring Data JPA creates this query: SELECT * FROM records WHERE title = ?
     */
    List<Record> findByTitle(String title);
}