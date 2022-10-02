package com.ufi.euing.client.api;


import com.ufi.euing.client.business.WorldRemitService;
import com.ufi.euing.client.entity.CriterialSearchPayment;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.TransactionDetailsEntity;
import com.ufi.euing.client.entity.TransactionEuing;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/worldRemit")
public class WorldRemitApi {

    private static final Logger log = LoggerFactory.getLogger(WorldRemitApi.class);

   final WorldRemitService worldRemitService;

    public WorldRemitApi(WorldRemitService worldRemitService) {
        this.worldRemitService = worldRemitService;
    }


    @PostMapping("/doGetTransaction")
    public ResponseEntity<EuingApiResponse<TransactionDetailsEntity>> WorldRemitDoGetTransaction(CriterialSearchPayment criterial) {
        log.info("[POST] /worldRemit/doGetTransaction :::  {}", criterial);
        EuingApiResponse<TransactionDetailsEntity> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = worldRemitService.doGetTransaction(criterial);
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


    @PostMapping("/doPayTransaction")
    public ResponseEntity<EuingApiResponse<TransactionEuing>> worldRemitDoPayTransaction(TransactionDetailsEntity trxEntity) {
        log.info("[POST] /worldRemit/doSendTransaction :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = worldRemitService.doPayTransaction(trxEntity);
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
