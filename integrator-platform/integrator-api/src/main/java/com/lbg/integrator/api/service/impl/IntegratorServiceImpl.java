package com.lbg.integrator.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbg.integrator.api.dto.request.SourceInfo;
import com.lbg.integrator.api.entity.IntegratorRequest;
import com.lbg.integrator.api.repository.IntegratorRequestRepository;
import com.lbg.integrator.api.service.IntegratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IntegratorServiceImpl implements IntegratorService {

    @Autowired
    private IntegratorRequestRepository integratorRequestRepository;
    @Override
    public IntegratorRequest saveIntegratorRequest(SourceInfo sourceInfo) throws JsonProcessingException {
        IntegratorRequest ir = new IntegratorRequest();
        String requestorID = UUID.randomUUID().toString();
        ir.setRequestorId(requestorID);
        String sourceInfoJsonString =  new ObjectMapper().writeValueAsString(sourceInfo);
        ir.setSourceInfo(sourceInfoJsonString);
        IntegratorRequest request = (IntegratorRequest) this.integratorRequestRepository.save(ir);
        return request;
    }

    @Override
    public IntegratorRequest updateKycStatus(String kycStatus, String integratorId) {
        IntegratorRequest integRequest = (IntegratorRequest) this.integratorRequestRepository.getReferenceById(integratorId);
        integRequest.setKycStatus(kycStatus);
        IntegratorRequest request = (IntegratorRequest) this.integratorRequestRepository.save(integRequest);
        return integRequest;
    }

    @Override
    public IntegratorRequest getIntegratorRequest(String integratorId) {
        IntegratorRequest request = (IntegratorRequest) this.integratorRequestRepository.getReferenceById(integratorId);
        return request;
    }
}
