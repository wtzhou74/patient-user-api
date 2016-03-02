package gov.samhsa.mhc.patientuser.web;

import gov.samhsa.mhc.patientuser.service.EmailNotificationService;
import gov.samhsa.mhc.patientuser.service.PhrService;
import gov.samhsa.mhc.patientuser.service.UserCreationService;
import gov.samhsa.mhc.patientuser.service.dto.PatientDto;
import gov.samhsa.mhc.patientuser.service.dto.UserCreationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserCreationController {

    @Autowired
    private PhrService phrService;

    @Autowired
    private UserCreationService userCreationService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @RequestMapping(value = "/userCreation", method = RequestMethod.POST)
    public PatientDto initiateUserCreation(OAuth2Authentication authentication, @Valid @RequestBody UserCreationRequestDto userCreationRequest) {
        final PatientDto patientProfile = phrService.findPatientProfileById(authentication, userCreationRequest.getPatientId());
        final String emailToken = userCreationService.initiateUserCreation(patientProfile);
        emailNotificationService.sendEmailWithVerificationLink(emailToken, patientProfile);
        return patientProfile;
    }
}