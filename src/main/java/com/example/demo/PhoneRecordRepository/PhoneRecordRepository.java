package com.example.demo.PhoneRecordRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.PhoneRecord.PhoneRecord;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRecordRepository extends JpaRepository<PhoneRecord,String> {
    List<PhoneRecord> findAllByPhoneNumber(String number);
    List<PhoneRecord> findAllByDurationGreaterThan(int duration);
    Optional<PhoneRecord> findByPhoneNumber(String number);

    
}
