package com.lbg.integrator.api.service;

import com.lbg.integrator.api.dto.request.KYC;

public interface AccountService {

    boolean orchestrateAccountCreate(String integratorId);
}
