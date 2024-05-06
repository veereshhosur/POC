package com.lbg.integrator.api.dto.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class KYC implements Serializable {
    private String country;
//    private String aadharDetails;

    private AddressDetails addressDetails;
}
