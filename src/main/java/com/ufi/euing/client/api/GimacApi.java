package com.ufi.euing.client.api;


import com.ufi.euing.client.business.GimacService;
import com.ufi.euing.client.business.ListToUseService;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.integration.gimac.model.GimacUtil;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gimac")
public class GimacApi {

    private static final Logger log = LoggerFactory.getLogger(GimacApi.class);

    final GimacService gimacService;

    final ListToUseService listToUseService;

    public GimacApi(GimacService gimacService, ListToUseService listToUseService) {
        this.gimacService = gimacService;
        this.listToUseService = listToUseService;
    }


    @GetMapping("/doGetOperationTypes")
    public List<DoubleValue> gimacDoGetOperationTypes() {
        List<DoubleValue> dv = new ArrayList<>();
        List<ListeToUse> list = listToUseService.getListeToUseByUsage(GimacUtil.GIMAC_TYPE_OPERATION);
        log.info("list type operation ::: {}", list);
        for (int i = 0; i < list.size(); i++) {
            dv.add(new DoubleValue(list.get(i).getCode(), list.get(i).getLibelle()));
        }
        return dv;
    }


    @GetMapping("/doGetMembers/{typeOp}/{country}")
    public List<DoubleValue> gimacDoGetMembers(@PathVariable(value = "typeOp") String typeOp, @PathVariable(value = "country") String country) {
        List<DoubleValue> dv = new ArrayList<>();
        List<ListeToUse> list = new ArrayList<>();
        if (typeOp.equalsIgnoreCase(GimacUtil.GIMAC_WALLET_REMITTANCE_CODE)) {
            log.info("find listWalletPartners");
            list = listToUseService.getListeToUseByUsage(GimacUtil.GIMAC_MEMBERS_WALLET_PREFIX + country);
        }

        if (typeOp.equalsIgnoreCase(GimacUtil.GIMAC_ACCOUNT_REMITTANCE_CODE)) {
            log.info("find listAccountPartners");
            list = listToUseService.getListeToUseByUsage(GimacUtil.GIMAC_MEMBERS_ACCOUNT_PREFIX + country);
        }
        log.info("list members :::  {}",  list);

        for (int i = 0; i < list.size(); i++) {
            dv.add(new DoubleValue(list.get(i).getCode(), list.get(i).getLibelle()));
        }
        return dv;
    }


    @PostMapping("/doGetTarif")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> gimacDoGetTarif(@RequestBody GetTransfinQuoteRequest t) {
        log.info("[POST] /gimac/doGetTarif :::  {}", t);
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = gimacService.doGetTarif(t);
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
    public ResponseEntity<EuingApiResponse<TransactionEuing>> gimacDoSendTransaction(@RequestBody TransactionDetailsEntity trxEntity) {
        log.info("[POST] /gimac/doSendTransaction :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = gimacService.doSendTransaction(trxEntity);
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
