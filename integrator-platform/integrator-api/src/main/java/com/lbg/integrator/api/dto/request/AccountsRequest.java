package com.lbg.integrator.api.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AccountsRequest {

    private String internetPassword;
    private String mobilePIN;
    private List<String> services;
}
