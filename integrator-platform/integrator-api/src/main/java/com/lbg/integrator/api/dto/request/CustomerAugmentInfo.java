package com.lbg.integrator.api.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerAugmentInfo implements Serializable {

    private Integer accountNumber;
    private String accountType;
    private List<String> services;
    private String otherDetails;
}
