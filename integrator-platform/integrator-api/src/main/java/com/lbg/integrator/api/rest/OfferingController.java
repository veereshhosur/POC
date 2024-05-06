package com.lbg.integrator.api.rest;

import com.lbg.integrator.api.entity.IntegratorRequest;
import com.lbg.integrator.api.service.IntegratorService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OfferingController {

    @Autowired
    IntegratorService integratorService;

    @GetMapping("v1/on-boarding/customers/{integratorId}/service-offerings")
    public ResponseEntity<List<String>> getServicesOffered(@PathVariable String integratorId){
        IntegratorRequest ir = integratorService.getIntegratorRequest(integratorId);
        List<String> services = null;
        if(ir.isKycStatus().equals("Rejected"))
            services = new ArrayList<>();
        else
            services = Arrays.asList("savings","credit","loan");
        return new ResponseEntity(services,HttpStatus.OK);
    }
}
