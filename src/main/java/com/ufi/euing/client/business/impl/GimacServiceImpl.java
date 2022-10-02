package com.ufi.euing.client.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufi.euing.client.api.GimacApi;
import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.gimac.model.GimacUtil;
import com.ufi.euing.client.integration.gimac.model.ReceiverCustomerData;
import com.ufi.euing.client.integration.gimac.model.SenderCustomerData;
import com.ufi.euing.client.integration.gimac.model.WalletIncomingRemittanceRequest;
import com.ufi.euing.client.integration.gimac.response.AccessTokenResponse;
import com.ufi.euing.client.integration.gimac.response.WalletIncomingRemittanceResponse;
import com.ufi.euing.client.integration.gimac.service.GimacCallApi;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.GuichetRepository;
import com.ufi.euing.client.repositories.ParametreBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GimacServiceImpl implements GimacService {

    private static final Logger log = LoggerFactory.getLogger(GimacServiceImpl.class);

    final EuingProperties euingProperties;

    final ParametreBaseService parametreBaseService;

    final ParametrewsService parametrewsService;

    final TransactionEuingService transactionEuingService;

    final UtilisateurService utilisateurService;

    final SenderService senderService;

    final BeneficiaryService beneficiaryService;

    final GuichetService guichetService;

    final CompagnieService compagnieService;

    final GuichetRepository guichetRepository;

    final ParametreBaseRepository parametreBaseRepository;

    final ServiceService serviceService;

    final PaysService paysService;

    final TypePieceIdentiteService typePieceIdentiteService;

    public GimacServiceImpl(EuingProperties euingProperties,
                            ParametreBaseService parametreBaseService,
                            ParametrewsService parametrewsService,
                            TransactionEuingService transactionEuingService,
                            UtilisateurService utilisateurService,
                            SenderService senderService,
                            BeneficiaryService beneficiaryService,
                            GuichetService guichetService,
                            CompagnieService compagnieService,
                            GuichetRepository guichetRepository,
                            ParametreBaseRepository parametreBaseRepository,
                            ServiceService serviceService,
                            PaysService paysService,
                            TypePieceIdentiteService typePieceIdentiteService) {
        this.euingProperties = euingProperties;
        this.parametreBaseService = parametreBaseService;
        this.parametrewsService = parametrewsService;
        this.transactionEuingService = transactionEuingService;
        this.utilisateurService = utilisateurService;
        this.senderService = senderService;
        this.beneficiaryService = beneficiaryService;
        this.guichetService = guichetService;
        this.compagnieService = compagnieService;
        this.guichetRepository = guichetRepository;
        this.parametreBaseRepository = parametreBaseRepository;
        this.serviceService = serviceService;
        this.paysService = paysService;
        this.typePieceIdentiteService = typePieceIdentiteService;
    }


    @Override
    public GenericsResponse doGetTarif(GetTransfinQuoteRequest request) {
        GenericsResponse<GetTransfinQuote> response;
        try {

            com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(GimacUtil.GIMAC_CODE_SERVICE)).getData();
            Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());

            log.info("utilisateur Lcp  {}", usr);
            log.info("guichet Lcp  {}", usr);

            Pays po = guichet.getAgence().getCompagnie().getPays();
            String originDev = po.getPsZmCode().getZmDevCode().getDevCode();
            Pays pd = paysService.findPaysByCodeIso(request.getDestinationCountry()).getData();
            String destDev = pd.getPsZmCode().getZmDevCode().getDevCode();
            GenericsResponse<CalculTarifInternational> feesResponse = transactionEuingService.calculTarifInterTransaction(guichet.getAgence().getCompagnie().getId(), guichet.getAgence().getCompagnie().getLibelle(),
                    service.getId(), service.getNom(), pd.getPsCode(), pd.getPsLibelle(), 1, request.getLocalAmount(), usr.getUsrId(), originDev, destDev);
            if (feesResponse.getResponseCode() == 200) {
                CalculTarifInternational tarif = (CalculTarifInternational) feesResponse.getData();
                GetTransfinQuote quote = new GetTransfinQuote();
                quote.setOriginatingCountry(po.getPsCode() + ":" + po.getPsLibelle());
                quote.setDestinationCountry(pd.getPsCode() + ":" + pd.getPsLibelle());
                quote.setOriginatingCurrency(originDev);
                quote.setDestinationCurrency(destDev);
                quote.setLocalAmount(tarif.getAmountSent());
                quote.setCustomerCharge(tarif.getFees());
                quote.setTaxCharged(tarif.getOthersFees());
                quote.setExchangeRate(tarif.getExchangeRate());
                quote.setCashTellerReceiver(tarif.getAmountToPaid());
                quote.setPayoutAmount(tarif.getTotalToPaid());
                response = new GenericsResponse(quote);
            } else {
                response = new GenericsResponse(feesResponse.getResponseCode(), feesResponse.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception do get tarif ::: " + e.getMessage(), null);
        }
        return response;
    }

    @Override
    public GenericsResponse doSendTransaction(TransactionDetailsEntity trxEntity) {
        GenericsResponse<TransactionEuing> response;
        try {
            GenericsResponse<Parametrews> paramRep = parametrewsService.getParametrewsByPartnerCode(GimacUtil.GIMAC_CODE_MARCHAND);
            if (paramRep.getResponseCode() == 200) {
                Parametrews pws = paramRep.getT();
                String url = pws.getPwsWebServiceUrl() + GimacUtil.GIMAC_URI_PAYMENT_SEND;

                Compagnie marchand = compagnieService.findByCodePartenaire(GimacUtil.GIMAC_CODE_MARCHAND);
                com.ufi.euing.client.entity.Service service = (serviceService.getServiceByCode(GimacUtil.GIMAC_CODE_SERVICE)).getData();
                Guichet guichet = guichetService.getById(euingProperties.getGuichetCode());
                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());

                GenericsResponse<TransactionEuing> trxRes = this.doBuildtransaction(trxEntity, pws, usr, service, guichet); //build transaction
                TransactionEuing transaction = new TransactionEuing();
                if (trxRes.getResponseCode() == 200) {
                    transaction = trxRes.getData();
                } else {
                    response = new GenericsResponse(trxRes.getResponseCode(), trxRes.getResponseDescription(), null);
                }

                String sendingCurrencyNumeric = GimacUtil.GIMAC_CURENCY_NUMERIC_DEFAULT;
                ParametreBase paramRep1 = parametreBaseService.find(GimacUtil.GIMAC_CURENCY_NUMERIC);
                if (paramRep1 != null) {
                    sendingCurrencyNumeric = paramRep1.getValeur();
                }

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Long createtime = new Date().getTime();
                String issuertrxref = "";
                TransactionEuing trx = new TransactionEuing();

                String prefix = "";
                ParametreBase paramRep2 = parametreBaseService.find(GimacUtil.GIMAC_PREFIXE_TRANSACTION);
                if (paramRep2 != null) {
                    prefix = paramRep2.getValeur();
                }

                if (transaction.getTransId() == null) {
                    GenericsResponse<TransactionEuing> restrx = transactionEuingService.createNewInterTransactionC2C(transaction);
                    if (restrx.getResponseCode() == 200) {
                        trx = restrx.getT();
                        issuertrxref = trx.getTransReference().replace(prefix, "");
                    } else {
                        response = new GenericsResponse<>(restrx.getResponseCode(), "Error ::: " + restrx.getResponseDescription(), null);
                    }
                } else {
                    trx = transaction;
                    issuertrxref = trx.getTransReference().replace(prefix, "");
                }

                WalletIncomingRemittanceRequest request = new WalletIncomingRemittanceRequest();
                request.setIssuertrxref(issuertrxref);
                request.setIntent(GimacUtil.GIMAC_AMA_INTENT_INC_WAL_REMIT);
                request.setCreatetime(createtime);
                request.setSendermobile(trx.getTransSenPhone());
                request.setDescription(trx.getTransMessage());
                request.setTomember(trx.getTransMtpartnercode()); // FromMenber = nous ToMember = destinataire
                request.setAmount(trx.getTransAmountToPaid().doubleValue());
                request.setCurrency(sendingCurrencyNumeric);
                request.setWalletdestination(trx.getNg5());

                SenderCustomerData sender = new SenderCustomerData();
                sender.setFirstname(trx.getTransSenFirstname());
                sender.setSecondname(trx.getTransSenLastname());
                sender.setIdtype(trx.getTransSenIdtype());
                sender.setIdnumber(trx.getTransSenIdnumber());
                sender.setAddress(trx.getTransSenAddress());
                sender.setBirthdate((trx.getTransSenId().getSenDob() != null) ? dateFormat.format(trx.getTransSenId().getSenDob()) : "");
                request.setSendercustomerdata(sender);
                System.out.println("request sender ::: " + sender);

                ReceiverCustomerData receiver = new ReceiverCustomerData();
                receiver.setFirstname(trx.getTransBenFirstname());
                receiver.setSecondname(trx.getTransBenLastname());
                receiver.setIdtype(trx.getTransBenIdtype());
                receiver.setIdnumber(trx.getTransBenIdnumber());
                receiver.setAddress(trx.getTransBenAddress());
                receiver.setBirthdate((trx.getTransBenId().getBenDob() != null) ? dateFormat.format(trx.getTransBenId().getBenDob()) : "");
                receiver.setCity(trx.getTransBenCity());
                receiver.setCountry(trx.getTransBenCountry());
                receiver.setPhone(trx.getTransBenPhone());
                request.setReceivercustomerdata(receiver);
                System.out.println("request receiver ::: " + receiver);

                System.out.println("request walletIncomingRemittance ::: " + request);
                GenericsResponse<AccessTokenResponse> tokenResponse = oauth();
                System.out.println("tokenResponse ::: " + tokenResponse);

                if (tokenResponse.getResponseCode() == 200) {
                    AccessTokenResponse token = tokenResponse.getT();
                    String resp = GimacCallApi.callApiGenericGimac(token.getAccess_token(), url, request);
                    System.out.println("resp ::: " + resp);

                    ObjectMapper mapper = new ObjectMapper();
                    WalletIncomingRemittanceResponse remittanceResponse = mapper.readValue(resp, WalletIncomingRemittanceResponse.class);
                    System.out.println("remittanceResponse ::: " + remittanceResponse);

                    if (remittanceResponse.getError() == null || "".equals(remittanceResponse.getError())) {
                        trx.setTransMttransactionnumber(remittanceResponse.getAcquirertrxref());
                        trx.setTransMttransactionid(remittanceResponse.getVouchercode());
                        trx.setTransMtstatus(remittanceResponse.getState());
                        trx.setTransMtobservation(remittanceResponse.getDescription());
                        trx.setTransStatut("G");
                        GenericsResponse<TransactionEuing> respSave = transactionEuingService.updateTransaction(trx);
                        if (respSave.getResponseCode() == 200) {
                            TransactionEuing transactionEuing = respSave.getT();
                            response = new GenericsResponse<>(transactionEuing);
                        } else {
                            response = new GenericsResponse<>(respSave.getResponseCode(), "Error ::: " + respSave.getResponseDescription(), null);
                        }
                    } else {
                        response = new GenericsResponse<>(trx);
                        //result = new GenericsResponse<>(500, "Error ::: " + remittanceResponse.getError() + " Description ::: " + remittanceResponse.getError_description());
                    }
                } else {
                    response = new GenericsResponse<>(tokenResponse.getResponseCode(), "Error during authentication. Message ::: " + tokenResponse.getResponseDescription(), null);
                }
            } else {
                response = new GenericsResponse<>(paramRep.getResponseCode(), "Error Parametre ws " + GimacUtil.GIMAC_CODE_MARCHAND + " not found. Message ::: " + paramRep.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception do send transaction ::: " + e.getMessage(), null);
        }
        return response;
    }


    GenericsResponse<TransactionEuing> doBuildtransaction(TransactionDetailsEntity trxEntity, Parametrews parametrews, Utilisateur usr, com.ufi.euing.client.entity.Service service, Guichet guichet) {
        GenericsResponse<TransactionEuing> response;
        try {
            boolean terminar = false;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Agence agence = guichet.getAgence();
            Compagnie compagnie = agence.getCompagnie();

            Pays originCountry = compagnie.getPays();
            Pays destCountry = (paysService.findPaysByCodeIso(trxEntity.getDestinationCountry())).getData();

            Sender sender = new Sender();
            if (!terminar) {
                System.out.println("sender");
                sender.setSenFirstname(trxEntity.getSenderDetails().getFirstName());
                sender.setSenLastname(trxEntity.getSenderDetails().getLastName());
                sender.setSenCountryId(originCountry);
                sender.setSenCountry(originCountry.getPsLibelle());
                sender.setSenCountryResId(originCountry);
                sender.setSenCountryRes(originCountry.getPsLibelle());
                Pays psn = paysService.find(trxEntity.getSenderIdDetails().getNationality());
                sender.setSenNationality(psn.getPsLibelle());
                sender.setSenNationalityId(psn);
                sender.setSenCityId(agence.getVille());
                sender.setSenCity(agence.getVille().getViLibelle());
                sender.setSenState(trxEntity.getSenderIdDetails().getNationality());
                sender.setSenPostalCode(trxEntity.getSenderDetails().getPostCode());
                sender.setSenPhoneNumber1(trxEntity.getSenderDetails().getMobileNumber());
                sender.setSenPhoneNumber2(trxEntity.getSenderDetails().getPhoneNumber());
                sender.setSenEmail(trxEntity.getSenderDetails().getEmail());
                sender.setSenDob((trxEntity.getSenderIdDetails().getDateOfBirth() == null) ? null : formatter.parse(trxEntity.getSenderIdDetails().getDateOfBirth()));
                sender.setSenIdDocumentDelDate(formatter.parse(trxEntity.getSenderIdDetails().getDateOfIssue()));
                sender.setSenIdDocumentExpDate(formatter.parse(trxEntity.getSenderIdDetails().getDateOfExpiry()));
                sender.setSenIdDocumentNumber(trxEntity.getSenderIdDetails().getId1Number());
                sender.setSenIdDocumentIssueCountry(paysService.find(trxEntity.getSenderIdDetails().getCountryOfIssue()));
                sender.setSenIdDocumentType(typePieceIdentiteService.find(trxEntity.getSenderIdDetails().getCodeTypePiece()));
                sender.setSenGender(trxEntity.getSenderIdDetails().getGender());
                sender.setSenOccupation(trxEntity.getSenderIdDetails().getOccupation());
                sender.setSenAddress(trxEntity.getSenderDetails().getAddress1());
                sender.setSenAddress2(trxEntity.getSenderDetails().getAddress2());
                //create sender
                GenericsResponse<Sender> createSenderResponse = senderService.createSender(sender);
                if (createSenderResponse.getResponseCode() == 200) {
                    sender = createSenderResponse.getData();
                } else {
                    response = new GenericsResponse(createSenderResponse.getResponseCode(), "Error create sender ::: " + createSenderResponse.getResponseDescription(), null);
                    terminar = true;
                }
                System.out.println("sennder created ::: " + sender);
            }

            Beneficiary beneficiary = new Beneficiary();
            if (!terminar) {
                System.out.println("benef");
                beneficiary.setBenFirstname(trxEntity.getReceiverDetails().getFirstName());
                beneficiary.setBenLastname(trxEntity.getReceiverDetails().getLastName());
                beneficiary.setBenCountryId(destCountry);
                beneficiary.setBenCountry(destCountry.getPsLibelle());
                //Pays pdn = paysFacadeLocal.find(trxEntity.getReceiverIdDetails().getNationality());
                beneficiary.setBenNationalityId(destCountry);
                beneficiary.setBenNationality(destCountry.getPsLibelle());
                Ville ville = transactionEuingService.getCityByName("OTHERS").getData();
                beneficiary.setBenCityId(ville);
                beneficiary.setBenCity(ville.getViLibelle());
                beneficiary.setBenState(trxEntity.getReceiverDetails().getState());
                beneficiary.setBenPostalCode(trxEntity.getReceiverDetails().getPostCode());
                beneficiary.setBenPhoneNumber1(trxEntity.getReceiverDetails().getMobileNumber());
                beneficiary.setBenPhoneNumber2(trxEntity.getReceiverDetails().getPhoneNumber());
                beneficiary.setBenEmail(trxEntity.getReceiverDetails().getEmail());
                beneficiary.setBenGender("ND");
                beneficiary.setBenAddress(trxEntity.getReceiverDetails().getAddress1());
                beneficiary.setBenAddress2(trxEntity.getReceiverDetails().getAddress2());
                //create benef
                GenericsResponse<Beneficiary> createBenfResponse = beneficiaryService.createBeneficiary(sender.getSenId(), beneficiary);
                if (createBenfResponse.getResponseCode() == 200) {
                    beneficiary = createBenfResponse.getData();
                } else {
                    response = new GenericsResponse(createBenfResponse.getResponseCode(), "Error create beneficiary ::: " + createBenfResponse.getResponseDescription(), null);
                }
                System.out.println("beneficiary create ::: " + beneficiary);
            }

            if (!terminar) {
                System.out.println("transaction");
                TransactionEuing trx = new TransactionEuing();

                //Guichet transaction
                trx.setTransGuichetId(guichet);
                trx.setTransGuichet(guichet.getLibelle());

                //Agence transaction
                trx.setTransAgencyId(agence);
                trx.setTransAgency(agence.getLibelle());

                //Compagnie transaction
                trx.setTransCompanyId(compagnie);
                trx.setTransCompany(compagnie.getLibelle());

                //Groupe transaction
                Succursale succursale = compagnie.getSuccursale();
                trx.setTransGroupId(succursale);
                trx.setTransGroup(succursale.getLibelle());

                //Utilisateur transaction
                trx.setTransCasher(usr.getUsrPrenom() + " " + usr.getUsrNom());
                trx.setTransUserid(usr);

                //origin country transaction
                trx.setTransOriginCountryId(originCountry);
                trx.setTransOriginCountry(originCountry.getPsLibelle());

                //destination country transaction
                trx.setTransDestCountryId(beneficiary.getBenCountryId());
                trx.setTransDestCountry(beneficiary.getBenCountry());

                //Origin Currency transaction
                trx.setTransOriginCur(originCountry.getPsZmCode().getZmDevCode());

                //Destination Currency transaction
                trx.setTransDestCur(destCountry.getPsZmCode().getZmDevCode());

                //Service Transaction
                trx.setTransServiceId(service);
                trx.setTransServiceName(service.getNom());

                //Sender Transaction
                trx.setTransSenId(sender);
                trx.setTransSenFirstname(sender.getSenFirstname());
                trx.setTransSenLastname(sender.getSenLastname());
                trx.setTransSenCountry(sender.getSenCountry());
                trx.setTransSenCity(sender.getSenCity());
                trx.setTransSenPostalCode(sender.getSenPostalCode());
                trx.setTransSenAddress(sender.getSenAddress());
                trx.setTransSenPhone(sender.getSenPhoneNumber1());
                trx.setTransSenEmail(sender.getSenEmail());
                trx.setTransSenIdnumber(sender.getSenIdDocumentNumber());
                trx.setTransSenIdtype(sender.getSenIdDocumentType().getTpiCode());

                //Beneficiary Transaction
                trx.setTransBenId(beneficiary);
                trx.setTransBenFirstname(beneficiary.getBenFirstname());
                trx.setTransBenLastname(beneficiary.getBenLastname());
                trx.setTransBenAddress(beneficiary.getBenAddress());
                trx.setTransBenCity(beneficiary.getBenCity());
                trx.setTransBenCountry(beneficiary.getBenCountry());
                trx.setTransBenEmail(beneficiary.getBenEmail());
                trx.setTransBenIdnumber(beneficiary.getBenIdDocumentNumber());
                trx.setTransBenIdtype((beneficiary.getBenIdDocumentType() != null) ? beneficiary.getBenIdDocumentType().getTpiCode() : "");
                trx.setTransBenPhone(beneficiary.getBenPhoneNumber1());
                trx.setTransBenPostalCode(beneficiary.getBenPostalCode());

                //Transaction money
                trx.setTransMessage("ND");
                trx.setTransMotif("ND");
                trx.setTransAmountSent(BigDecimal.valueOf(trxEntity.getLocalAmount()));
                trx.setTransAmountToPaid(BigDecimal.valueOf(trxEntity.getPayoutAmount()));
                trx.setTransFees(BigDecimal.valueOf(trxEntity.getCustomerCharge()));
                trx.setTransOthers(BigDecimal.valueOf(trxEntity.getTaxCharged()));
                trx.setTransTotal(BigDecimal.valueOf(trxEntity.getPayoutAmount()));
                trx.setTransExchangeRate(BigDecimal.valueOf(trxEntity.getExchangeRate()));

                //Transaction details
                trx.setTransInterfacage("G");
                trx.setTransStatut("I");
                trx.setTransSens("C2C_ENVOI");
                trx.setTransMotif("ND");
                trx.setTransMessage("ND");
                trx.setTransMtpartnercode(trxEntity.getPartnerTransactionNo());
                trx.setTransParamWs(parametrews);
                //trx.setTransMotif("9.9.9.9.9.9");
                //trx.setTransMessage(trxEntity.getReceiverPaymentDetails().getTestAnswer());
                trx.setNg5(trxEntity.getPartenaire().getCodePartenaire());
                trx.setNg4(trxEntity.getPartenaire().getDenommination());

                trx.setTransServiceId(service);
                trx.setTransServiceName(service.getNom());
                System.out.println("trx = " + trx);
                response = new GenericsResponse(trx);
            } else {
                response = new GenericsResponse(500, "Error create transaction", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new GenericsResponse(500, "Exception doBuildtransaction Envoi ::: " + e.getMessage(), null);
        }
        return response;
    }

    GenericsResponse<AccessTokenResponse> oauth() {
        GenericsResponse<AccessTokenResponse> result;
        try {
            GenericsResponse<Parametrews> paramRep = parametrewsService.getParametrewsByPartnerCode(GimacUtil.GIMAC_CODE_MARCHAND);
            if (paramRep.getResponseCode() == 200) {
                Parametrews pws = paramRep.getT();
                String url = pws.getPwsWebServiceUrl() + GimacUtil.GIMAC_URI_OAUTH;

                String params = "grant_type=" + pws.getAutorized() + "&client_id=" + pws.getPwsMyagentIdPartner() + "&client_secret=" + pws.getPwsMykeyPartner()
                        + "&scope=" + GimacUtil.GIMAC_ACCESS_TOKEN_REQUEST_SCOPE + "&username=" + pws.getLogin() + "&password=" + pws.getPassword();

                String response = GimacCallApi.callApiGimacOauth(url, params);
                System.out.println("response ::: " + response);
                if (response != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    AccessTokenResponse tokenResponse = mapper.readValue(response, AccessTokenResponse.class);
                    System.out.println("tokenResponse ::: " + tokenResponse);

                    if (tokenResponse.getError() == null || "".equals(tokenResponse.getError())) {
                        result = new GenericsResponse<>(tokenResponse);
                    } else {
                        result = new GenericsResponse<>(500, "Code = " + tokenResponse.getError() + " Description = " + tokenResponse.getError_description(), null);
                    }
                } else {
                    result = new GenericsResponse<>(500, "Error connecting to GIMAC server. Possible connection timout. Description " + response, null);
                }
            } else {
                result = new GenericsResponse<>(paramRep.getResponseCode(), "Error Parametre ws " + GimacUtil.GIMAC_CODE_MARCHAND + " not found. Message ::: " + paramRep.getResponseDescription(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new GenericsResponse<>(500, "Exception ::: " + e.getMessage(), null);
        }
        System.out.println("result ::: " + result);
        return result;
    }

    GenericsResponse<TransactionEuing> updateRemittanceStatus(TransactionEuing trx) {
        GenericsResponse<TransactionEuing> result;
        try {
            TransactionEuing transaction = transactionEuingService.find(trx.getTransId());
            System.out.println("transaction ::: " + transaction);
            if (transaction == null) {
                result = new GenericsResponse<>(404, "Transaction with reference " + trx.getTransReference() + " not found!!!", null);
            } else {
                GenericsResponse<TransactionEuing> respSave = transactionEuingService.updateTransaction(trx);
                if (respSave.getResponseCode() == 200) {
                    TransactionEuing transactionEuing = respSave.getT();
                    result = new GenericsResponse<>(transactionEuing);
                } else {
                    result = new GenericsResponse<>(respSave.getResponseCode(), "Error ::: " + respSave.getResponseDescription(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new GenericsResponse<>(500, "Une erreur s'est produite lors du update remittance status. Détails ::: " + e.getMessage(), null);
        }
        return result;
    }
}
