package com.kustlik.medicalclinicproxy.repository;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTOListCache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientDTOListCacheRepository extends MongoRepository<PatientDTOListCache, String> {
}
