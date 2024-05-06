package com.lbg.integrator.api.dto.request;


import lombok.Data;

@Data
public class KYC {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pin;
}
