package com.kustlik.medicalclinicproxy.remote;

import com.kustlik.medicalclinicproxy.model.dto.patient.PatientCreationDTO;
import com.kustlik.medicalclinicproxy.model.dto.patient.PatientDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitAssignmentDTO;
import com.kustlik.medicalclinicproxy.model.dto.visit.VisitDTO;
import com.kustlik.medicalclinicproxy.remote.configuration.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@FeignClient(
        name = "MedicalClinicClient",
        url = "${feign.client.Medical-Clinic-Client.url}",
        configuration = ClientConfiguration.class
)
public interface MedicalClinicClient {

    @GetMapping("patients/id/{patientId}")
    PatientDTO getPatient(@PathVariable("patientId") Long patientId);

    @GetMapping("patients")
    List<PatientDTO> getPatients(@RequestParam(required = false) LocalDate visitDate, Pageable pageable);

    @GetMapping("visits/patient/{patientId}")
    List<VisitDTO> getVisitsByPatient(@PathVariable("patientId") Long patientID);

    @PostMapping("patients")
    @ResponseStatus(HttpStatus.CREATED)
    PatientDTO createPatient(@RequestBody PatientCreationDTO patientDTO);

    @PostMapping("visits/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    VisitDTO createVisit(@RequestBody VisitAssignmentDTO visitDTO, @PathVariable("doctorId") Long doctorID);

    @PatchMapping("visits/patient/{patientId}")
    VisitDTO assignVisitToPatient(@PathVariable("patientId") Long patientID, @RequestBody Long visitID);
}
