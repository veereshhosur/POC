package com.lbg.integrator.api.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbg.integrator.api.dto.request.AccountsRequest;
import com.lbg.integrator.api.dto.request.SourceInfo;
import com.lbg.integrator.api.dto.response.LbgBankDetails;
import com.lbg.integrator.api.dto.response.LbgIntegrationResponse;
import com.lbg.integrator.api.entity.IntegratorRequest;
import com.lbg.integrator.api.service.AccountService;
import com.lbg.integrator.api.service.IntegratorService;
import com.lbg.integrator.api.util.UniqueIdGenerator;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AccountsController {

    @Autowired
    IntegratorService integratorService;

    @Autowired
    AccountService accountService;
    @PostMapping(("v1/on-boarding/customers/{integratorId}/accounts"))
    public ResponseEntity createCustomerAccount(@RequestBody AccountsRequest request,
                                                @PathVariable String integratorId) throws JsonProcessingException {
        IntegratorRequest ir = integratorService.getIntegratorRequest(integratorId);
        String jsonSourceInfo = ir.getSourceInfo();
        SourceInfo sourceInfo =  new ObjectMapper().readValue(jsonSourceInfo, SourceInfo.class);
        LbgIntegrationResponse lbgr = buildLbgAccountResponse(sourceInfo,integratorId);

        return new ResponseEntity(lbgr, HttpStatus.CREATED);
    }

    private LbgIntegrationResponse buildLbgAccountResponse(SourceInfo sourceInfo, String integratorId) {
        LbgIntegrationResponse lr = new LbgIntegrationResponse();
        LbgBankDetails lbgBankDetails = new LbgBankDetails();
        boolean appStatus = accountService.orchestrateAccountCreate(integratorId);
        if(appStatus) {
            lbgBankDetails.setAccountNumber(UniqueIdGenerator.getUniqueIngestionId(new Date()));
            lbgBankDetails.setInternetId(UniqueIdGenerator.getUniqueIngestionId(new Date()));
            lr.setLbgAccountDetails(lbgBankDetails);
            lr.setCustomerAugmentInfo(sourceInfo.getCustomerAugmentInfo());
            lr.setSourceBankDetails(sourceInfo.getSourceBankInfo());
        }
        return lr;
    }
}
