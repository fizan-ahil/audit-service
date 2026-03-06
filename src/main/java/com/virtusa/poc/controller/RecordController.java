package com.virtusa.poc.controller;

import com.virtusa.poc.dto.request.RecordRequest;
import com.virtusa.poc.dto.response.RecordResponse;
import com.virtusa.poc.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    /**
     * POST /api/v1/records — Create a new record.
     */
    @PostMapping
    public ResponseEntity<RecordResponse> createRecord(@Valid @RequestBody RecordRequest request) {
        log.info("API: Received request to CREATE a record with title: {}", request.getTitle());
        RecordResponse response = recordService.createRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/v1/records — Fetch all records.
     */
    @GetMapping
    public ResponseEntity<List<RecordResponse>> getAllRecords() {
        log.info("API: Fetching ALL records from database");
        List<RecordResponse> records = recordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    /**
     * GET /api/v1/records/{id} — Fetch a record by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecordResponse> getRecordById(@PathVariable Long id) {
        log.info("API: Fetching record with ID: {}", id);
        RecordResponse response = recordService.getRecordById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/v1/records/{id} — Update an existing record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecordResponse> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody RecordRequest request) {
        log.info("API: Updating details for record ID: {}", id);
        RecordResponse response = recordService.updateRecord(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/v1/records/{id} — Delete record by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        log.info("API: Deleting record with ID: {}", id);
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/records/search?title=...
     * Purpose: Find records whose titles contain a specific word.
     */
    @GetMapping("/search")
    public ResponseEntity<List<RecordResponse>> searchByTitle(@RequestParam String title) {
        log.info("API: Searching for records containing title: {}", title);
        List<RecordResponse> results = recordService.searchByTitle(title);
        return ResponseEntity.ok(results);
    }

    /**
     * DELETE /api/v1/records/delete-by-title?title=...
     * Purpose: Remove records that match a specific title exactly.
     */
    @DeleteMapping("/delete-by-title")
    public ResponseEntity<Void> deleteByTitle(@RequestParam String title) {
        log.info("API: Request to DELETE all records with exact title: {}", title);
        recordService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }
}