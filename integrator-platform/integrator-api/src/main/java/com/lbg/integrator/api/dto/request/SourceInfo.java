package com.lbg.integrator.api.dto.request;


import lombok.Data;

@Data
public class SourceInfo {
    private SourceBankInfo sourceBankInfo;
    private CustomerInfo customerInfo;
    private KYC customerKyc;
    private CustomerAugmentInfo customerAugmentInfo;

}
