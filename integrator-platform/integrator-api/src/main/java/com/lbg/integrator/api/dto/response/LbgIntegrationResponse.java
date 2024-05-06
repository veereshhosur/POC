package com.lbg.integrator.api.dto.response;

import com.lbg.integrator.api.dto.request.CustomerAugmentInfo;
import com.lbg.integrator.api.dto.request.SourceBankInfo;
import lombok.Data;

@Data
public class LbgIntegrationResponse {

    private String applicationStatus;
    private SourceBankInfo sourceBankDetails;
    private LbgBankDetails lbgAccountDetails;
    private CustomerAugmentInfo customerAugmentInfo;

}