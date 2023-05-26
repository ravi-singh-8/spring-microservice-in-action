package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.config.LicensingServiceConfig;
import com.thoughtmechanix.licenses.models.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organizations/{orgId}/licenses")
public class LicenseServiceController {

    @Autowired
    LicensingServiceConfig config;

    @GetMapping(value="/{licenseId}")
    public License getLicenses(@PathVariable("orgId")String orgId,
                               @PathVariable("licenseId") String licenseId) {
        return new License()
                .withId(licenseId)
                .withProductName("Telco")
                .withLicenseType("Seat")
                .withOrganizationId(orgId);
    }

    @RequestMapping(value = "config")
    public LicensingServiceConfig getConfig() {
        return config;
    }
}
