package com.lbg.integrator.api.service;

import com.lbg.integrator.api.dto.request.KYC;
import com.lbg.integrator.api.dto.request.SourceBankInfo;
import com.lbg.integrator.api.dto.request.SourceInfo;

public interface KYCService {

    boolean validateKyc(KYC kyc);
}
