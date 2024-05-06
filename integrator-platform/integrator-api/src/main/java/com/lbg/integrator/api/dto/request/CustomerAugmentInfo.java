package com.lbg.integrator.api.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CustomerAugmentInfo {

    private Integer accountNumber;
    private String accountType;
    private List<String> services;
    private String otherDetails;
}
