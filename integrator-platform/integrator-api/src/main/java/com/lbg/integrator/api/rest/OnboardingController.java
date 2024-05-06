package com.lbg.integrator.api.rest;

import com.lbg.integrator.api.dto.request.SourceInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;

@RestController(value = "/v1/on-boarding")
public class OnboardingController {

    @PostMapping(value = "/customers")
    public ResponseEntity createConfiguration(@RequestBody SourceInfo request){

        return ResponseEntity.ok().build();
    }
}
