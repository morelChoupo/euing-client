package com.ufi.euing.client.api;


import com.ufi.euing.client.business.ListToUseService;
import com.ufi.euing.client.business.PaysService;
import com.ufi.euing.client.entity.ListeToUse;
import com.ufi.euing.client.entity.Pays;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generic")
public class GenericApi {

    private static final Logger log = LoggerFactory.getLogger(GenericApi.class);

    final ListToUseService listToUseService;

    final PaysService paysService;

    public GenericApi(ListToUseService listToUseService, PaysService paysService) {
        this.listToUseService = listToUseService;
        this.paysService = paysService;
    }


    @PostMapping("/partner-choice")
    public ResponseEntity<EuingApiResponse<List<ListeToUse>>> choicePartner(@RequestParam String country, @RequestParam String serviceCode) {
        EuingApiResponse<List<ListeToUse>> payload = new EuingApiResponse<>();

        String usageFinal = "PARTNER_CHOICE_".concat(country);
        try {
            return ResponseEntity.ok(payload.success(listToUseService.getListeToUseByUsageAndLibelle(usageFinal, serviceCode)));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
    }


    @GetMapping("/country/all")
    public ResponseEntity<EuingApiResponse<List<Pays>>> getCountry() {
        EuingApiResponse<List<Pays>> payload = new EuingApiResponse<>();
        try {
            return ResponseEntity.ok(payload.success(paysService.getAllCountries()));
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }

    }
}
