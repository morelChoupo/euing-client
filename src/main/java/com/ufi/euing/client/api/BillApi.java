package com.ufi.euing.client.api;


import com.ufi.euing.client.business.BillPayService;
import com.ufi.euing.client.business.CompagnieService;
import com.ufi.euing.client.business.FactureService;
import com.ufi.euing.client.business.ListToUseService;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.integration.billpay.BillResponse;
import com.ufi.euing.client.integration.billpay.ConsultBill;
import com.ufi.euing.client.integration.billpay.PayBill;
import com.ufi.euing.client.integration.billpay.UtilsBillPay;
import com.ufi.euing.client.response.EuingApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillApi {

    private static final Logger log = LoggerFactory.getLogger(BillApi.class);

    final BillPayService billPayService;

    final ListToUseService listToUseService;

    final CompagnieService compagnieService;

    final FactureService factureService;

    public BillApi(BillPayService billPayService,
                   ListToUseService listToUseService,
                   CompagnieService compagnieService,
                   FactureService factureService) {
        this.billPayService = billPayService;
        this.listToUseService = listToUseService;
        this.compagnieService = compagnieService;
        this.factureService = factureService;
    }


    @GetMapping("/{country}")
    public List<DoubleValue> getBillPayByCompagnieAndServiceAndPays(@PathVariable(value = "country") String country) {
        System.out.println("## All BillPay ####");
        List<DoubleValue> dv = new ArrayList<>();
        List<ListeToUse> listeToUse = listToUseService.getListeToUseByUsage("BILLPAY");
        for (int i = 0; i < listeToUse.size(); i++) {
            ListeToUse get = listeToUse.get(i);
            Compagnie cmp = compagnieService.findByCodePartenaire(get.getCode());
            if (cmp != null) {
                if (cmp.getPays().getPsCode().equalsIgnoreCase(country)) {
                    dv.add(new DoubleValue(get.getCode(), get.getLibelle()));

                }
            }

        }
        return dv;
    }

    @PostMapping("/searchBill")
    public ResponseEntity<EuingApiResponse<List>> consultationBilling(ConsultBill consultBill) {
        log.info("[POST] /bill/searchBill:::  {}", consultBill);
        EuingApiResponse<List> payload = new EuingApiResponse<>();
        try {
            BillResponse response = billPayService.consultBill(consultBill);
            if (response.getCode() == 200) {
                return ResponseEntity.ok(payload.success(response.getData()));
            } else {
                return new ResponseEntity<>(payload.failure(response.getCode(),response.getMessage()), HttpStatus.valueOf(response.getCode()));
            }
        }catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/payBill")
    public ResponseEntity<EuingApiResponse<String>> payementBilling(PayBill payBill) {
        log.info("[POST] /bill/pay :::  {}", payBill);
        EuingApiResponse<String> payload = new EuingApiResponse<>();
        try {
                    Facture facture = factureService.findById(payBill.getFactureId());
                    if (facture != null && facture.getStatusFacture().equalsIgnoreCase("N")) {
                        GenericsResponse<TransactionBill> response = billPayService.PayBill(payBill, facture);
                        if (response.getResponseCode() == 200) {
                            return ResponseEntity.ok(payload.success(response.getData().getTransReference()));
                        } else {
                            return new ResponseEntity<>(payload.failure(response.getResponseCode(),response.getResponseDescription()), HttpStatus.valueOf(response.getResponseCode()));
                        }
                    } else {
                        return new ResponseEntity<>(payload.failure(404,UtilsBillPay.MSG_EROOR_BILL_NOT_FOUND_OR_ALREADY_PAID), HttpStatus.valueOf(404));
                    }

        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()), HttpStatus.valueOf(500));
        }
    }
}
