package com.virtusa.poc.service;

import com.virtusa.poc.dto.request.RecordRequest;
import com.virtusa.poc.dto.response.RecordResponse;
import com.virtusa.poc.entity.Record;
import com.virtusa.poc.exception.ResourceNotFoundException;
import com.virtusa.poc.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that contains the business logic for managing Records.
 * It acts as the "Brain" between the Controller and the Repository.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Optimizes performance for read-only operations (GET)
public class RecordService {

    private final RecordRepository recordRepository;

    /**
     * Business logic to create a new record.
     * Maps the Request DTO to the Record Entity and saves it.
     */
    @Transactional // Required for writing data to PostgreSQL
    public RecordResponse createRecord(RecordRequest request) {
        log.info("Service: Creating record with title: {}", request.getTitle());

        // Using the Builder pattern to create a new Record entity
        Record record = Record.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : "ACTIVE")
                .build();

        Record savedRecord = recordRepository.save(record);
        log.info("Service: Record saved successfully with ID: {}", savedRecord.getId());

        return RecordResponse.fromEntity(savedRecord);
    }

    /**
     * Retrieves all records currently stored in the 'records' table.
     */
    public List<RecordResponse> getAllRecords() {
        log.info("Service: Fetching all records from database");
        return recordRepository.findAll().stream()
                .map(RecordResponse::fromEntity)
                .toList();
    }

    /**
     * Finds a single record by its ID. Throws an error if not found.
     */
    public RecordResponse getRecordById(Long id) {
        log.info("Service: Fetching record with ID: {}", id);
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", "id", id));
        return RecordResponse.fromEntity(record);
    }

    /**
     * Logic for updating an existing record's details.
     */
    @Transactional
    public RecordResponse updateRecord(Long id, RecordRequest request) {
        log.info("Service: Updating record with ID: {}", id);

        // 1. Find the record or throw an exception if it doesn't exist
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", "id", id));

        // 2. Map the new values from the request to the entity
        record.setTitle(request.getTitle());
        record.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            record.setStatus(request.getStatus());
        }

        // 3. Save the updated entity back to PostgreSQL
        Record updatedRecord = recordRepository.save(record);
        return RecordResponse.fromEntity(updatedRecord);
    }

    /**
     * Deletes a record by its unique ID.
     */
    @Transactional
    public void deleteRecord(Long id) {
        log.info("Service: Deleting record with ID: {}", id);
        if (!recordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Record", "id", id);
        }
        recordRepository.deleteById(id);
    }

    /**
     * Performs a partial, case-insensitive search by title.
     */
    public List<RecordResponse> searchByTitle(String title) {
        log.info("Service: Searching for records containing: {}", title);
        return recordRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(RecordResponse::fromEntity)
                .toList();
    }

    /**
     * NEW: Logic to delete records by an exact title.
     * This method finds all matches and removes them from the database.
     */
    @Transactional
    public void deleteByTitle(String title) {
        log.info("Service: Deleting all records with exact title: {}", title);

        // 1. Fetch records matching the exact title
        List<Record> recordsToDelete = recordRepository.findByTitle(title);

        if (recordsToDelete.isEmpty()) {
            log.warn("Service: No records found with title '{}' to delete", title);
            return;
        }

        // 2. Batch delete all found records
        recordRepository.deleteAll(recordsToDelete);
        log.info("Service: Successfully deleted {} record(s) with title '{}'", recordsToDelete.size(), title);
    }
}