package com.kustlik.medicalclinicproxy.service;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientCreationDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitAssignmentDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitDTO;
import com.kustlik.medicalclinicproxy.remote.MedicalClinicClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final MedicalClinicClient medicalClinicClient;

    @Override
    public List<PatientDTO> getPatients(LocalDate visitDate, Pageable pageable) {
        return medicalClinicClient.getPatients(visitDate, pageable);
    }

    @Override
    public PatientDTO getPatient(Long id) {
        return medicalClinicClient.getPatient(id);
    }

    @Override
    public List<VisitDTO> getVisitsByPatient(Long patientID) {
        return medicalClinicClient.getVisitsByPatient(patientID);
    }

    @Override
    public PatientDTO createPatient(PatientCreationDTO patientDTO) {
        return medicalClinicClient.createPatient(patientDTO);
    }

    @Override
    public VisitDTO createAndAssignVisit(VisitAssignmentDTO visitDTO) {
        Long doctorId = visitDTO.getDoctorId();
        Long patientId = visitDTO.getPatientId();
        Long visitId = medicalClinicClient.createVisit(visitDTO, doctorId).getId();
        return medicalClinicClient.assignVisitToPatient(patientId, visitId);
    }
}
