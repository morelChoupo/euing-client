package com.ufi.euing.client.api;


import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.EuingServiceException;
import com.ufi.euing.client.response.EuingApiResponse;
import com.ufi.mfs.MfsUtils;
import com.ufi.mfs.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mfs")
public class MfsApi  {

    private static final Logger log = LoggerFactory.getLogger(MfsApi.class);

    final MfsService mfsService;

    final ListToUseService listToUseService;

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final PaysService paysService;

    MessageApi message = new MessageApi();

    public MfsApi(MfsService mfsService,
                  ListToUseService listToUseService,
                  ParametreBaseService parametreBaseService,
                  ParametrewsService parametrewsService, PaysService paysService) {
        this.mfsService = mfsService;
        this.listToUseService = listToUseService;
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.paysService = paysService;
    }

    com.ufi.mfs.MfsService mfsServices = new com.ufi.mfs.MfsService();


    @GetMapping("/getTypeOperation")
    public List<DoubleValue> mfsGetTypeOperation() {
        System.out.println("mfsGetTypeOperation ::: ");
        List<DoubleValue> dv = new ArrayList<>();
        List<ListeToUse> list = listToUseService.getListeToUseByUsage(MfsUtils.MFS_TYPE_OPERATION);
        System.out.println("list type operation ::: " + list);
        for (int i = 0; i < list.size(); i++) {
            dv.add(new DoubleValue(list.get(i).getCode(), list.get(i).getLibelle()));
        }
        return dv;
    }


    @GetMapping("/getListPartners/{typeOp}/{country}")
    public List<DoubleValue> mfsGetListPartners(@PathVariable(value = "typeOp") String typeOp, @PathVariable(value = "country") String country) {
        System.out.println("mfsGetListPartners ::: typeOp ::: " + typeOp + " ::: country ::: " + country);
        List<DoubleValue> dv = new ArrayList<>();

        GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode(MfsUtils.MFS_COMPAGNIE_CODE);
        if (p.getResponseCode() == 200) {
            Parametrews pws = p.getT();

            if (typeOp.equalsIgnoreCase(MfsUtils.MFS_WALLET_REMITTANCE_CODE)) {
                List<ListeToUse> list = new ArrayList<>();
                System.out.println("list wallet partners ::: " + list);
                for (int i = 0; i < list.size(); i++) {
                    dv.add(new DoubleValue(list.get(i).getCode(), list.get(i).getLibelle()));
                }
            }
            if (typeOp.equalsIgnoreCase(MfsUtils.MFS_BANK_REMITTANCE_CODE)) {
                List<BanksModel> list = mfsServices.doGetBanks(pws.getLogin(), pws.getPassword(), country);
                System.out.println("list bank partners ::: " + list);
                for (int i = 0; i < list.size(); i++) {
                    dv.add(new DoubleValue(list.get(i).getMfsBankCode(), list.get(i).getBankName()));
                }
            }
            if (typeOp.equalsIgnoreCase(MfsUtils.MFS_CASH_PICKUP_REMITTANCE_CODE)) {
                List<ListeToUse> list = listToUseService.getListeToUseByUsage(MfsUtils.MFS_CASH_PICKUP_PARTNER);
                System.out.println("list cash pickup partners ::: " + list);
                for (int i = 0; i < list.size(); i++) {
                    dv.add(new DoubleValue(list.get(i).getCode(), list.get(i).getLibelle()));
                }
            }
            if (typeOp.equalsIgnoreCase(MfsUtils.MFS_AIRTIME_REMITTANCE_CODE)) {
                List<PartnerModel> list = mfsServices.doGetPartners(pws.getLogin(), pws.getPassword());
                System.out.println("list airtime partners ::: " + list);
                for (int i = 0; i < list.size(); i++) {
                    dv.add(new DoubleValue(list.get(i).getPartnerCode(), list.get(i).getPartnerCode()));
                }
            }
        }

        return dv;
    }



    @PostMapping("/doAccountRequest")
    public ResponseEntity<EuingApiResponse<String>> mfsDoAccountRequest(@RequestBody MAccountRequest request) {
        log.info("mfsDoAccountRequest");
        EuingApiResponse<String> payload = new EuingApiResponse<>();
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode(MfsUtils.MFS_COMPAGNIE_CODE);
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                String corporateCode = pws.getLogin();
                String password = pws.getPassword();
                boolean result = false;
                GenericsResponse resppays = paysService.findPaysByCodeIso(request.getTo_country());
                if (resppays.getResponseCode() == 200) {
                    Pays pays = (Pays) resppays.getData();
                    if (request.getTypeOp().equalsIgnoreCase(MfsUtils.MFS_WALLET_REMITTANCE_CODE) || request.getTypeOp().equalsIgnoreCase(MfsUtils.MFS_CASH_PICKUP_REMITTANCE_CODE)) {
                        if (request.getTo_country() != null || !request.getTo_country().trim().isEmpty() || request.getMsidn() != null || !request.getMsidn().trim().isEmpty()) {
                            WalletModel wallet = mfsServices.doAccountRequest(corporateCode, password, pays.getPsCode2(), request.getMsidn());
                            result = wallet.getStatus().getStatusCode().equalsIgnoreCase("Active");
                            if (result) {
                                return ResponseEntity.ok(payload.success(wallet.getPartnerCode()));
                            } else {
                                return new ResponseEntity<>(payload.failure(404,"Account not found"), HttpStatus.valueOf(404));
                            }
                        } else {
                            return new ResponseEntity<>(payload.failure(403,"Error ::: Attention intégrité des données compromise"), HttpStatus.valueOf(403));
                        }
                    } else if (request.getTypeOp().equalsIgnoreCase(MfsUtils.MFS_BANK_REMITTANCE_CODE)) {
                        if (request.getMsidn() != null || !request.getMsidn().trim().isEmpty() || request.getAccount_number() != null || !request.getAccount_number().trim().isEmpty()
                                || request.getMfs_bank_code() != null || !request.getMfs_bank_code().trim().isEmpty() || request.getTo_country() != null || !request.getTo_country().trim().isEmpty()) {
                            BankAccountModel account = mfsServices.doValidateBankAccount(corporateCode, password, request.getMsidn(), request.getName(), request.getAccount_number(), request.getMfs_bank_code(), pays.getPsCode2());
                            result = account.getStatus().getStatusCode().equalsIgnoreCase("MR105");
                            if (result) {
                                return ResponseEntity.ok(payload.success(account.getAccountHolderName()));
                            } else {
                                return new ResponseEntity<>(payload.failure(404,"Account not found"), HttpStatus.valueOf(404));
                            }
                        } else {
                            return new ResponseEntity<>(payload.failure(403,"Error ::: Attention intégrité des données compromise"), HttpStatus.valueOf(403));
                        }
                    } else if (request.getTypeOp().equalsIgnoreCase(MfsUtils.MFS_AIRTIME_REMITTANCE_CODE)) {
                        if (request.getFrom_country() != null || !request.getFrom_country().trim().isEmpty() || request.getSend_currency() != null || !request.getSend_currency().trim().isEmpty()
                                || request.getTo_country() != null || !request.getTo_country().trim().isEmpty() || request.getMsidn() != null || !request.getMsidn().trim().isEmpty()) {
                            AirtimeModel airtime = mfsServices.doValidateAirtimeAccount(corporateCode, password, request.getFrom_country(), request.getSend_currency(), pays.getPsCode2(), request.getMsidn());
                            if (result) {
                                return ResponseEntity.ok(payload.success(airtime.getAirtimeMnoId()));
                            } else {
                                return new ResponseEntity<>(payload.failure(404,"Account not found"), HttpStatus.valueOf(404));
                            }
                        } else {
                            return new ResponseEntity<>(payload.failure(403,"Error ::: Attention intégrité des données compromise"), HttpStatus.valueOf(403));
                        }
                    } else {
                        return new ResponseEntity<>(payload.failure(404,"Error ::: method " + request.getTypeOp() + " not implemented."), HttpStatus.valueOf(404));
                    }
                } else {
                    return new ResponseEntity<>(payload.failure(404,"Error ::: No country with code " + request.getTo_country() + " found"), HttpStatus.valueOf(404));
                }
            } else {
                return new ResponseEntity<>(payload.failure(p.getResponseCode(),p.getResponseDescription()), HttpStatus.valueOf(p.getResponseCode()));
            }
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()),HttpStatus.valueOf(500));
        }

    }


    @GetMapping("/doValidateWalletAccount/{country}/{msidn}")
    public ResponseEntity<EuingApiResponse<WalletModel>> accountRequest(@PathVariable(value = "country") String country, @PathVariable(value = "msidn") String msidn) {
        System.out.println("mfsDoValidateWalletAccount ::: country ::: " + country + " ::: msidn ::: " + msidn);
        EuingApiResponse<WalletModel> payload = new EuingApiResponse<>();
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode(MfsUtils.MFS_COMPAGNIE_CODE);
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                WalletModel walletModel = mfsServices.doAccountRequest(pws.getLogin(), pws.getPassword(), country, msidn);
                return ResponseEntity.ok(payload.success(walletModel));
            } else {
                return new ResponseEntity<>(payload.failure(p.getResponseCode(),p.getResponseDescription()),HttpStatus.valueOf(p.getResponseCode()));
            }
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()),HttpStatus.valueOf(500));
        }
    }


    @GetMapping("/doValidateBankAccount/{msisdn}/{name}/{account_number}/{mfs_bank_code}/{to_country}")
    public ResponseEntity<EuingApiResponse<BankAccountModel>> doValidateBankAccount(@PathVariable(value = "msisdn") String msisdn, @PathVariable(value = "name") String name,
                                          @PathVariable(value = "account_number") String account_number, @PathVariable(value = "mfs_bank_code") String mfs_bank_code,
                                          @PathVariable(value = "to_country") String to_country) {
        System.out.println("mfsDoValidateBankAccount ::: msisdn ::: " + msisdn + " ::: name ::: " + name + " ::: account_number ::: "
                + account_number + " ::: mfs_bank_code ::: " + mfs_bank_code + " ::: to_country ::: " + to_country);

        EuingApiResponse<BankAccountModel> payload = new EuingApiResponse<>();
        try {
            GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode(MfsUtils.MFS_COMPAGNIE_CODE);
            if (p.getResponseCode() == 200) {
                Parametrews pws = p.getT();
                BankAccountModel accountModel = mfsServices.doValidateBankAccount(pws.getLogin(), pws.getPassword(), msisdn, name, account_number, mfs_bank_code, to_country);
                return ResponseEntity.ok(payload.success(accountModel));
            } else {
                return new ResponseEntity<>(payload.failure(p.getResponseCode(),p.getResponseDescription()),HttpStatus.valueOf(p.getResponseCode()));
            }
        } catch (EuingServiceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()),HttpStatus.valueOf(500));
        }
    }


    @PostMapping("/doGetTarif")
    public ResponseEntity<EuingApiResponse<GetTransfinQuote>> mfsDoGetTarif(@RequestBody  GetTransfinQuoteRequest t) {
        log.info("[POST] /mfs/doGetTarif :::  {}", t);
        EuingApiResponse<GetTransfinQuote> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = mfsService.doGetTarif(t);
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
    public ResponseEntity<EuingApiResponse<TransactionEuing>> mfsDoSendTransaction(@RequestBody TransactionDetailsEntity trxEntity) {
        log.info("[POST] /mfs/doSendTransaction :::  {}", trxEntity);
        EuingApiResponse<TransactionEuing> payload = new EuingApiResponse<>();
        try {
            GenericsResponse response = mfsService.doRemittance(trxEntity);
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


    @PostMapping("/getTransStatus/{mfsTransId}/{userId}")
    public ResponseEntity<EuingApiResponse<String>> mfsGetTransStatus(@PathVariable(value = "mfsTransId") String mfsTransId, @PathVariable(value = "userId") Long userId) {
        System.out.println("mfsGetTransStatus ::: " + mfsTransId);
        EuingApiResponse<String> payload = new EuingApiResponse<>();
        try {
                        GenericsResponse<Parametrews> p = parametrewsService.getParametrewsByCode(MfsUtils.MFS_COMPAGNIE_CODE);
                        if (p.getResponseCode() == 200) {
                            Parametrews pws = p.getT();
                            System.out.println("=============== ICI MSF GET TRANSACTION STATUS ************************* ");
                            EResponseModel result = mfsServices.doGetTransStatus(pws.getLogin(), pws.getPassword(), mfsTransId);
                            System.out.println("result ::: " + result);
                            if (result.getCode().getStatusCode().equalsIgnoreCase("MR101")) {
                                String code = result.getMfsTransId() + ":" + result.getThirdPartyTransId();
                                message.setSendCode(code);
                                return ResponseEntity.ok(payload.success(code));
                            } else {
                                return new ResponseEntity<>(payload.failure(500,"Error mfsGetTransStatus ::: Code = " + result.getCode().getStatusCode() + "; Message = " + result.getMessage()),HttpStatus.valueOf(500));
                            }
                        } else {
                            return new ResponseEntity<>(payload.failure(p.getResponseCode(),p.getResponseDescription()),HttpStatus.valueOf(p.getResponseCode()));
                        }

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(payload.failure(500,e.getMessage()),HttpStatus.valueOf(500));
        }
    }
}
