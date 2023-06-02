package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.config.LicensingServiceConfig;
import com.thoughtmechanix.licenses.models.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organizations/{orgId}/licenses")
public class LicenseServiceController {

    @Autowired
    private Environment environment;

    @Autowired
    private LicensingServiceConfig config;

    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicenses(@PathVariable("orgId")String orgId,
                                              @PathVariable("licenseId") String licenseId) {
        HttpHeaders headers = new HttpHeaders();
        String port = environment.getProperty("local.server.port");
        headers.add("server.port",  port);

        License license = new License()
                .withId(licenseId)
                .withProductName("Telco")
                .withLicenseType("Seat")
                .withOrganizationId(orgId);
        return ResponseEntity.ok().headers(headers).body(license);
    }

    @RequestMapping(value = "config")
    public LicensingServiceConfig getConfig() {
        return config;
    }
}
