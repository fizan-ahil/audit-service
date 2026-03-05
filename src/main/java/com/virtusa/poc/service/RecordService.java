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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;

    @Transactional
    public RecordResponse createRecord(RecordRequest request) {
        log.info("Creating new record with title: {}", request.getTitle());

        Record record = Record.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : "ACTIVE")
                .build();

        Record savedRecord = recordRepository.save(record);
        log.info("Record created successfully with id: {}", savedRecord.getId());

        return RecordResponse.fromEntity(savedRecord);
    }

    public List<RecordResponse> getAllRecords() {
        log.info("Fetching all records");

        List<RecordResponse> records = recordRepository.findAll().stream()
                .map(RecordResponse::fromEntity)
                .toList();

        log.info("Found {} records", records.size());
        return records;
    }

    public RecordResponse getRecordById(Long id) {
        log.info("Fetching record with id: {}", id);

        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", "id", id));

        return RecordResponse.fromEntity(record);
    }

    @Transactional
    public RecordResponse updateRecord(Long id, RecordRequest request) {
        log.info("Updating record with id: {}", id);

        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", "id", id));

        record.setTitle(request.getTitle());
        record.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            record.setStatus(request.getStatus());
        }

        Record updatedRecord = recordRepository.save(record);
        log.info("Record updated successfully with id: {}", updatedRecord.getId());

        return RecordResponse.fromEntity(updatedRecord);
    }

    @Transactional
    public void deleteRecord(Long id) {
        log.info("Deleting record with id: {}", id);

        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", "id", id));

        recordRepository.delete(record);
        log.info("Record deleted successfully with id: {}", id);
    }
}
