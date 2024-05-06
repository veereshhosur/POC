package com.lbg.integrator.api.dto.response;

import com.lbg.integrator.api.dto.request.BranchDetails;
import lombok.Data;

@Data
public class LbgBankDetails {

    private String accountNumber;
    private String internetId;
    private BranchDetails branchDetails;
}
