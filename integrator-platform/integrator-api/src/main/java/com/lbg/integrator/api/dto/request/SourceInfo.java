package com.lbg.integrator.api.dto.request;


import lombok.Data;

@Data
public class SourceInfo {
    private SourceInfo sourceInfo;
    private CustomerInfo customerInfo;
    private KYC KYC;
    private CustomerAugmentInfo customerAugmentInfo;

}
