package com.lbg.integrator.api.dto.request;

import lombok.Data;

@Data
public class sourceBankInfo {
    private String bankName;
    private String micr;
    private String pan;
    private BranchDetails branchDetails;

}
