package com.lbg.integrator.api.service.impl;

import com.lbg.integrator.api.dto.request.KYC;
import com.lbg.integrator.api.service.AccountService;
import com.lbg.integrator.api.service.KYCService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public boolean orchestrateAccountCreate(String integratorId) {
//        String aadharDetails = kyc.getAadharDetails();
//        if(aadharDetails.contains("111"))
//            return false;

        return Math.random()<0.5;
    }
}
