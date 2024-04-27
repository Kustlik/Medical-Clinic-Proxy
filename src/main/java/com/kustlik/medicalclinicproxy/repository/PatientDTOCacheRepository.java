package com.kustlik.medicalclinicproxy.repository;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTOCache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientDTOCacheRepository extends MongoRepository<PatientDTOCache, String> {
}
