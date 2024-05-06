package com.lbg.integrator.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lbg.integrator.api.dto.request.SourceInfo;
import com.lbg.integrator.api.entity.IntegratorRequest;

public interface IntegratorService {

    IntegratorRequest saveIntegratorRequest(SourceInfo sourceInfo) throws JsonProcessingException;
    IntegratorRequest updateKycStatus(String kycStatus, String integratorId) ;

    IntegratorRequest getIntegratorRequest(String integratorId);
}
