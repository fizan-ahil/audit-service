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
        log.info("POST /api/v1/records — Creating record with title: {}", request.getTitle());
        RecordResponse response = recordService.createRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/v1/records — Fetch all records.
     */
    @GetMapping
    public ResponseEntity<List<RecordResponse>> getAllRecords() {
        log.info("GET /api/v1/records — Fetching all records");
        List<RecordResponse> records = recordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    /**
     * GET /api/v1/records/{id} — Fetch a record by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecordResponse> getRecordById(@PathVariable Long id) {
        log.info("GET /api/v1/records/{} — Fetching record by id", id);
        RecordResponse response = recordService.getRecordById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/records/count — Get total number of records.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countRecords() {
        log.info("GET /api/v1/records/count — Counting records");
        long count = recordService.countRecords();
        return ResponseEntity.ok(count);
    }

    /**
     * GET /api/v1/records/status/{status} — Fetch records by status.
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<RecordResponse>> getRecordsByStatus(@PathVariable String status) {
        log.info("GET /api/v1/records/status/{} — Fetching records by status", status);
        List<RecordResponse> records = recordService.getRecordsByStatus(status);
        return ResponseEntity.ok(records);
    }
}