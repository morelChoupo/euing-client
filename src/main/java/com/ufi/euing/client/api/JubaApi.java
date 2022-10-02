package com.ufi.euing.client.api;


import com.ufi.euing.client.business.JubaService;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.integration.juba.response.DoGetBeneficiaryNameDetails;
import com.ufi.euing.client.integration.juba.response.GetAgentsDetailsResponse;
import com.ufi.euing.client.integration.juba.response.ListeToUseDetailsResponse;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/juba")
public class JubaApi  {

    private static final Logger log = LoggerFactory.getLogger(JubaApi.class);

    final JubaService jubaService;

    public JubaApi(JubaService jubaService) {
        this.jubaService = jubaService;
    }



    @GetMapping("/doGetAgent/{countryCode}")
    public GetAgentsDetailsResponse jubaDoGetAgent(@PathVariable(value = "countryCode") String countryCode) {
        return jubaService.doGetAgent(countryCode);
    }


    @GetMapping("/doGetPurpose")
    public ListeToUseDetailsResponse jubaDoGetPurpose() {
        return jubaService.doGetPurpose();
    }


    @GetMapping("/doGetSourceOfIncome")
    public ListeToUseDetailsResponse jubaDoGetSourceOfIncome() {
        return jubaService.doGetSourceOfIncome();
    }


    @GetMapping("/doGetCollectionMode")
    public ListeToUseDetailsResponse jubaDoGetCollectionMode() {
        return jubaService.doGetCollectionMode();
    }


    @GetMapping("/doGetBeneficiaryName/{mobileNo}")
    public ResponseEntity<EuingApiResponse<DoGetBeneficiaryNameDetails>> jubaDoGetBeneficiaryName(@PathVariable(value = "mobileNo") String mobileNo) {
        log.info("[POST] /juba/doGetBeneficiaryName/{} ::: ", mobileNo);
        EuingApiResponse<DoGetBeneficiaryNameDetails> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = jubaService.jubaDoGetBeneficiaryName(mobileNo);
            if (response.getResponseCode() == 200) {
                return ResponseEntity.ok(payload.success((DoGetBeneficiaryNameDetails) response.getData()));
            } else {
                return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/doGetTarif")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> jubaDoGetTarif(@RequestBody GetTransfinQuoteRequest t) {
        log.info("[POST] /juba/doGetTarif :::  {}", t);
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = jubaService.doGetTarif(t);
            if (response.getResponseCode() == 200) {
                return ResponseEntity.ok(payload.success((GetTransfinQuote) response.getData()));
            } else {
                return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }

    }


    @PostMapping("/doSendTransaction")
    public ResponseEntity<EuingApiResponse<TransactionEuing>> jubaDoSendTransaction(@RequestBody TransactionDetailsEntity trxEntity) {
        log.info("[POST] /juba/doSendTransaction :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = jubaService.doSendTransaction(trxEntity);
            if (response.getResponseCode() == 200) {
                return ResponseEntity.ok(payload.success((TransactionEuing) response.getData()));
            }
            else {
                return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/doGetTransaction")
    public ResponseEntity<EuingApiResponse<TransactionDetailsEntity>> jubaDoGetTransaction(@RequestBody CriterialSearchPayment criterial) {
        log.info("[POST] /juba/doGetTransaction :::  {}", criterial);
        EuingApiResponse<TransactionDetailsEntity> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = jubaService.doGetTransaction(criterial);
            if (response.getResponseCode() == 200) {
                return ResponseEntity.ok(payload.success((TransactionDetailsEntity) response.getData()));
            }
            else {
                return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/doPaymentConfirmation")
    public ResponseEntity<EuingApiResponse<TransactionEuing>> jubaDoPaymentConfirmation(@RequestBody TransactionDetailsEntity trxEntity) {
        log.info("[POST] /juba/doPaymentConfirmation :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = jubaService.doPaymentConfirmation(trxEntity);
            if (response.getResponseCode() == 200) {
                return ResponseEntity.ok(payload.success((TransactionEuing) response.getData()));
            }
            else {
                return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }
}
