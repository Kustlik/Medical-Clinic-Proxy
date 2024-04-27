package com.kustlik.medicalclinicproxy.service.scheduler;

import com.kustlik.medicalclinicproxy.repository.PatientDTOCacheRepository;
import com.kustlik.medicalclinicproxy.repository.PatientDTOListCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheScheduler {

    private final PatientDTOCacheRepository patientDTOCacheRepository;
    private final PatientDTOListCacheRepository patientDTOListCacheRepo;
    @Scheduled(fixedDelay = 300000, initialDelay = 300000)
    public void scheduleClearCache() {
        patientDTOCacheRepository.deleteAll();
        patientDTOListCacheRepo.deleteAll();
    }
}