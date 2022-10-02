package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.*;
import com.ufi.euing.client.props.EuingProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BillPayServiceImpl implements BillPayService {

    final ServiceService serviceService;

    final EuingProperties euingProperties;

    final GuichetService guichetService;

    final UtilisateurService utilisateurService;

    final BillEneoService billEneoService;

    final BillCamwaterService billCamwaterService;

    final CompagnieService compagnieService;

    final FactureService factureService;

    final ParametreBaseService parametreBaseService;

    public BillPayServiceImpl(ServiceService serviceService,
                              EuingProperties euingProperties,
                              GuichetService guichetService,
                              UtilisateurService utilisateurService,
                              BillEneoService billEneoService,
                              BillCamwaterService billCamwaterService,
                              CompagnieService compagnieService,
                              FactureService factureService,
                              ParametreBaseService parametreBaseService) {
        this.serviceService = serviceService;
        this.euingProperties = euingProperties;
        this.guichetService = guichetService;
        this.utilisateurService = utilisateurService;
        this.billEneoService = billEneoService;
        this.billCamwaterService = billCamwaterService;
        this.compagnieService = compagnieService;
        this.factureService = factureService;
        this.parametreBaseService = parametreBaseService;
    }


    @Override
    public BillResponse consultBill(ConsultBill consultBill) {
        BillResponse result = new BillResponse();
        try {
            List<Facture> factures = new ArrayList<>();
            int resCode = 500;
            String resMsg = " ";
            System.out.println("YMDF ====>" + consultBill.toString());
            Compagnie compagnie = compagnieService.findByCodePartenaire(consultBill.getMarchatCode()); // find companie
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(consultBill.getServiceCode())).getT(); // find service
            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
            /*Utilisateur usr = (utilisateurService.getUtilisateurById(euingProperties.getUtilisateurCode())).getT(); // find user
            Guichet guichet = new Guichet();
            if (usr.getUsrTypeUo().equalsIgnoreCase("GUICHET")) {
                guichet = guichetService.find(Long.valueOf(usr.getUsrUoId())); // find guichet
            }*/

            // find mode
            String requestModeEneo = "";
            String requestModeCamwater = "";

            ParametreBase modeEneo = parametreBaseService.find(UtilsBillPay.BILLPAY_ENEO_MODE);
            if (modeEneo != null) {
                requestModeEneo = modeEneo.getValeur();
            } else {
                result = new BillResponse(404, UtilsBillPay.MSG_EROOR_BILLPAY_ENEO_MODE_NOT_FOUND);
            }

            ParametreBase modeCamwater = parametreBaseService.find(UtilsBillPay.BILLPAY_ENEO_MODE);
            if (modeCamwater != null) {
                requestModeCamwater = modeCamwater.getValeur();
            } else {
                result = new BillResponse(404, UtilsBillPay.MSG_EROOR_BILLPAY_CAMWATER_MODE_NOT_FOUND);
            }

            // initialise marchat code
            String codeMarchandEneo = "";
            String codeMarchandCamwater = "";
            String codeMarchandUnivYde1 = "";
            String codeMarchandUnivDsc = "";
            String codeMarchandUnivYde2 = "";
            // find merchand code
            ParametreBase marchandEneo = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_ENEO);
            if (marchandEneo != null) {
                codeMarchandEneo = marchandEneo.getValeur().trim();
            }

            ParametreBase marchandCamwater = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_CAMWATER);
            if (marchandCamwater != null) {
                codeMarchandCamwater = marchandCamwater.getValeur();
            }

            ParametreBase marchandUnivYde1 = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_YDE1);
            if (marchandEneo != null) {
                codeMarchandUnivYde1 = marchandUnivYde1.getValeur();
            }

            ParametreBase marchandUnivDsc = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_DSC);
            if (marchandUnivDsc != null) {
                codeMarchandUnivDsc = marchandUnivDsc.getValeur();
            }

            ParametreBase marchandUnivYde2 = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_YDE2);
            if (marchandUnivYde2 != null) {
                codeMarchandUnivYde2 = marchandUnivYde2.getValeur();
            }
            System.out.println("Code Marchand ===== > " + consultBill.getMarchatCode());

            if (consultBill.getMarchatCode().equalsIgnoreCase(codeMarchandEneo)) {
                System.out.println("CONSULTATION FACTURE ENEO EN MODE " + modeEneo);
                List<Facture> list;
                if (UtilsBillPay.TYPE_CUSTOMER_ID.equalsIgnoreCase(consultBill.getSearchType())) {
                    list = factureService.findByNumAbonne(consultBill.getSearchValue());
                } else {
                    list = factureService.findByFactureNumber(consultBill.getSearchValue());
                }

                BillSearchResponse spr;
                if (list.isEmpty()) {
                    System.out.println("==============> Compagnie ==============> " + compagnie.toString());
                    spr = billEneoService.consultBillsEneo(compagnie.getCodePartenaire(), consultBill.getSearchType(), consultBill.getSearchValue(), requestModeEneo);
                    System.out.println("spr = " + spr.getData());
                    System.out.println("spr = " + spr.toString());
                    if (spr.getCode() == 200 && spr.getData().size() > 0) {
                        for (Bill bill : spr.getData()) {
                            Facture facture = new Facture();
                            facture = getFactureFromEneoSearch(facture, bill, compagnie, usr);
                            // calcul de frais facture
                            GenericsResponse<Facture> calculFees = billEneoService.calculFeesEneo(compagnie, guichet, service.getId(), service.getNom(), facture, usr.getUsrId(), usr.getUsrLogin());
                            if (calculFees.getResponseCode() == 200) {
                                GenericsResponse<CheckBillResponse> precheckFees = billEneoService.precheckFeesEneo(compagnie, guichet, service, facture.getMontant(), usr);
                                if (precheckFees.getResponseCode() == 200) {
                                    facture = factureService.createFctureDb(facture);
                                    factures.add(facture);
                                    resCode = 200;
                                } else {
                                    resMsg += "precheckFees Error = " + precheckFees.getResponseDescription() + " ";
                                    resCode = precheckFees.getResponseCode();
                                }
                            } else {
                                resMsg += "calculFees Error = " + calculFees.getResponseDescription() + " ";
                                resCode = calculFees.getResponseCode();
                            }
                        }
                    } else {
                        resCode = 404;
                        //resMsg = UtilsBillPay.BILL_STATUS_NOT_FOUND;
                        resMsg = "Bill not found";
                    }
                } else {
                    for (Facture f : list) {
                        if ("N".equalsIgnoreCase(f.getStatusFacture())) {
                            spr = billEneoService.consultBillsEneo(compagnie.getCodePartenaire(), consultBill.getSearchType(), consultBill.getSearchValue(), requestModeEneo);
                            if (spr.getCode() == 200 && spr.getData().size() > 0) {
                                for (Bill bill : spr.getData()) {
                                    Facture facture = getFactureFromEneoSearch(f, bill, compagnie, usr);
                                    // calcul de frais facture
                                    GenericsResponse<Facture> calculFees = billEneoService.calculFeesEneo(compagnie, guichet, service.getId(), service.getNom(), facture, usr.getUsrId(), usr.getUsrLogin());
                                    if (calculFees.getResponseCode() == 200) {
                                        GenericsResponse<CheckBillResponse> precheckFees = billEneoService.precheckFeesEneo(compagnie, guichet, service, facture.getMontant(), usr);
                                        if (precheckFees.getResponseCode() == 200) {
                                            facture = factureService.createFctureDb(facture);
                                            factures.add(facture);
                                            resCode = 200;
                                        } else {
                                            resMsg += "precheckFees Error = " + precheckFees.getResponseDescription() + " ";
                                            resCode = precheckFees.getResponseCode();
                                        }
                                    } else {
                                        resMsg += "calculFees Error = " + calculFees.getResponseDescription() + " ";
                                        resCode = calculFees.getResponseCode();
                                    }
                                }
                            }
                        } else {
                            resCode = 404;
                            resMsg = UtilsBillPay.BILL_STATUS_PAID;
                        }
                    }
                }

                System.out.println("number of factures = " + factures.size());
                if (UtilsBillPay.TYPE_CUSTOMER_ID.equalsIgnoreCase(consultBill.getSearchType())) {
                    List<Facture> lists = factures;
                    for (int i = 0; i < lists.size(); i++) {
                        spr = billEneoService.consultBillsEneo(compagnie.getCodePartenaire(), UtilsBillPay.TYPE_BILL_NUMBER, lists.get(i).getFactureNumber(), requestModeEneo);
                        if (spr.getCode() != 200) {
                            System.out.println("removed facture = " + lists.get(i));
                            factures.remove(i);
                        }
                    }
                    System.out.println("size of factures = " + factures.size());
                    if (factures.isEmpty()) {
                        System.out.println("list facture is empty");
                        resCode = 404;
                    } else {
                        System.out.println("list facture not empty");
                        resCode = 200;
                    }
                }
                result = new BillResponse(resCode, resMsg, factures);
            } else if (consultBill.getMarchatCode().equalsIgnoreCase(codeMarchandCamwater)) {
                System.out.println("CONSULTATION FACTURE CAMWATER EN MODE " + modeCamwater);
                BillSearchResponseCamwater spr = billCamwaterService.consultBillsCamwater(compagnie.getCodePartenaire(), consultBill.getSearchType(), consultBill.getSearchValue(), requestModeCamwater);
                System.out.println("spr = " + spr);
                if (spr.getCode() == 200 && spr.getData().size() > 0) {
                    for (ResponseCamwater bill : spr.getData()) {
                        System.out.println("bill = " + bill);
                        Facture facture = getFactureFromCamwaterSearch(bill, compagnie, usr, consultBill.getSearchValue());
                        System.out.println("facture = " + facture);
                        // calcul de frais facture
                        GenericsResponse<Facture> calculFees = billEneoService.calculFeesEneo(compagnie, guichet, service.getId(), service.getNom(), facture, usr.getUsrId(), usr.getUsrLogin());
                        if (calculFees.getResponseCode() == 200) {
                            GenericsResponse<CheckBillResponse> precheckFees = billEneoService.precheckFeesEneo(compagnie, guichet, service, facture.getMontant(), usr);
                            System.out.println("precheckFees res code = " + precheckFees.getResponseCode());
                            if (precheckFees.getResponseCode() == 200) {
                                facture = factureService.createFctureDb(facture);
                                System.out.println("facture id = " + facture.getId());
                                factures.add(facture);
                                spr.setCode(200);
                            } else {
                                spr.setMessage("precheckFees Error = " + precheckFees.getResponseDescription() + " ");
                                spr.setCode(precheckFees.getResponseCode());
                            }
                        } else {
                            spr.setMessage("calculFees Error = " + calculFees.getResponseDescription() + " ");
                            spr.setCode(calculFees.getResponseCode());
                        }
                    }
                }
                result = new BillResponse(spr.getCode(), spr.getMessage(), factures);
            } else if (consultBill.getMarchatCode().equalsIgnoreCase(codeMarchandUnivYde1)) {
                System.out.println("CONSULTATION DE DROIT UNIVERSITAIRE UNIV YDE 1");
            } else if (consultBill.getMarchatCode().equalsIgnoreCase(codeMarchandUnivDsc)) {
                System.out.println("CONSULTATION DE DROIT UNIVERSITAIRE UNIV DSC");
            } else if (consultBill.getMarchatCode().equalsIgnoreCase(codeMarchandUnivYde2)) {
                System.out.println("CONSULTATION DE DROIT UNIVERSITAIRE UNIV YDE 2");
            } else {
                // error message
                result = new BillResponse(404, UtilsBillPay.MSG_EROOR_MARCHAND_CODE_NOT_FOUND, new ArrayList<>());
            }
        } catch (Exception e) {
            result = new BillResponse(500, e.getMessage());
        }
        return result;
    }

    @Override
    public GenericsResponse<TransactionBill> PayBill(PayBill payBill, Facture facture) {
        GenericsResponse<TransactionBill> result = new GenericsResponse<TransactionBill>();
        try {
            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(payBill.getServiceCode())).getT(); // find service

            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());

            /*Utilisateur usr = (utilisateurFacadeLocal.getUtilisateurById(payBill.getUserId())).getT(); // find user
            Guichet guichet = new Guichet();
            if (usr.getUsrTypeUo().equalsIgnoreCase("GUICHET")) {
                guichet = guichetFacadeLocal.find(Long.valueOf(usr.getUsrUoId())); // find guichet
            }*/


            Compagnie merchant = facture.getCompagnie();
            facture.setMontant(BigDecimal.valueOf(payBill.getMontant()));

            // find mode
            String requestModeEneo = "";
            String requestModeCamwater = "";

            ParametreBase modeEneo = parametreBaseService.find(UtilsBillPay.BILLPAY_ENEO_MODE);
            if (modeEneo != null) {
                requestModeEneo = modeEneo.getValeur();
            } else {
                result = new GenericsResponse<>(404, UtilsBillPay.MSG_EROOR_BILLPAY_ENEO_MODE_NOT_FOUND, null);
            }

            ParametreBase modeCamwater = parametreBaseService.find(UtilsBillPay.BILLPAY_ENEO_MODE);
            if (modeCamwater != null) {
                requestModeCamwater = modeCamwater.getValeur();
            } else {
                result = new GenericsResponse<>(404, UtilsBillPay.MSG_EROOR_BILLPAY_CAMWATER_MODE_NOT_FOUND, null);
            }

            // initialise marchat code
            String codeMarchandEneo = "";
            String codeMarchandCamwater = "";
            String codeMarchandUnivYde1 = "";
            String codeMarchandUnivDsc = "";
            String codeMarchandUnivYde2 = "";
            // find merchand code
            ParametreBase marchandEneo = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_ENEO);
            if (marchandEneo != null) {
                codeMarchandEneo = marchandEneo.getValeur();
            }

            ParametreBase marchandCamwater = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_CAMWATER);
            if (marchandCamwater != null) {
                codeMarchandCamwater = marchandCamwater.getValeur();
            }

            ParametreBase marchandUnivYde1 = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_YDE1);
            if (marchandEneo != null) {
                codeMarchandUnivYde1 = marchandUnivYde1.getValeur();
            }

            ParametreBase marchandUnivDsc = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_DSC);
            if (marchandUnivDsc != null) {
                codeMarchandUnivDsc = marchandUnivDsc.getValeur();
            }

            ParametreBase marchandUnivYde2 = parametreBaseService.find(UtilsBillPay.CODE_MARCHAND_UNIV_YDE2);
            if (marchandUnivYde2 != null) {
                codeMarchandUnivYde2 = marchandUnivYde2.getValeur();
            }

            if (facture.getCompagnie().getCodePartenaire().equalsIgnoreCase(codeMarchandEneo)) {
                System.out.println("PAIEMENT DE FACTURE ENEO");
                result = billEneoService.payBillEneo(merchant, guichet, facture, service, usr, payBill.getDepositorName(), payBill.getDepositorPhone(), requestModeEneo);
            } else if (facture.getCompagnie().getCodePartenaire().equalsIgnoreCase(codeMarchandCamwater)) {
                System.out.println("PAIEMENT DE FACTURE CAMWATER");
                result = billCamwaterService.payBillCamwater(merchant, guichet, facture, service, usr, payBill.getDepositorName(), payBill.getDepositorPhone(), requestModeCamwater);
            } else if (facture.getCompagnie().getCodePartenaire().equalsIgnoreCase(codeMarchandUnivYde1)) {
                System.out.println("PAIEMENT DES DROITS UNIVERSITAIRES UNIVERSITE DE YAOUNDE 1");
                //return billUnivFacadeLocal.payYde1(merchant, guichet, facture, serviceID, serviceName, BigDecimal.valueOf(userID), userLogin, depositorName, depositorPhone);
            } else if (facture.getCompagnie().getCodePartenaire().equalsIgnoreCase(codeMarchandUnivDsc)) {
                System.out.println("PAIEMENT DES DROITS UNIVERSITAIRES UNIVERSITE DE DSCHANG");
                //return billUnivFacadeLocal.payUds(merchant, guichet, facture, serviceID, serviceName, BigDecimal.valueOf(userID), userLogin, depositorName, depositorPhone);
            } else if (facture.getCompagnie().getCodePartenaire().equalsIgnoreCase(codeMarchandUnivYde2)) {
                System.out.println("PAIEMENT DES DROITS UNIVERSITAIRES UNIVERSITE DE YAOUNDE 2");
                //return billUnivFacadeLocal.payYde2(merchant, guichet, facture, serviceID, serviceName, BigDecimal.valueOf(userID), userLogin, depositorName, depositorPhone);
            } else {
                result = new GenericsResponse<>(404, UtilsBillPay.MSG_EROOR_MARCHAND_CODE_NOT_FOUND, null);
            }
        } catch (Exception e) {
            result = new GenericsResponse<>(500, e.getMessage(), null);
        }
        return result;
    }

    public Facture getFactureFromEneoSearch(Facture facture, Bill bill, Compagnie compagnie, Utilisateur usr) {
        facture.setCodeCompagnie(compagnie.getCodePartenaire());
        facture.setDateCreate(new Date(bill.getBillGenerationDate()));
        facture.setDateLimite(new Date(bill.getBillDueDate()));
        facture.setDateModif(new Date());
        facture.setFact1(bill.getMeterNumber());
        facture.setFact2(null);
        facture.setFact3(null);
        facture.setFact4(null);
        facture.setFact5(null);
        facture.setFactureLibelle(UtilsBillPay.FACTURE_LIBELLE_ENEO);
        facture.setFactureNumber(bill.getBillNumber());
        facture.setMontant(BigDecimal.valueOf(bill.getBillAmount()));
        facture.setNomAbonne(bill.getCustomerName());
        facture.setNumeroAbonne(bill.getCustomerId());
        facture.setStatusFacture("N");
        facture.setUserCreate(usr.getUsrLogin());
        facture.setUserModif(usr.getUsrLogin());
        facture.setMinToPay(BigDecimal.valueOf(bill.getBillAmount()));
        facture.setCompagnie(compagnie);
        return facture;
    }

    public Facture getFactureFromCamwaterSearch(ResponseCamwater bill, Compagnie compagnie, Utilisateur usr, String numeroAbonne) {
        Facture facture = new Facture();
        facture.setCodeCompagnie(compagnie.getCodePartenaire());
        facture.setDateCreate(new Date());
        if (bill.getDateLimit() != null) {
            facture.setDateLimite(new Date(bill.getDateLimit().replace("-", "/")));
        }
        facture.setDateModif(new Date());
        facture.setFact1(null);
        facture.setFact2(bill.getUnpaid() + "");
        facture.setFact3(bill.getPenalty() + "");
        facture.setFact4(bill.getMonthFlow() + "");
        facture.setFact5(null);
        facture.setFactureLibelle(UtilsBillPay.FACTURE_LIBELLE_CAMWATER);
        facture.setFactureNumber(new Date().getTime() + "");
        facture.setMontant(BigDecimal.valueOf(bill.getBalance()));
        facture.setNomAbonne(bill.getCustomerName());
        facture.setNumeroAbonne(numeroAbonne);
        facture.setStatusFacture("N");
        facture.setUserCreate(usr.getUsrLogin());
        facture.setUserModif(usr.getUsrLogin());
        facture.setMinToPay(BigDecimal.valueOf(bill.getMinToPay()));
        facture.setCompagnie(compagnie);
        return facture;
    }
}
