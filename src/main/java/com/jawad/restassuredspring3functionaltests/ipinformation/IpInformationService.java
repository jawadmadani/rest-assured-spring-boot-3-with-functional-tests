package com.jawad.restassuredspring3functionaltests.ipinformation;

import com.jawad.restassuredspring3functionaltests.ipinformation.io.IpInformationResponse;
import org.springframework.stereotype.Service;

@Service
public class IpInformationService {

    private final IpInformationClient client;

    public IpInformationService(IpInformationClient client) {
        this.client = client;
    }

    public IpInformationResponse callIpInformationClient() {
        return client.callIpInformationApi();
    }
}
