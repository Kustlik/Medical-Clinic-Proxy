package com.kustlik.medicalclinicproxy.model.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients_dto_cache")
public class PatientDTOCache {
    @Id
    private String id;
    private PatientDTO patientDTO;
}