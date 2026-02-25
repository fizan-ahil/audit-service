package com.virtusa.poc.dto.response;

import com.virtusa.poc.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Factory method to convert a Record entity to a RecordResponse DTO.
     *
     * @param record the JPA entity
     * @return the response DTO
     */
    public static RecordResponse fromEntity(Record record) {
        return RecordResponse.builder()
                .id(record.getId())
                .title(record.getTitle())
                .description(record.getDescription())
                .status(record.getStatus())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getUpdatedAt())
                .build();
    }
}
