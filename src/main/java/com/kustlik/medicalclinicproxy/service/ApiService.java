package com.kustlik.medicalclinicproxy.service;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientCreationDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitAssignmentDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ApiService {
    List<PatientDTO> getPatients(LocalDate visitDate, Pageable pageable);

    PatientDTO getPatient(Long id);

    List<VisitDTO> getVisitsByPatient(Long patientID);

    PatientDTO createPatient(PatientCreationDTO patientDTO);

    VisitDTO createAndAssignVisit(VisitAssignmentDTO visitDTO);
}
