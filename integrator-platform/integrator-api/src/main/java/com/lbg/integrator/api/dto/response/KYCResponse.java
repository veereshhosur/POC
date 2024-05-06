package com.lbg.integrator.api.dto.response;

import com.lbg.integrator.api.dto.request.AddressDetails;
import lombok.Data;

@Data
public class KYCResponse{
    private String integrationId;
    private String country;
    private String aadharDetails;
    private String kycStatus;
    private AddressDetails addressDetails;
}

