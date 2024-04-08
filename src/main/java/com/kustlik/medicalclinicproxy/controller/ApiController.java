package com.kustlik.medicalclinicproxy.controller;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientCreationDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitAssignmentDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitDTO;
import com.kustlik.medicalclinicproxy.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/patients")
    public List<PatientDTO> getPatients(@RequestParam(required = false) LocalDate visitDate, Pageable pageable) {
        return apiService.getPatients(visitDate, pageable);
    }

    @GetMapping("/patients/{id}")
    public PatientDTO getPatient(@PathVariable Long id) {
        return apiService.getPatient(id);
    }

    @GetMapping("/patients/{patientId}/visits")
    public List<VisitDTO> getVisitsByPatient(@PathVariable("patientId") Long patientID) {
        return apiService.getVisitsByPatient(patientID);
    }

    @PostMapping("/patients")
    public PatientDTO createPatient(@RequestBody PatientCreationDTO patientDTO) {
        return apiService.createPatient(patientDTO);
    }

    @PatchMapping("/visits")
    public VisitDTO createAndAssignVisit(@RequestBody VisitAssignmentDTO visitAssignmentDTO) {
        return apiService.createAndAssignVisit(visitAssignmentDTO);
    }
}
