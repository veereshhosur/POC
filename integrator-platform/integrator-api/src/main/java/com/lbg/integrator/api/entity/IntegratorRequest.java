package com.lbg.integrator.api.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbg.integrator.api.dto.request.SourceInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name ="integrator_request")
public class IntegratorRequest {

    @Id
    private String requestorId;
    private String sourceInfo;

    private String kycStatus;

    public String getRequestorId() {
        return requestorId;
    }

    public String isKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }
//    public String getRequestorId() {
//        return requestorId;
//    }

//    public void setRequestorId() {
//        this.requestorId = UUID.randomUUID().toString();
//    }
//
//    public SourceInfo getSourceBankInfo() {
//        try {
//            return new ObjectMapper().readValue(SourceInfo, SourceInfo.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void setSourceBankInfo(String sourceBankInfo) {
//        try {
//            this.sourceInfo =new ObjectMapper().writeValueAsString(sourceBankInfo);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
