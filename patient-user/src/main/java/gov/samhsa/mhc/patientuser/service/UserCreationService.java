package gov.samhsa.mhc.patientuser.service;

import gov.samhsa.mhc.patientuser.service.dto.PatientDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by burcak.ulug on 3/2/2016.
 */
public interface UserCreationService {
    @Transactional
    String initiateUserCreation(PatientDto patientDto);
}