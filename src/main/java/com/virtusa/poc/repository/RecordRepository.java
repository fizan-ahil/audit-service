package com.virtusa.poc.repository;
import java.util.List;
import com.virtusa.poc.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByStatus(String status);
}
