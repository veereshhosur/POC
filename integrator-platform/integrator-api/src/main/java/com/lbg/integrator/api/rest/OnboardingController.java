package com.lbg.integrator.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbg.integrator.api.dto.request.KYC;
import com.lbg.integrator.api.dto.request.SourceInfo;
import com.lbg.integrator.api.dto.response.KYCResponse;
import com.lbg.integrator.api.entity.IntegratorRequest;
import com.lbg.integrator.api.service.IntegratorService;
import com.lbg.integrator.api.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardingController {

    @Autowired
    private IntegratorService integratorService;

    @Autowired
    private KYCService kycService;

    @PostMapping(value = "/v1/on-boarding/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createIntegratorRequest(@RequestBody SourceInfo request){
        try {
            IntegratorRequest ir = integratorService.saveIntegratorRequest(request);
            String jsonSourceInfo = ir.getSourceInfo();
            SourceInfo sourceInfo =  new ObjectMapper().readValue(jsonSourceInfo, SourceInfo.class);
            KYC kyc = sourceInfo.getCustomerKyc();

            KYCResponse response = buildResponse( kycService.validateKyc(kyc),kyc, ir.getRequestorId());
            integratorService.updateKycStatus(response.getKycStatus(),response.getIntegrationId() );
            return new ResponseEntity(response, HttpStatus.CREATED);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private KYCResponse buildResponse(boolean b, KYC kyc, String integrationId) {
        KYCResponse response = new KYCResponse();
//        response.setAadharDetails(kyc.getAadharDetails());
        response.setCountry(kyc.getCountry());
        response.setAddressDetails(kyc.getAddressDetails());
        response.setIntegrationId(integrationId);
        response.setKycStatus(b?"Approve":"Rejected");
        return response;
    }
}
