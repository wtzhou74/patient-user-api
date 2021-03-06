package gov.samhsa.c2s.patientuser.infrastructure;

import gov.samhsa.c2s.patientuser.config.ClientCredentialsAwareOAuth2FeignClientConfig;
import gov.samhsa.c2s.patientuser.config.TokenRelayTypeHeader;
import gov.samhsa.c2s.patientuser.infrastructure.dto.PatientDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "phr", configuration = ClientCredentialsAwareOAuth2FeignClientConfig.class)
public interface PhrServiceClientCredentials {

    @RequestMapping(value = "/patients/{patientId}/profile", method = RequestMethod.GET, headers = TokenRelayTypeHeader.CLIENT_CREDENTIALS_HEADER_VALUE)
    PatientDto findPatientProfileById(@PathVariable("patientId") Long patientId);
}