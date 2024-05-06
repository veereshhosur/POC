package com.lbg.integrator.api.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private List<String> serviceType;
    private String internetPassword;
    private String mobilePIN;

}
