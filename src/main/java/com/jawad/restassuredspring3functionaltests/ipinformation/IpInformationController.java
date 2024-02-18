package com.jawad.restassuredspring3functionaltests.ipinformation;

import com.jawad.restassuredspring3functionaltests.BaseController;
import com.jawad.restassuredspring3functionaltests.ipinformation.io.IpInformationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpInformationController extends BaseController {

    private final IpInformationService service;

    public IpInformationController(IpInformationService service) {
        this.service = service;
    }

    @GetMapping(path = "/ip-information")
    public ResponseEntity<IpInformationResponse> retrieveIpInformation() {
        IpInformationResponse response = service.callIpInformationClient();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
