package com.ufi.euing.client.api;


import com.ufi.euing.client.business.ThunesService;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/thunes")
public class ThunesApi {

    final ThunesService thunesService;

    private static final Logger log = LoggerFactory.getLogger(ThunesApi.class);

    public ThunesApi(ThunesService thunesService) {
        this.thunesService = thunesService;
    }


    @PostMapping("/doGetTarif")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> thunesDoGetTarif(@RequestBody GetTransfinQuoteRequest t) {
        log.info("[POST] /thunes/doGetTarif :::  {}", t);
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = thunesService.doGetTarif(t);
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
    public ResponseEntity<EuingApiResponse<TransactionEuing>> thunesDoSendTransaction(@RequestBody TransactionDetailsEntity trxEntity) {
        log.info("[POST] /thunes/doSendTransaction :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = thunesService.doSendTransaction(trxEntity);
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
