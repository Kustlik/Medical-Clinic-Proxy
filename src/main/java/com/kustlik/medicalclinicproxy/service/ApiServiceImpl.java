package com.kustlik.medicalclinicproxy.service;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientCreationDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTOCache;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTOListCache;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitAssignmentDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitDTO;
import com.kustlik.medicalclinicproxy.remote.MedicalClinicClient;
import com.kustlik.medicalclinicproxy.repository.PatientDTOCacheRepository;
import com.kustlik.medicalclinicproxy.repository.PatientDTOListCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private static final String CACHE_ID = "allPatients";
    private final MedicalClinicClient medicalClinicClient;
    private final PatientDTOCacheRepository patientDTOCacheRepository;
    private final PatientDTOListCacheRepository patientDTOListCacheRepo;

    @Override
    public List<PatientDTO> getPatients(LocalDate visitDate, Pageable pageable) {
        Optional<PatientDTOListCache> cachedPatients = patientDTOListCacheRepo.findById(CACHE_ID);
        if (cachedPatients.isPresent()) {
            return cachedPatients.get().getPatientsDTO();
        }
        List<PatientDTO> patients = medicalClinicClient.getPatients(visitDate, pageable);
        patientDTOListCacheRepo.insert(PatientDTOListCache.builder()
                .id(CACHE_ID)
                .patientsDTO(patients)
                .build());
        return patients;
    }

    @Override
    public PatientDTO getPatient(Long id) {
        String cachedId = id.toString();
        Optional<PatientDTOCache> cachedResponse = patientDTOCacheRepository.findById(cachedId);
        if (cachedResponse.isPresent()) {
            return cachedResponse.get().getPatientDTO();
        }
        PatientDTO patient = medicalClinicClient.getPatient(id);
        patientDTOCacheRepository.insert(PatientDTOCache.builder()
                .id(cachedId)
                .patientDTO(patient)
                .build());
        return patient;
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
