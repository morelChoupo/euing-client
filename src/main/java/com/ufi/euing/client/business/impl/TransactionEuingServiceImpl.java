package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.*;
import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.props.EuingProperties;
import com.ufi.euing.client.repositories.TransactionEuingRepository;
import com.ufi.euing.client.response.ResponseSimulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TransactionEuingServiceImpl extends AbstractFacade<TransactionEuing> implements TransactionEuingService {

    @Autowired
    private EntityManager em;

    public TransactionEuingServiceImpl() {
        super(TransactionEuing.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    TransactionEuingRepository transactionEuingRepository;

    @Autowired
    VwTransactionEuingService vwTransactionEuingService;

    @Autowired
    EuingProperties euingProperties;

    @Autowired
    UtilisateurService utilisateurService;


    @Autowired
    private BeneficiaryService beneficiaryService;

    private static final Logger log = LoggerFactory.getLogger(TransactionEuingServiceImpl.class);


    @Override
    public ReferenceEUI giveRerence() {
        ReferenceEUI retour = new ReferenceEUI();
        Integer res = null;
        String res_message = "";
        String reference = "";
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_GENERATE_REFERENCE_EUI");
        storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
        storedProcedure.registerStoredProcedureParameter("pREFERENCE", String.class, ParameterMode.OUT);
        storedProcedure.setParameter("pRES", res);
        storedProcedure.setParameter("pRES_MESSAGE", res_message);
        storedProcedure.setParameter("pREFERENCE", reference);

        // execute stored procedure
        storedProcedure.execute();
        res = (Integer) storedProcedure.getOutputParameterValue("pRES");
        res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
        reference = (String) storedProcedure.getOutputParameterValue("pREFERENCE");
        retour.setReference(reference);
        retour.setRes_message(res_message);
        retour.setRes(res);
        return retour;
    }

    @Override
    public GenericsResponse<CalculTarifInternational> calculTarifInterTransaction(Long companyId, String companyName, Long serviceId, String serviceName, String destinationCountryCode, String destinationCountryLibelle, int mode, double amount, BigDecimal senderId, String originCurrency, String destinationCurrency) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_CAL_TARIF_INTER_TRANSACTION");
            // set parameters

            Double amountSent = 0.0;
            Double amountToPaid = 0.0;
            Double totalToPaid = 0.0;
            Double fees = 0.0;
            Double othersFees = 0.0;
            Double exchangeRate = 0.0;

            Integer res = 0;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pCOMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pDESTINATION_COUNTRY_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pCALCULATION_MODE", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pAMOUNT", Double.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSENDER_ID", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pAMOUNT_TO_SENT", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pAMOUNT_TO_PAID", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pTOTAL_TO_PAID", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pFEES", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pOTHERS_FEES", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pEXCHANGE_RATE", Double.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.INOUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.INOUT);

            storedProcedure.setParameter("pCOMPANY_ID", companyId);
            storedProcedure.setParameter("pSERVICE_ID", serviceId);
            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", originCurrency);
            storedProcedure.setParameter("pTRANS_DEST_CUR", destinationCurrency);
            storedProcedure.setParameter("pDESTINATION_COUNTRY_CODE", destinationCountryCode);
            storedProcedure.setParameter("pCALCULATION_MODE", mode);
            storedProcedure.setParameter("pAMOUNT", amount);
            storedProcedure.setParameter("pSENDER_ID", senderId);

            storedProcedure.setParameter("pAMOUNT_TO_SENT", amountSent);
            storedProcedure.setParameter("pAMOUNT_TO_PAID", amountToPaid);
            storedProcedure.setParameter("pTOTAL_TO_PAID", totalToPaid);
            storedProcedure.setParameter("pFEES", fees);
            storedProcedure.setParameter("pOTHERS_FEES", othersFees);
            storedProcedure.setParameter("pEXCHANGE_RATE", exchangeRate);
            storedProcedure.setParameter("pRES", res);
            storedProcedure.setParameter("pRES_MESSAGE", res_message);

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            log.info("Res ===> {}", res);
            log.info("RES_MESSAGE ===> {}", res_message);
            amountSent = (double) storedProcedure.getOutputParameterValue("pAMOUNT_TO_SENT");
            amountToPaid = (double) storedProcedure.getOutputParameterValue("pAMOUNT_TO_PAID");
            totalToPaid = (double) storedProcedure.getOutputParameterValue("pTOTAL_TO_PAID");
            fees = (double) storedProcedure.getOutputParameterValue("pFEES");
            othersFees = (double) storedProcedure.getOutputParameterValue("pOTHERS_FEES");
            exchangeRate = (double) storedProcedure.getOutputParameterValue("pEXCHANGE_RATE");
            log.info("FEES ===> {}", fees);
            return new GenericsResponse<>(new CalculTarifInternational(res, res_message, amountSent, amountToPaid, totalToPaid, fees, othersFees, exchangeRate));

        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> createNewInterTransactionC2C(TransactionEuing trx) {
        try {
            log.info("TransactionEuing === >  {}", trx.toString());
            Integer res = null;
            BigDecimal trans_id = null;
            String res_message = "";
            StoredProcedureQuery storedProcedure;
            if (trx.getTransId() == null) {
                log.info("trx.getTransServiceId().getCode() === > {}", trx.getTransServiceId().getCode());
                if (trx.getTransServiceId().getCode().equalsIgnoreCase("ENVOI_THUNES_C2C_INTL")) {
                    storedProcedure = em.createStoredProcedureQuery("SP_INSERT_TRX_INTER_C2C_THUNES");
                    storedProcedure.registerStoredProcedureParameter("pTRANS_MTSTATUS", String.class, ParameterMode.IN);
                    storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONID", String.class, ParameterMode.IN);
                    storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);
                    storedProcedure.registerStoredProcedureParameter("pTRANS_MTOBSERVATION", String.class, ParameterMode.IN);

                    storedProcedure.setParameter("pTRANS_MTSTATUS", trx.getTransMtstatus());
                    storedProcedure.setParameter("pTRANS_MTTRANSACTIONID", trx.getTransMttransactionid());
                    storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());
                    storedProcedure.setParameter("pTRANS_MTOBSERVATION", trx.getTransMtobservation());
                } else {
                    if (trx.getTransServiceId().getCode().equalsIgnoreCase("ENVOI_JUBA_C2C_INTL")) {
                        storedProcedure = em.createStoredProcedureQuery("SP_INSERT_TRX_INTER_C2C_JUBA");
                        storedProcedure.registerStoredProcedureParameter("pTRANS_MTSTATUS", String.class, ParameterMode.IN);
                        storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONID", String.class, ParameterMode.IN);
                        storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);
                        storedProcedure.registerStoredProcedureParameter("pTRANS_MTOBSERVATION", String.class, ParameterMode.IN);
                        storedProcedure.registerStoredProcedureParameter("pTRANS_NG4", String.class, ParameterMode.IN);
                        storedProcedure.registerStoredProcedureParameter("pTRANS_NG5", String.class, ParameterMode.IN);

                        storedProcedure.setParameter("pTRANS_MTSTATUS", trx.getTransMtstatus());
                        storedProcedure.setParameter("pTRANS_MTTRANSACTIONID", trx.getTransMttransactionid());
                        storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());
                        storedProcedure.setParameter("pTRANS_MTOBSERVATION", trx.getTransMtobservation());
                        storedProcedure.setParameter("pTRANS_NG4", trx.getNg4());
                        storedProcedure.setParameter("pTRANS_NG5", trx.getNg5());
                    } else {
                        if (trx.getTransServiceId().getCode().equalsIgnoreCase("ENVOI_MFS_C2C_INTL")) {
                            storedProcedure = em.createStoredProcedureQuery("SP_INSERT_TRX_INTER_C2C_MFS");
                            storedProcedure.registerStoredProcedureParameter("pTRANS_MTSTATUS", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONID", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_MTOBSERVATION", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG4", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG5", String.class, ParameterMode.IN);

                            storedProcedure.setParameter("pTRANS_MTSTATUS", trx.getTransMtstatus());
                            storedProcedure.setParameter("pTRANS_MTTRANSACTIONID", trx.getTransMttransactionid());
                            storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());
                            storedProcedure.setParameter("pTRANS_MTOBSERVATION", trx.getTransMtobservation());
                            storedProcedure.setParameter("pTRANS_NG4", trx.getNg4());
                            storedProcedure.setParameter("pTRANS_NG5", trx.getNg5());
                        } else {
                            storedProcedure = em.createStoredProcedureQuery("SP_INSERT_TRX_INTER_C2C");
                            log.info("trx.getTransServiceId().getCode() === > {}", trx.getTransServiceId().getCode());
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG4", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG5", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG1", String.class, ParameterMode.IN);
                            storedProcedure.registerStoredProcedureParameter("pTRANS_NG2", String.class, ParameterMode.IN);
                            //storedProcedure.registerStoredProcedureParameter("pTRANS_INTERFACAGE", String.class, ParameterMode.IN);
                            storedProcedure.setParameter("pTRANS_NG4", trx.getNg4());
                            storedProcedure.setParameter("pTRANS_NG5", trx.getNg5());
                            storedProcedure.setParameter("pTRANS_NG1", trx.getNg1());
                            storedProcedure.setParameter("pTRANS_NG2", trx.getNg2());
                            //storedProcedure.setParameter("pTRANS_INTERFACAGE", trx.getTransInterfacage());
                            //TRANS_INTERFACAGE
                        }
                    }
                }
                // set parameters

                storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
                storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
                storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.OUT);
                storedProcedure.registerStoredProcedureParameter("pTRANS_GUICHET_ID", Long.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_GUICHET", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_AGENCY_ID", Long.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_AGENCY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY_ID", Long.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_GROUP", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_GROUP_ID", Long.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_CASHER", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_USERID", BigInteger.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_SENT", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_OTHERS", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_FEES", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_REMISE", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_TAXES", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_TOTAL", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", BigDecimal.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_INTERFACAGE", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_STATUT", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SENS", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ID", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_FIRSTNAME", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_LASTNAME", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_COUNTRY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_CITY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_POSTAL_CODE", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ADDRESS", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_PHONE", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_EMAIL", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDNUMBER", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDTYPE", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ID", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_FIRSTNAME", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_LASTNAME", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_COUNTRY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_CITY", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_POSTAL_CODE", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ADDRESS", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_PHONE", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_EMAIL", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDNUMBER", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDTYPE", String.class, ParameterMode.IN);

                storedProcedure.registerStoredProcedureParameter("pTRANS_MOTIF", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_MESSAGE", String.class, ParameterMode.IN);

                storedProcedure.setParameter("pRES", null);
                storedProcedure.setParameter("pRES_MESSAGE", null);
                storedProcedure.setParameter("pTRANS_ID", null);
                storedProcedure.setParameter("pTRANS_GUICHET_ID", trx.getTransGuichetId().getId());
                storedProcedure.setParameter("pTRANS_GUICHET", trx.getTransGuichet());
                storedProcedure.setParameter("pTRANS_AGENCY_ID", trx.getTransAgencyId().getId());
                storedProcedure.setParameter("pTRANS_AGENCY", trx.getTransAgency());
                storedProcedure.setParameter("pTRANS_COMPANY", trx.getTransCompany());
                storedProcedure.setParameter("pTRANS_COMPANY_ID", trx.getTransCompanyId().getId());
                storedProcedure.setParameter("pTRANS_GROUP", trx.getTransGroup());
                storedProcedure.setParameter("pTRANS_GROUP_ID", trx.getTransGroupId().getId());

                storedProcedure.setParameter("pTRANS_CASHER", trx.getTransCasher());
                storedProcedure.setParameter("pTRANS_USERID", trx.getTransUserid().getUsrId().toBigInteger());

                storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", trx.getTransOriginCountry());
                storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", trx.getTransOriginCountryId().getPsCode());
                storedProcedure.setParameter("pTRANS_DEST_COUNTRY", trx.getTransDestCountry());
                storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", trx.getTransDestCountryId().getPsCode());

                storedProcedure.setParameter("pTRANS_ORIGIN_CUR", trx.getTransOriginCur().getDevCode());
                storedProcedure.setParameter("pTRANS_DEST_CUR", trx.getTransDestCur().getDevCode());

                storedProcedure.setParameter("pTRANS_AMOUNT_SENT", trx.getTransAmountSent());
                storedProcedure.setParameter("pTRANS_OTHERS", trx.getTransOthers());
                storedProcedure.setParameter("pTRANS_FEES", trx.getTransFees());
                storedProcedure.setParameter("pTRANS_REMISE", trx.getTransRemise());
                storedProcedure.setParameter("pTRANS_TAXES", trx.getTransTaxes());
                storedProcedure.setParameter("pTRANS_TOTAL", trx.getTransTotal());
                storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", trx.getTransAmountToPaid());

                storedProcedure.setParameter("pTRANS_INTERFACAGE", trx.getTransInterfacage());
                storedProcedure.setParameter("pTRANS_STATUT", trx.getTransStatut());
                storedProcedure.setParameter("pTRANS_SENS", trx.getTransSens());

                storedProcedure.setParameter("pTRANS_SERVICE_ID", trx.getTransServiceId().getId());
                storedProcedure.setParameter("pTRANS_SERVICE_NAME", trx.getTransServiceName());

                storedProcedure.setParameter("pTRANS_SEN_ID", trx.getTransSenId().getSenId());
                storedProcedure.setParameter("pTRANS_SEN_FIRSTNAME", trx.getTransSenFirstname());
                storedProcedure.setParameter("pTRANS_SEN_LASTNAME", trx.getTransSenLastname());
                storedProcedure.setParameter("pTRANS_SEN_COUNTRY", trx.getTransSenCountry());
                storedProcedure.setParameter("pTRANS_SEN_CITY", trx.getTransSenCity());
                storedProcedure.setParameter("pTRANS_SEN_POSTAL_CODE", trx.getTransSenPostalCode());
                storedProcedure.setParameter("pTRANS_SEN_ADDRESS", trx.getTransSenAddress());
                storedProcedure.setParameter("pTRANS_SEN_PHONE", trx.getTransSenPhone());
                storedProcedure.setParameter("pTRANS_SEN_EMAIL", trx.getTransSenEmail());
                storedProcedure.setParameter("pTRANS_SEN_IDNUMBER", trx.getTransSenIdnumber());
                storedProcedure.setParameter("pTRANS_SEN_IDTYPE", trx.getTransSenIdtype());

                storedProcedure.setParameter("pTRANS_BEN_ID", trx.getTransBenId().getBenId());
                storedProcedure.setParameter("pTRANS_BEN_FIRSTNAME", trx.getTransBenFirstname());
                storedProcedure.setParameter("pTRANS_BEN_LASTNAME", trx.getTransBenLastname());
                storedProcedure.setParameter("pTRANS_BEN_COUNTRY", trx.getTransBenCountry());
                storedProcedure.setParameter("pTRANS_BEN_CITY", trx.getTransBenCity());
                storedProcedure.setParameter("pTRANS_BEN_POSTAL_CODE", trx.getTransBenPostalCode());
                storedProcedure.setParameter("pTRANS_BEN_ADDRESS", trx.getTransBenAddress());
                storedProcedure.setParameter("pTRANS_BEN_PHONE", trx.getTransBenPhone());
                storedProcedure.setParameter("pTRANS_BEN_EMAIL", trx.getTransBenEmail());
                storedProcedure.setParameter("pTRANS_BEN_IDNUMBER", trx.getTransBenIdnumber());
                storedProcedure.setParameter("pTRANS_BEN_IDTYPE", trx.getTransBenIdtype());

                storedProcedure.setParameter("pTRANS_MOTIF", trx.getTransMotif());
                storedProcedure.setParameter("pTRANS_MESSAGE", trx.getTransMessage());

                // execute stored procedure
                storedProcedure.execute();
                log.info("################# storedProcedure.execute() ##################");
                res = (Integer) storedProcedure.getOutputParameterValue("pRES");
                res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
                trans_id = (BigDecimal) storedProcedure.getOutputParameterValue("pTRANS_ID");

                if (res == 0) {
                    trx.setTransId(trans_id);
                    TransactionEuing result = transactionEuingRepository.findById(trans_id).orElseThrow(()-> new EmailNotFoundException("Transaction not found"));
                    trx = result;

                    //sender.setSenCode(result.getSenCode());
                    //em.merge(trx);
                    transactionEuingRepository.save(trx);
                    return new GenericsResponse<>(trx);
                } else {
                    return new GenericsResponse<>(500, "Code d'erreur : [ " + res + " ], ===> Message : " + res_message, null);
                }
            } else {
                storedProcedure = em.createStoredProcedureQuery("SP_UPDATE_TRANSACTION_THUNES");
                storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
                storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
                storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_MTSTATUS", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONID", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter("pTRANS_MTOBSERVATION", String.class, ParameterMode.IN);

                storedProcedure.setParameter("pRES", null);
                storedProcedure.setParameter("pRES_MESSAGE", null);
                storedProcedure.setParameter("pTRANS_ID", trx.getTransId());
                storedProcedure.setParameter("pTRANS_MTSTATUS", trx.getTransMtstatus());
                storedProcedure.setParameter("pTRANS_MTTRANSACTIONID", trx.getTransMttransactionid());
                storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());
                storedProcedure.setParameter("pTRANS_MTOBSERVATION", trx.getTransMtobservation());
                storedProcedure.execute();
//                res = (Integer) storedProcedure.getOutputParameterValue("pRES");
//                res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
//                trans_id = (BigDecimal) storedProcedure.getOutputParameterValue("pTRANS_ID");
//                this.edit(trx);
                TransactionEuing result = transactionEuingRepository.findById(trx.getTransId()).orElseThrow(()-> new EmailNotFoundException("Transaction not found"));
                trx = result;
                //this.getEntityManager().merge(trx);
                log.info("result ===> {} ", result.toString());
                return new GenericsResponse<>(trx);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> findTransaction(BigDecimal id) throws EmailNotFoundException {

            return new GenericsResponse<>(transactionEuingRepository.findById(id).orElseThrow(()-> new EmailNotFoundException("Transaction not found")));

    }

    @Override
    public GenericsResponse<TransactionEuing> updateTransaction(TransactionEuing trx) {
        try {
            System.out.println(trx.getTransStatut());
            transactionEuingRepository.save(trx);
            return new GenericsResponse<>(trx);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), null);
        }
    }

    @Override
    public List<TransactionEuing> searchTransactions(Date searchDateStart, Date searchDateEnd, Succursale searchGroupeId, Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId, String searchReference, String searchCodeClient, String searchExpediteur, String searchBeneficiaire, Pays searchDestinationCode, String searchStatut, Service searchServiceId, String searchIdSender, String searchIdBenef, String searchPhoneSender, String searchPhoneBenef) {
        return convertVwtrxToTrxeuingNew(vwTransactionEuingService.searchTransactions(searchDateStart, searchDateEnd, searchGroupeId, searchCompagnieId, searchAgenceId, searchGuichetId, searchReference, searchCodeClient, searchExpediteur, searchBeneficiaire, searchDestinationCode, searchStatut, searchServiceId, searchIdSender, searchIdBenef, searchPhoneSender, searchPhoneBenef));
    }

    @Override
    public GenericsResponse<TransactionEuing> searchTransactionByReferenceEuiToPaid(String code, String destinationCode) {
        try {
            Query query = em.createNamedQuery("TransactionEuing.searchTransactionByReferenceEuiToPaid").setParameter("transReference", code).setParameter("destinationCountryCode", destinationCode);
            List<TransactionEuing> list = query.getResultList();
            if (list.size() <= 0) {
                return new GenericsResponse<>(404, "Impossible de trouver cette transsaction", null);
            } else if (list.size() == 1) {
                return new GenericsResponse<>(list.get(0));
            } else {
                return new GenericsResponse<>(500, "Impossible de payer cette transaction car elle existe en plusieurs occurences dans le système", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de la recherche de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public synchronized GenericsResponse<TransactionEuing> payTransactionC2CInternationalWithReferenceEui(TransactionEuing trx) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_PAY_INTER_C2C_INTERN");
            // set parameters
            System.out.println("SP_PAY_INTER_C2C_INTERN data ===== " + trx.toString());
            Integer res = null;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", BigInteger.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDTYPE", String.class, ParameterMode.IN);

            storedProcedure.setParameter("pRES", null);
            storedProcedure.setParameter("pRES_MESSAGE", null);
            storedProcedure.setParameter("pTRANS_ID", trx.getTransId());
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", trx.getTransPayerguichetid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", trx.getTransPayerguichet());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY_ID", trx.getTransPayeragencyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY", trx.getTransPayeragency());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY", trx.getTransPayercompany());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY_ID", trx.getTransPayercompanyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP", trx.getTransPayergroup());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP_ID", trx.getTransPayergroupid().getId());

            storedProcedure.setParameter("pTRANS_PAYER_CASHER", trx.getTransPayercasher());
            storedProcedure.setParameter("pTRANS_PAYER_USERID", trx.getTransPayercasherid().getUsrId().toBigInteger());

            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", trx.getTransOriginCountry());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", trx.getTransOriginCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", trx.getTransDestCountry());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", trx.getTransDestCountryId().getPsCode());

            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", trx.getTransOriginCur().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", trx.getTransDestCur().getDevCode());

            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", trx.getTransAmountToPaid());

//            storedProcedure.setParameter("pTRANS_SERVICE_ID", new Long("69"));
//            storedProcedure.setParameter("pTRANS_SERVICE_NAME", "Paiement Cash To Cash");
            storedProcedure.setParameter("pTRANS_SERVICE_ID", trx.getTransServiceId().getId());
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", trx.getTransServiceName());

            storedProcedure.setParameter("pTRANS_BEN_CITY", trx.getTransBenCity());
            storedProcedure.setParameter("pTRANS_BEN_POSTAL_CODE", trx.getTransBenPostalCode());
            storedProcedure.setParameter("pTRANS_BEN_ADDRESS", trx.getTransBenAddress());
            storedProcedure.setParameter("pTRANS_BEN_PHONE", trx.getTransBenPhone());
            storedProcedure.setParameter("pTRANS_BEN_EMAIL", trx.getTransBenEmail());
            storedProcedure.setParameter("pTRANS_BEN_IDNUMBER", trx.getTransBenIdnumber());
            storedProcedure.setParameter("pTRANS_BEN_IDTYPE", trx.getTransBenIdtype());

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");

            System.out.println("SP_PAY_INTER_C2C_INTERN RESPONSE ===== " + res + "====" + res_message);
            if (res == 0) {
                em.getEntityManagerFactory().getCache().evict(TransactionEuing.class);
                TransactionEuing result = (TransactionEuing) em.createNativeQuery("select * from transaction_euing where trans_id =" + trx.getTransId(), TransactionEuing.class).getSingleResult();
                System.out.println(result.toString());
                trx = result;
                return new GenericsResponse<>(trx);
            } else {
                return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Code d'erreur " + res + " - " + res_message, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> payTransactionC2COPI(TransactionEuing trx) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_PAY_INTER_C2C_OPI");
            // set parameters

            Integer res = null;
            BigDecimal trans_id = null;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.OUT);

            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_INTERFACAGE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_STATUT", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENS", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDTYPE", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDTYPE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MOTIF", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MESSAGE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PARAM_WS", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PARAM_FTP", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", BigInteger.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MTPARTNERCODE", String.class, ParameterMode.IN);

            storedProcedure.setParameter("pRES", null);
            storedProcedure.setParameter("pRES_MESSAGE", null);
            storedProcedure.setParameter("pTRANS_ID", null);

            storedProcedure.setParameter("pTRANS_INTERFACAGE", trx.getTransInterfacage());
            storedProcedure.setParameter("pTRANS_STATUT", trx.getTransStatut());
            storedProcedure.setParameter("pTRANS_SENS", trx.getTransSens());
            storedProcedure.setParameter("pTRANS_COMPANY", trx.getTransCompany());
            storedProcedure.setParameter("pTRANS_COMPANY_ID", trx.getTransCompanyId().getId());
            storedProcedure.setParameter("pTRANS_SEN_ID", trx.getTransSenId().getSenId());
            storedProcedure.setParameter("pTRANS_SEN_FIRSTNAME", trx.getTransSenFirstname());
            storedProcedure.setParameter("pTRANS_SEN_LASTNAME", trx.getTransSenLastname());
            storedProcedure.setParameter("pTRANS_SEN_COUNTRY", trx.getTransSenCountry());
            storedProcedure.setParameter("pTRANS_SEN_CITY", trx.getTransSenCity());
            storedProcedure.setParameter("pTRANS_SEN_POSTAL_CODE", trx.getTransSenPostalCode());
            storedProcedure.setParameter("pTRANS_SEN_ADDRESS", trx.getTransSenAddress());
            storedProcedure.setParameter("pTRANS_SEN_PHONE", trx.getTransSenPhone());
            storedProcedure.setParameter("pTRANS_SEN_EMAIL", trx.getTransSenEmail());
            storedProcedure.setParameter("pTRANS_SEN_IDNUMBER", trx.getTransSenIdnumber());
            storedProcedure.setParameter("pTRANS_SEN_IDTYPE", trx.getTransSenIdtype());

            storedProcedure.setParameter("pTRANS_BEN_ID", trx.getTransBenId().getBenId());
            storedProcedure.setParameter("pTRANS_BEN_FIRSTNAME", trx.getTransBenFirstname());
            storedProcedure.setParameter("pTRANS_BEN_LASTNAME", trx.getTransBenLastname());
            storedProcedure.setParameter("pTRANS_BEN_COUNTRY", trx.getTransBenCountry());
            storedProcedure.setParameter("pTRANS_BEN_CITY", trx.getTransBenCity());
            storedProcedure.setParameter("pTRANS_BEN_POSTAL_CODE", trx.getTransBenPostalCode());
            storedProcedure.setParameter("pTRANS_BEN_ADDRESS", trx.getTransBenAddress());
            storedProcedure.setParameter("pTRANS_BEN_PHONE", trx.getTransBenPhone());
            storedProcedure.setParameter("pTRANS_BEN_EMAIL", trx.getTransBenEmail());
            storedProcedure.setParameter("pTRANS_BEN_IDNUMBER", trx.getTransBenIdnumber());
            storedProcedure.setParameter("pTRANS_BEN_IDTYPE", trx.getTransBenIdtype());

            storedProcedure.setParameter("pTRANS_MOTIF", trx.getTransMotif());
            storedProcedure.setParameter("pTRANS_MESSAGE", trx.getTransMessage());
            storedProcedure.setParameter("pTRANS_PARAM_WS", (trx.getTransParamWs().getPwsId() == null) ? null : trx.getTransParamWs().getPwsId());
            storedProcedure.setParameter("pTRANS_PARAM_FTP", (trx.getTransParamFtp() == null) ? null : trx.getTransParamFtp().getPfId());

            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", trx.getTransPayerguichetid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", trx.getTransPayerguichet());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY_ID", trx.getTransPayeragencyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY", trx.getTransPayeragency());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY", trx.getTransPayercompany());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY_ID", trx.getTransPayercompanyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP", trx.getTransPayergroup());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP_ID", trx.getTransPayergroupid().getId());

            storedProcedure.setParameter("pTRANS_PAYER_CASHER", trx.getTransPayercasher());
            storedProcedure.setParameter("pTRANS_PAYER_USERID", trx.getTransPayercasherid().getUsrId().toBigInteger());

            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", trx.getTransOriginCountry());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", trx.getTransOriginCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", trx.getTransDestCountry());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", trx.getTransDestCountryId().getPsCode());

            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", trx.getTransOriginCur().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", trx.getTransDestCur().getDevCode());

            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", trx.getTransAmountToPaid());

            storedProcedure.setParameter("pTRANS_SERVICE_ID", trx.getTransServiceId().getId());
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", trx.getTransServiceName());

            storedProcedure.setParameter("pTRANS_MTPARTNERCODE", trx.getTransMtpartnercode());
            storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            trans_id = (BigDecimal) storedProcedure.getOutputParameterValue("pTRANS_ID");

            if (res == 0) {
                trx.setTransId(trans_id);
                TransactionEuing result = transactionEuingRepository.findById(trans_id).orElseThrow(()-> new EmailNotFoundException("Transaction not found"));
                trx = result;

                //sender.setSenCode(result.getSenCode());
                //this.getEntityManager().merge(trx);
                transactionEuingRepository.save(trx);
                return new GenericsResponse<>(trx);
            } else {
                System.out.println(res_message);
                return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Code d'erreur " + res + " - " + res_message, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<String> precheckPaymentTransaction(TransactionEuing trx) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_PRECHECK_PAY_INTER_C2C_OPI");
            // set parameters

            Integer res = null;
            BigDecimal trans_id = null;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.OUT);

            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_INTERFACAGE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_STATUT", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENS", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SEN_IDTYPE", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_FIRSTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_LASTNAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_CITY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_POSTAL_CODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_ADDRESS", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_PHONE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_EMAIL", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDNUMBER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_BEN_IDTYPE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MOTIF", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MESSAGE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PARAM_WS", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PARAM_FTP", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GROUP_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", BigInteger.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", BigDecimal.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MTPARTNERCODE", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_MTTRANSACTIONNUMBER", String.class, ParameterMode.IN);

            storedProcedure.setParameter("pRES", null);
            storedProcedure.setParameter("pRES_MESSAGE", null);
            storedProcedure.setParameter("pTRANS_ID", null);

            storedProcedure.setParameter("pTRANS_INTERFACAGE", trx.getTransInterfacage());
            storedProcedure.setParameter("pTRANS_STATUT", trx.getTransStatut());
            storedProcedure.setParameter("pTRANS_SENS", trx.getTransSens());
            storedProcedure.setParameter("pTRANS_COMPANY", trx.getTransCompany());
            storedProcedure.setParameter("pTRANS_COMPANY_ID", trx.getTransCompanyId().getId());
            storedProcedure.setParameter("pTRANS_SEN_ID", trx.getTransSenId().getSenId());
            storedProcedure.setParameter("pTRANS_SEN_FIRSTNAME", trx.getTransSenFirstname());
            storedProcedure.setParameter("pTRANS_SEN_LASTNAME", trx.getTransSenLastname());
            storedProcedure.setParameter("pTRANS_SEN_COUNTRY", trx.getTransSenCountry());
            storedProcedure.setParameter("pTRANS_SEN_CITY", trx.getTransSenCity());
            storedProcedure.setParameter("pTRANS_SEN_POSTAL_CODE", trx.getTransSenPostalCode());
            storedProcedure.setParameter("pTRANS_SEN_ADDRESS", trx.getTransSenAddress());
            storedProcedure.setParameter("pTRANS_SEN_PHONE", trx.getTransSenPhone());
            storedProcedure.setParameter("pTRANS_SEN_EMAIL", trx.getTransSenEmail());
            storedProcedure.setParameter("pTRANS_SEN_IDNUMBER", trx.getTransSenIdnumber());
            storedProcedure.setParameter("pTRANS_SEN_IDTYPE", trx.getTransSenIdtype());

            storedProcedure.setParameter("pTRANS_BEN_ID", trx.getTransBenId().getBenId());
            storedProcedure.setParameter("pTRANS_BEN_FIRSTNAME", trx.getTransBenFirstname());
            storedProcedure.setParameter("pTRANS_BEN_LASTNAME", trx.getTransBenLastname());
            storedProcedure.setParameter("pTRANS_BEN_COUNTRY", trx.getTransBenCountry());
            storedProcedure.setParameter("pTRANS_BEN_CITY", trx.getTransBenCity());
            storedProcedure.setParameter("pTRANS_BEN_POSTAL_CODE", trx.getTransBenPostalCode());
            storedProcedure.setParameter("pTRANS_BEN_ADDRESS", trx.getTransBenAddress());
            storedProcedure.setParameter("pTRANS_BEN_PHONE", trx.getTransBenPhone());
            storedProcedure.setParameter("pTRANS_BEN_EMAIL", trx.getTransBenEmail());
            storedProcedure.setParameter("pTRANS_BEN_IDNUMBER", trx.getTransBenIdnumber());
            storedProcedure.setParameter("pTRANS_BEN_IDTYPE", trx.getTransBenIdtype());

            storedProcedure.setParameter("pTRANS_MOTIF", trx.getTransMotif());
            storedProcedure.setParameter("pTRANS_MESSAGE", trx.getTransMessage());
            storedProcedure.setParameter("pTRANS_PARAM_WS", (trx.getTransParamWs().getPwsId() == null) ? null : trx.getTransParamWs().getPwsId());
            storedProcedure.setParameter("pTRANS_PARAM_FTP", (trx.getTransParamFtp() == null) ? null : trx.getTransParamFtp().getPfId());

            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", trx.getTransPayerguichetid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", trx.getTransPayerguichet());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY_ID", trx.getTransPayeragencyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_AGENCY", trx.getTransPayeragency());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY", trx.getTransPayercompany());
            storedProcedure.setParameter("pTRANS_PAYER_COMPANY_ID", trx.getTransPayercompanyid().getId());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP", trx.getTransPayergroup());
            storedProcedure.setParameter("pTRANS_PAYER_GROUP_ID", trx.getTransPayergroupid().getId());

            storedProcedure.setParameter("pTRANS_PAYER_CASHER", trx.getTransPayercasher());
            storedProcedure.setParameter("pTRANS_PAYER_USERID", trx.getTransPayercasherid().getUsrId());

            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", trx.getTransOriginCountry());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", trx.getTransOriginCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", trx.getTransDestCountry());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", trx.getTransDestCountryId().getPsCode());

            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", trx.getTransOriginCur().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", trx.getTransDestCur().getDevCode());

            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", trx.getTransAmountToPaid());

            storedProcedure.setParameter("pTRANS_SERVICE_ID", trx.getTransServiceId().getId());
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", trx.getTransServiceName());
            storedProcedure.setParameter("pTRANS_MTPARTNERCODE", trx.getTransMtpartnercode());
            storedProcedure.setParameter("pTRANS_MTTRANSACTIONNUMBER", trx.getTransMttransactionnumber());

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            trans_id = (BigDecimal) storedProcedure.getOutputParameterValue("pTRANS_ID");

            if (res == 0) {
                return new GenericsResponse<>("");
            } else {
                System.out.println(res_message);
                return new GenericsResponse<>(res_message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage(), "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage());
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> cancelTransaction(TransactionEuing trx, int user_id, int keep_fees) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_CANCEL_TRANSACTION");
            // set parameters

            Integer res = null;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("puser_id", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pKEEP_FEES", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("pRES", null);
            storedProcedure.setParameter("pRES_MESSAGE", null);
            storedProcedure.setParameter("pTRANS_ID", trx.getTransId());
            storedProcedure.setParameter("puser_id", user_id);
            storedProcedure.setParameter("pKEEP_FEES", keep_fees);

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");

            System.out.println(res);
            System.out.println(res_message);

            if (res == 0) {
                em.getEntityManagerFactory().getCache().evict(TransactionEuing.class);
                TransactionEuing result = (TransactionEuing) em.createNativeQuery("select * from transaction_euing where trans_id =" + trx.getTransId(), TransactionEuing.class).getSingleResult();
                trx = result;
                return new GenericsResponse<>(trx);
            } else {
                System.out.println(res_message);
                return new GenericsResponse<>(500, "Une erreur s'est produite lors de l'annulation de la transaction. Code d'erreur " + res + " - " + res_message, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de l'annulation de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    private List<TransactionEuing> convertVwtrxToTrxeuingNew(List<VwTransactionEuing> listvwttrxn) {
        List<TransactionEuing> list = new ArrayList<>();

        TransactionEuing teuing;
        Short sh = 1;
        for (VwTransactionEuing vwtrxn : listvwttrxn) {
            Sender sender = new Sender(new BigDecimal(vwtrxn.getTransSenId()), vwtrxn.getTransSenLastname(), vwtrxn.getTransSenCountry(), vwtrxn.getTransSenCountry(), vwtrxn.getTransSenCity(), vwtrxn.getTransSenPhone(), new Date(), sh, "N/A");

            Beneficiary benef = new Beneficiary(new BigDecimal(vwtrxn.getTransBenId()), vwtrxn.getTransBenLastname(), vwtrxn.getTransBenCountry(), vwtrxn.getTransBenCity(), vwtrxn.getTransBenPhone(), new Date(), sh, "N/A");
            Service service = new Service(vwtrxn.getTransServiceId().longValue(), vwtrxn.getServiceName(), vwtrxn.getServiceCode());
            Guichet guichet = new Guichet();
            guichet.setId(vwtrxn.getTransGuichetId().longValue());
            guichet.setLibelle(vwtrxn.getTransGuichet());

            Agence agence = new Agence();
            agence.setId(vwtrxn.getTransAgencyId().longValue());
            agence.setLibelle(vwtrxn.getTransAgency());

            Compagnie compagnie = new Compagnie(vwtrxn.getTransCompanyId().longValue());
            compagnie.setLibelle(vwtrxn.getTransCompany());

            Succursale succursale = new Succursale(vwtrxn.getTransGroupId().longValue());
            succursale.setLibelle(vwtrxn.getTransGroup());

            Pays source = new Pays(vwtrxn.getTransOriginCountryId());
            source.setPsLibelle(vwtrxn.getTransOriginCountry());

            Pays destination = new Pays(vwtrxn.getTransDestCountryId());
            destination.setPsLibelle(vwtrxn.getTransDestCountry());
            Devise devsource, devdest = null;

            devsource = new Devise(vwtrxn.getTransOriginCur());

            if (vwtrxn.getTransDestCur() != null) {
                //devsource = new Devise(vwtrxn.getTransOriginCur());
                devdest = new Devise(vwtrxn.getTransDestCur());
            }
            Guichet guipayeur = null;
            if (vwtrxn.getTransPayerguichetid() != null) {
                guipayeur = new Guichet(vwtrxn.getTransPayerguichetid().longValue());
                guipayeur.setLibelle(vwtrxn.getTransPayerguichet());

            }
            Agence agpayeur = null;
            if (vwtrxn.getTransPayeragencyid() != null) {
                agpayeur = new Agence(vwtrxn.getTransPayeragencyid().longValue(), vwtrxn.getTransPayeragency());
            }
            Compagnie compayeur = null;
            Succursale succpayeur = null;
            if (vwtrxn.getTransPayercompanyid() != null) {
                compayeur = new Compagnie(vwtrxn.getTransPayercompanyid().longValue());
                compayeur.setLibelle(vwtrxn.getTransPayercompany());

                succpayeur = new Succursale(vwtrxn.getTransPayergroupid().longValue());
                succpayeur.setLibelle(vwtrxn.getTransPayergroup());
            }
            teuing = new TransactionEuing(new BigDecimal(vwtrxn.getTransId()), vwtrxn.getTransGuichet(), vwtrxn.getTransAgency(), vwtrxn.getTransCompany(), vwtrxn.getTransGroup(), vwtrxn.getTransCasher(), vwtrxn.getTransOriginCountry(), vwtrxn.getTransDestCountry(), vwtrxn.getTransExchangeRate(), vwtrxn.getTransExchangeRateCust(), vwtrxn.getTransExchangeRateMargin(), vwtrxn.getTransAmountSent(), vwtrxn.getTransOthers(), vwtrxn.getTransFees(), vwtrxn.getTransTaxes(), vwtrxn.getTransTotal(), vwtrxn.getTransAmountToPaid(), vwtrxn.getTransStatut(), vwtrxn.getTransSens(), vwtrxn.getTransServiceName(), vwtrxn.getTransSenLastname(), vwtrxn.getTransSenCountry(), vwtrxn.getTransSenCity(), vwtrxn.getTransBenLastname(), vwtrxn.getTransBenCountry(), vwtrxn.getTransBenCity(), vwtrxn.getTransComisAgence(), vwtrxn.getTransComisCompanie(), vwtrxn.getTransComisGroupe(), vwtrxn.getTransComisSysteme(), vwtrxn.getTransComisGuichet());
            teuing.setTransServiceId(service);
            teuing.setTransSenId(sender);
            teuing.setTransBenId(benef);
            teuing.setTransServiceName(vwtrxn.getServiceCode());
            teuing.setTransGuichetId(guichet);
            teuing.setTransGuichetId(guichet);
            teuing.setTransAgencyId(agence);
            teuing.setTransCompanyId(compagnie);
            teuing.setTransGroupId(succursale);
            teuing.setTransReference(vwtrxn.getTransReference());
            teuing.setTransMttransactionnumber(vwtrxn.getTransMttransactionnumber());
            teuing.setTransSenLastname(vwtrxn.getTransSenLastname());
            teuing.setTransSenFirstname(vwtrxn.getTransSenFirstname());
            teuing.setTransBenLastname(vwtrxn.getTransBenLastname());
            teuing.setTransBenFirstname(vwtrxn.getTransBenFirstname());
            teuing.setTransOriginCountryId(source);
            teuing.setTransDestCountryId(destination);
            teuing.setTransOriginCur(devsource);
            teuing.setTransDestCur(devdest);
            teuing.setTransComisGrpReal(vwtrxn.getTransComisGrpReal());
            teuing.setTransComisSysReal(vwtrxn.getTransComisSysReal());
            teuing.setTransDateCreated(vwtrxn.getTransDateCreated());
            teuing.setTransDatePaid(vwtrxn.getTransDatePaid());
            teuing.setTransPayerguichetid(guipayeur);
            teuing.setTransPayeragencyid(agpayeur);
            teuing.setTransPayercompanyid(compayeur);
            teuing.setTransPayergroupid(succpayeur);
            teuing.setTransPayercomisguichet(vwtrxn.getTransPayercomisguichet());
            teuing.setTransPayercomisagency(vwtrxn.getTransPayercomisagency());
            teuing.setTransPayercomiscompany(vwtrxn.getTransPayercomiscompany());
            teuing.setTransPayercomisgroup(vwtrxn.getTransPayercomisgroup());
            teuing.setTransPayercomissysteme(vwtrxn.getTransPayercomissysteme());
            teuing.setTransComisGrpRealPayer(vwtrxn.getTransComisGrpRealPayer());
            teuing.setTransComisSysRealPayer(vwtrxn.getTransComisSysRealPayer());
            list.add(teuing);
        }

        return list;
    }

    @Override
    public GenericsResponse<BigDecimal> checkSoldeCompany(BigDecimal company_id, String dev_paiement, BigDecimal amountToPaid, int ptrans_service_id) {
        try {
            BigDecimal balance = (BigDecimal) em
                    .createNativeQuery("SELECT CHECK_SOLDE_COMPANY(?, ?, ?, ?) FROM DUAL")
                    .setParameter(1, company_id)
                    .setParameter(2, dev_paiement)
                    .setParameter(3, amountToPaid)
                    .setParameter(4, ptrans_service_id)
                    .getSingleResult();
            System.out.println(balance);
            return new GenericsResponse<>(balance);
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "An error occured during get balance company. Details : " + e.getMessage(), null);
        }
    }

    public String validateFieldsDateBenef(Beneficiary transBenId) {
        String check = "";
        Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);

        List<String> clientIds = new ArrayList<>();
        List<String> messages = new ArrayList<>();

// if (transBenId.getBenDob().compareTo(currentDate) >= 0) {
//// JsfUtil.addErrorMessage("EnvoiInternationalForm:dob",ResourceBundle.getBundle("/messages_fr").getString("ValidateFieldsDateSenderError"));
// return "Date invalide pour la date de naissance du bénéficiaire";
// }
        if (transBenId.getBenIdDocumentDelDate().compareTo(currentDate) > 0) {
// JsfUtil.addErrorMessage("EnvoiInternationalForm:idissuedate",ResourceBundle.getBundle("/messages_fr").getString("ValidateFieldsDateSenderError"));
            return "Date invalide pour la date de délivrance de l'ID du bénéficiaire";
        }

        Calendar compareCalendar1 = Calendar.getInstance();
        Calendar compareCalendar2 = Calendar.getInstance();
        compareCalendar1.setTime(currentDate);
        compareCalendar2.setTime(transBenId.getBenIdDocumentExpDate());

        compareCalendar1.set(Calendar.MILLISECOND, 0);
        compareCalendar2.set(Calendar.MILLISECOND, 0);
/// comment by JB for solve date send by eui
//        if (compareCalendar2.compareTo(compareCalendar1) < 0) {
//// JsfUtil.addErrorMessage("EnvoiInternationalForm:idexpirationdate",ResourceBundle.getBundle("/messages_fr").getString("ValidateFieldsDateSenderError"));
//            return "Date invalide pour la date d'expiration de l'ID du bénéficiaire";
//        }

        return check;
    }

    @Override
    public GenericsResponse<TransactionEuing> payTransaction(TransactionEuing trx) {
        try {
            System.out.println("============== DONNEES ENVOYEES PAR EUI ==========================");
            System.out.println("TRX == " + trx.toString());

                Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
                if (!"GUICHET".equals(usr.getUsrTypeUo())) {
                    return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre compte utilisateur n'est affecté à aucun guichet!", null);
                } else {
                    int guichetId = usr.getUsrUoId();
                    Guichet guichetUser = (Guichet) getEntityManager().createNamedQuery("Guichet.findById").setParameter("id", guichetId).getSingleResult();
                    Agence agenceUser = null;
                    Compagnie compagnieUser = null;
                    Succursale succursaleUser = null;

                    //verifions si le guichet ou un de ses parents est désactivée!
                    if (guichetUser.getStatut() == 0) {
                        return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre guichet est désactivé!", null);
                    } else {
                        if (guichetUser.getAgence().getStatut() == 0) {
                            return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre agence est désactivée!", null);
                        } else {
                            if (guichetUser.getAgence().getCompagnie().getStatut() == 0) {
                                return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre compagnie est désactivée!", null);
                            } else {
                                if (guichetUser.getAgence().getCompagnie().getSuccursale().getStatut() == 0) {
                                    return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre groupe est désactivé!", null);
                                }
                            }
                        }
                    }

                    //validation des champs date de l'objet beneficiaire
                    String resValidateFieldsDateBenef = validateFieldsDateBenef(trx.getTransBenId());
                    if (!"".equals(resValidateFieldsDateBenef)) {
                        return new GenericsResponse<>(500, resValidateFieldsDateBenef, null);
                    }

                    //validation idnumber
                    System.out.println("=============== ICI avant responseSearchctpi  =============" + guichetUser.getAgence().getCompagnie().getLibelle());
                    System.out.println("TPI-CODE === " + trx.getTransBenIdtype());
                    GenericsResponse responseSearchctpi
                            = findCompagnieTpiByTpiComagnie(guichetUser.getAgence().getCompagnie().getId(), trx.getTransBenIdtype());
                    System.out.println("=============== ICI apres responseSearchctpi  =============");

                    if (responseSearchctpi.getResponseCode() == 200) {
                        CompagnieTpi res = (CompagnieTpi) responseSearchctpi.getT();
                        int maxLength = (res.getMaxLength() == null) ? 0 : res.getMaxLength();
                        int minLength = (res.getMinLength() == null) ? 0 : res.getMinLength();
                        int lengthIdDocument = trx.getTransBenIdnumber().trim().length();

                        if (lengthIdDocument < minLength || lengthIdDocument > maxLength) {
                            String errorMessage = "Numéro de pièce d'identité invalide. Le nombre de caractères doit etre entre [min] et [max] pour la compagnie [comp] et le type de pièce d'identité [tpi]";
                            errorMessage = errorMessage
                                    .replace("[max]", "" + maxLength)
                                    .replace("[min]", "" + minLength)
                                    .replace("[comp]", "" + guichetUser.getAgence().getCompagnie().getLibelle())
                                    .replace("[tpi]", "" + trx.getTransBenId().getBenIdDocumentType().getTpiLibelle());
                            return new GenericsResponse<>(500, errorMessage, null);
                        }
                    } else {
                        return new GenericsResponse<>(500, responseSearchctpi.getResponseDescription(), null);
                    }

                    // Enregistrement du bénéficiaire
                    ////////setForeignFieldBenef
                    //Type de piece d'identité
                    GenericsResponse responseSearchTpi = getTypePieceIdentiteByCode(trx.getTransBenIdtype());
                    if (responseSearchTpi.getResponseCode() != 200) {
                        return new GenericsResponse<>(responseSearchTpi.getResponseCode(), responseSearchTpi.getResponseDescription(), null);
                    } else {
                        TypePieceIdentite tpi = (TypePieceIdentite) responseSearchTpi.getT();
                        trx.setTransBenIdtype(tpi.getTpiLibelle());
                        trx.getTransBenId().setBenIdDocumentType(tpi);
                    }
                    //Ville
                    System.out.println("Ville ===== " + trx.getTransBenCity());
                    GenericsResponse responseSearchCity = getCityByName(trx.getTransBenCity());
                    if (responseSearchCity.getResponseCode() != 200) {
                        return new GenericsResponse<>(responseSearchCity.getResponseCode(), responseSearchCity.getResponseDescription(), null);
                    } else {
                        Ville vi = (Ville) responseSearchCity.getT();
                        trx.getTransBenId().setBenCity(vi.getViLibelle());
                        trx.getTransBenId().setBenCityId(vi);
                    }
                    // add by JBY 22032021 *Begin
                    System.out.println("Country issue card send by EUI ==== " + trx.getTransBenId().getBenIdDocumentIssueCountry().getPsCode());
                    GenericsResponse responseSearchCountry = getCountryByCode(trx.getTransBenId().getBenIdDocumentIssueCountry().getPsCode().trim());
                    if (responseSearchCountry.getResponseCode() == 200) {
                        trx.getTransBenId().setBenIdDocumentIssueCountry((Pays) responseSearchCountry.getT());
                    }
                    // end
                    GenericsResponse responseSaveBenef = beneficiaryService.updateBeneficiary(trx.getTransBenId());

                    if (responseSaveBenef.getResponseCode() == 200) {
                        Beneficiary res = (Beneficiary) responseSaveBenef.getT();
                        trx.setTransBenId(res);
                    } else {
                        return new GenericsResponse<>(500, responseSaveBenef.getResponseDescription(), null);
                    }

                    //paiement de la transaction
                    trx.setTransPayercasher(usr.getUsrPrenom() + " " + usr.getUsrNom());
//                    trx.setTransPayercasherid(usr.getUsrId().toBigInteger());
                    trx.setTransPayercasherid(usr);

                    trx.setTransStatut("P");

                    ////////setForeignFieldTransactionsToPaid();
                    //Guichet Payer transaction
                    trx.setTransPayerguichetid(guichetUser);
                    trx.setTransPayerguichet(guichetUser.getLibelle());

                    //Agence Payer transaction
                    trx.setTransPayeragencyid(guichetUser.getAgence());
                    trx.setTransPayeragency(guichetUser.getAgence().getLibelle());

                    //Compagnie Payer transaction
                    trx.setTransPayercompanyid(guichetUser.getAgence().getCompagnie());
                    trx.setTransPayercompany(guichetUser.getAgence().getCompagnie().getLibelle());

                    //Groupe transaction
                    trx.setTransPayergroupid(guichetUser.getAgence().getCompagnie().getSuccursale());
                    trx.setTransPayergroup(guichetUser.getAgence().getCompagnie().getSuccursale().getLibelle());

                    System.out.println("Données avant le paiement :");
                    System.out.println("****** " + trx.toString() + " ******");
                    return this.payTransactionC2CInternationalWithReferenceEui(trx);
                }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage(), null);
        }

    }

    public GenericsResponse<CompagnieTpi> findCompagnieTpiByTpiComagnie(Long compagnieId, String tpiCode) {
        try {
            CompagnieTpi ctpi = (CompagnieTpi) getEntityManager().createNamedQuery("CompagnieTpi.checkDoublon").setParameter("compagnie", compagnieId).setParameter("tpiCode", tpiCode).getSingleResult();
            return new GenericsResponse<>(ctpi);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new CompagnieTpi());
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> searchTransaction(String reference) {
        try {

            Utilisateur usr = utilisateurService.find(euingProperties.getUtilisateurCode());
                if (!"GUICHET".equals(usr.getUsrTypeUo())) {
                    return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre compte utilisateur n'est affecté à aucun guichet!", null);
                } else {
                    int guichetId = usr.getUsrUoId();
                    Guichet guichetUser = (Guichet) getEntityManager().createNamedQuery("Guichet.findById").setParameter("id", guichetId).getSingleResult();
                    Agence agenceUser = null;
                    Compagnie compagnieUser = null;
                    Succursale succursaleUser = null;

                    //verifions si le guichet ou un de ses parents est désactivée!
                    if (guichetUser.getStatut() == 0) {
                        return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre guichet est désactivé!", null);
                    } else {
                        if (guichetUser.getAgence().getStatut() == 0) {
                            return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre agence est désactivée!", null);
                        } else {
                            if (guichetUser.getAgence().getCompagnie().getStatut() == 0) {
                                return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre compagnie est désactivée!", null);
                            } else {
                                if (guichetUser.getAgence().getCompagnie().getSuccursale().getStatut() == 0) {
                                    return new GenericsResponse<>(500, "Merci de contacter votre administrateur. Votre groupe est désactivé!", null);
                                }
                            }
                        }
                    }

                    //recherche de la transaction
                    return this.searchTransactionByReferenceEuiToPaid(reference, guichetUser.getAgence().getCompagnie().getPays().getPsCode());

                }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de la recherche de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    public GenericsResponse<TypePieceIdentite> getTypePieceIdentiteByCode(String code) {
        try {
            TypePieceIdentite tf = (TypePieceIdentite) getEntityManager().createNamedQuery("TypePieceIdentite.findByTpiCode")
                    .setParameter("tpiCode", code)
                    .getSingleResult();
            if (tf != null) {
                return new GenericsResponse<>(tf);
            } else {
                return new GenericsResponse<>(404, "Type piece d'identité non existant avec le code " + code, new TypePieceIdentite());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured while search type piece d'identite. Details : " + e.getMessage(), new TypePieceIdentite());
        }
    }

    @Override
    public GenericsResponse<Ville> getCityByName(String name) {
        try {
            Ville vi = (Ville) em.createNamedQuery("Ville.findByViLibelle")
                    .setParameter("viLibelle", name)
                    .getSingleResult();
            if (vi != null) {
                return new GenericsResponse<>(vi);
            } else {
                return new GenericsResponse<>(404, "Impossible de trouver la ville avec le nom " + name, new Ville());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured while search city. Details : " + e.getMessage(), new Ville());
        }
    }

    @Override
    public GenericsResponse<Pays> getCountryByCode(String name) {
        try {
            Pays p;
            if (name.length() == 3) {
                p = (Pays) em.createNamedQuery("Pays.findByPsCode")
                        .setParameter("psCode", name)
                        .getSingleResult();
            } else {
                p = (Pays) em.createNamedQuery("Pays.findByPsCode2")
                        .setParameter("psCode2", name)
                        .getSingleResult();
            }
            if (p != null) {
                return new GenericsResponse<>(p);
            } else {
                return new GenericsResponse<>(404, "Impossible de trouver la ville avec le nom " + name, new Pays());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured while search city. Details : " + e.getMessage(), new Pays());
        }
    }

    @Override
    public GenericsResponse<ResponseSimulation> simulatePayC2COpi(Long guichetId, String guichet, Long companyId, String company, String casher, Long userId, String originCountry, String originCountryId, String destCountry, String destCountryId, String originCur, String destCur, Long serviceId, String serviceName, double amountToPaid, Long paramWs) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_SIMULATE_PAY_INTER_C2C_OPI");
            // set parameters

            Double pamount_to_paid = null;
            Double pfees = null;
            Double pglobal_tva = null;
            Double pglobal_other_taxe = null;
            Double page_commission = null;
            Double pcom_commission = null;
            Double pgrp_commission = null;
            Double psys_commission = null;
            Double pgui_commission = null;
            Double pexchange_rate = null;

            Integer pRes = null;
            String pres_message = "";

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", Double.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pexchange_rate", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pgui_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("psys_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pgrp_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pcom_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("page_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pglobal_other_taxe", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pglobal_tva", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pfees", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pamount_to_paid", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PARAM_WS", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);

            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", guichetId);
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", guichet);
            storedProcedure.setParameter("pTRANS_COMPANY", company);
            storedProcedure.setParameter("pTRANS_COMPANY_ID", companyId);
            storedProcedure.setParameter("pTRANS_PAYER_CASHER", casher);
            storedProcedure.setParameter("pTRANS_PAYER_USERID", userId);
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", originCountry);
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", originCountryId);
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", destCountry);
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", destCountryId);
            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", originCur);
            storedProcedure.setParameter("pTRANS_DEST_CUR", destCur);
            storedProcedure.setParameter("pTRANS_SERVICE_ID", serviceId);
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", serviceName);
            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", amountToPaid);

            storedProcedure.setParameter("pexchange_rate", pexchange_rate);
            storedProcedure.setParameter("pgui_commission", pgui_commission);
            storedProcedure.setParameter("psys_commission", psys_commission);
            storedProcedure.setParameter("pgrp_commission", pgrp_commission);
            storedProcedure.setParameter("pcom_commission", pcom_commission);
            storedProcedure.setParameter("page_commission", page_commission);
            storedProcedure.setParameter("pglobal_other_taxe", pglobal_other_taxe);
            storedProcedure.setParameter("pglobal_tva", pglobal_tva);
            storedProcedure.setParameter("pfees", pfees);
            storedProcedure.setParameter("pamount_to_paid", pamount_to_paid);
            storedProcedure.setParameter("pTRANS_PARAM_WS", paramWs);
            storedProcedure.setParameter("pRES", pRes);
            storedProcedure.setParameter("pRES_MESSAGE", pres_message);

            // execute stored procedure
            storedProcedure.execute();
            pRes = (Integer) storedProcedure.getOutputParameterValue("pRES");
            pres_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            pamount_to_paid = (double) storedProcedure.getOutputParameterValue("pamount_to_paid");
            pfees = (double) storedProcedure.getOutputParameterValue("pfees");
            pglobal_tva = (double) storedProcedure.getOutputParameterValue("pglobal_tva");
            pglobal_other_taxe = (double) storedProcedure.getOutputParameterValue("pglobal_other_taxe");
            page_commission = (double) storedProcedure.getOutputParameterValue("page_commission");
            pcom_commission = (double) storedProcedure.getOutputParameterValue("pcom_commission");
            pgrp_commission = (double) storedProcedure.getOutputParameterValue("pgrp_commission");
            psys_commission = (double) storedProcedure.getOutputParameterValue("psys_commission");
            pgui_commission = (double) storedProcedure.getOutputParameterValue("pgui_commission");
            pexchange_rate = (double) storedProcedure.getOutputParameterValue("pexchange_rate");

            return new GenericsResponse<>(new ResponseSimulation(pRes, pres_message, pamount_to_paid, pfees, pglobal_tva, pglobal_other_taxe, page_commission, pcom_commission, pgrp_commission, psys_commission, pgui_commission, pexchange_rate));
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<ResponseSimulation> simulatePayC2CIntern(Long guichetId, String guichet, String casher, Long userId, String originCountry, String originCountryId, String destCountry, String destCountryId, String originCur, String destCur, Long serviceId, String serviceName, double amountToPaid, Long transId) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_SIMULATE_PAY_INTER_C2C_INTERN");
            // set parameters

            Double pamount_to_paid = null;
            Double pfees = null;
            Double pglobal_tva = null;
            Double pglobal_other_taxe = null;
            Double page_commission = null;
            Double pcom_commission = null;
            Double pgrp_commission = null;
            Double psys_commission = null;
            Double pgui_commission = null;
            Double pexchange_rate = null;

            Integer pRes = null;
            String pres_message = "";

            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_PAYER_USERID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_PAID", Double.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pexchange_rate", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pgui_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("psys_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pgrp_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pcom_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("page_commission", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pglobal_other_taxe", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pglobal_tva", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pfees", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pamount_to_paid", Double.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pRES", Integer.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);

            storedProcedure.setParameter("pTRANS_PAYER_GUICHET_ID", guichetId);
            storedProcedure.setParameter("pTRANS_PAYER_GUICHET", guichet);
            storedProcedure.setParameter("pTRANS_PAYER_CASHER", casher);
            storedProcedure.setParameter("pTRANS_PAYER_USERID", userId);
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", originCountry);
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", originCountryId);
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", destCountry);
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", destCountryId);
            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", originCur);
            storedProcedure.setParameter("pTRANS_DEST_CUR", destCur);
            storedProcedure.setParameter("pTRANS_SERVICE_ID", serviceId);
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", serviceName);
            storedProcedure.setParameter("pTRANS_AMOUNT_TO_PAID", amountToPaid);

            storedProcedure.setParameter("pexchange_rate", pexchange_rate);
            storedProcedure.setParameter("pgui_commission", pgui_commission);
            storedProcedure.setParameter("psys_commission", psys_commission);
            storedProcedure.setParameter("pgrp_commission", pgrp_commission);
            storedProcedure.setParameter("pcom_commission", pcom_commission);
            storedProcedure.setParameter("page_commission", page_commission);
            storedProcedure.setParameter("pglobal_other_taxe", pglobal_other_taxe);
            storedProcedure.setParameter("pglobal_tva", pglobal_tva);
            storedProcedure.setParameter("pfees", pfees);
            storedProcedure.setParameter("pamount_to_paid", pamount_to_paid);
            storedProcedure.setParameter("pTRANS_ID", transId);
            storedProcedure.setParameter("pRES", pRes);
            storedProcedure.setParameter("pRES_MESSAGE", pres_message);

            // execute stored procedure
            storedProcedure.execute();
            pRes = (Integer) storedProcedure.getOutputParameterValue("pRES");
            pres_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            pamount_to_paid = (double) storedProcedure.getOutputParameterValue("pamount_to_paid");
            pfees = (double) storedProcedure.getOutputParameterValue("pfees");
            pglobal_tva = (double) storedProcedure.getOutputParameterValue("pglobal_tva");
            pglobal_other_taxe = (double) storedProcedure.getOutputParameterValue("pglobal_other_taxe");
            page_commission = (double) storedProcedure.getOutputParameterValue("page_commission");
            pcom_commission = (double) storedProcedure.getOutputParameterValue("pcom_commission");
            pgrp_commission = (double) storedProcedure.getOutputParameterValue("pgrp_commission");
            psys_commission = (double) storedProcedure.getOutputParameterValue("psys_commission");
            pgui_commission = (double) storedProcedure.getOutputParameterValue("pgui_commission");
            pexchange_rate = (double) storedProcedure.getOutputParameterValue("pexchange_rate");

            return new GenericsResponse<>(new ResponseSimulation(pRes, pres_message, pamount_to_paid, pfees, pglobal_tva, pglobal_other_taxe, page_commission, pcom_commission, pgrp_commission, psys_commission, pgui_commission, pexchange_rate));
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du calcul de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> searchTransactionByReferenceEuiToMAJ(String code, String origineCode) {
        try {
            Query query = em.createNamedQuery("TransactionEuing.searchTransactionByReferenceEuiToMAJ").setParameter("transReference", code).setParameter("origineCountryCode", origineCode);
            List<TransactionEuing> list = query.getResultList();
            if (list.size() <= 0) {
                return new GenericsResponse<>(404, "Impossible de trouver cette transsaction", null);
            } else if (list.size() == 1) {
                return new GenericsResponse<>(list.get(0));
            } else {
                return new GenericsResponse<>(500, "Impossible de payer cette transaction car elle existe en plusieurs occurences dans le système", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de la recherche de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<TransactionEuing> searchTransactionByReferenceEui(String code) {
        try {
            em.getEntityManagerFactory().getCache().evict(TransactionEuing.class);
            Query query = em.createNamedQuery("TransactionEuing.findByTransReference").setParameter("transReference", code);
            List<TransactionEuing> list = query.getResultList();
            if (list.size() <= 0) {
                return new GenericsResponse<>(404, "Impossible de trouver cette transsaction", null);
            } else if (list.size() == 1) {
                return new GenericsResponse<>(list.get(0));
            } else {
                return new GenericsResponse<>(500, "Impossible de payer cette transaction car elle existe en plusieurs occurences dans le système", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors de la recherche de la transaction. Détails : " + e.getMessage(), null);
        }
    }

    @Override
    public GenericsResponse<String> precheckEnvoiInterC2C(TransactionEuing trx) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SP_PRECHECK_SEND_INTER_C2C");
            // set parameters
            Integer res = null;
            BigDecimal trans_id = null;
            String res_message = "";

            storedProcedure.registerStoredProcedureParameter("pRES", Long.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pRES_MESSAGE", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ID", BigDecimal.class, ParameterMode.OUT);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_GUICHET_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_GUICHET", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_AGENCY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_AGENCY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_COMPANY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_COMPANY_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_GROUP", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_GROUP_ID", Long.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_CASHER", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SENDER_USERID", BigInteger.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_COUNTRY_ID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_COUNTRY_ID", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_ORIGIN_CUR", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_DEST_CUR", String.class, ParameterMode.IN);

            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_ID", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_SERVICE_NAME", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pSENDER_ID", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pTRANS_AMOUNT_TO_SEND", BigDecimal.class, ParameterMode.IN);

            storedProcedure.setParameter("pRES", null);
            storedProcedure.setParameter("pRES_MESSAGE", null);
            storedProcedure.setParameter("pTRANS_ID", null);

            storedProcedure.setParameter("pTRANS_SENDER_GUICHET_ID", trx.getTransGuichetId().getId());
            storedProcedure.setParameter("pTRANS_SENDER_GUICHET", trx.getTransGuichet());
            storedProcedure.setParameter("pTRANS_SENDER_AGENCY_ID", trx.getTransAgencyId().getId());
            storedProcedure.setParameter("pTRANS_SENDER_AGENCY", trx.getTransAgency());
            storedProcedure.setParameter("pTRANS_SENDER_COMPANY", trx.getTransCompany());
            storedProcedure.setParameter("pTRANS_SENDER_COMPANY_ID", trx.getTransCompanyId().getId());
            storedProcedure.setParameter("pTRANS_SENDER_GROUP", trx.getTransGroup());
            storedProcedure.setParameter("pTRANS_SENDER_GROUP_ID", trx.getTransGroupId().getId());

            storedProcedure.setParameter("pTRANS_SENDER_CASHER", trx.getTransUserid().getUsrLogin());
            storedProcedure.setParameter("pTRANS_SENDER_USERID", trx.getTransUserid().getUsrId());

            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY", trx.getTransOriginCountry());
            storedProcedure.setParameter("pTRANS_ORIGIN_COUNTRY_ID", trx.getTransOriginCountryId().getPsCode());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY", trx.getTransDestCountry());
            storedProcedure.setParameter("pTRANS_DEST_COUNTRY_ID", trx.getTransDestCountryId().getPsCode());

            storedProcedure.setParameter("pTRANS_ORIGIN_CUR", trx.getTransOriginCur().getDevCode());
            storedProcedure.setParameter("pTRANS_DEST_CUR", trx.getTransDestCur().getDevCode());

            storedProcedure.setParameter("pTRANS_SERVICE_ID", trx.getTransServiceId().getId());
            storedProcedure.setParameter("pTRANS_SERVICE_NAME", trx.getTransServiceName());
            storedProcedure.setParameter("pSENDER_ID", trx.getTransSenId().getSenId());
            storedProcedure.setParameter("pTRANS_AMOUNT_TO_SEND", trx.getTransAmountToPaid());

            // execute stored procedure
            storedProcedure.execute();
            res = (Integer) storedProcedure.getOutputParameterValue("pRES");
            res_message = (String) storedProcedure.getOutputParameterValue("pRES_MESSAGE");
            trans_id = (BigDecimal) storedProcedure.getOutputParameterValue("pTRANS_ID");

            if (res == 0) {
                return new GenericsResponse<>("");
            } else {
                System.out.println(res_message);
                return new GenericsResponse<>(res_message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericsResponse<>(500, "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage(), "Une erreur s'est produite lors du paiement de la transaction. Détails : " + e.getMessage());
        }
    }


}
