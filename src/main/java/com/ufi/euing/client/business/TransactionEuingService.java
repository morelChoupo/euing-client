package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.response.ResponseSimulation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionEuingService {

    void create(TransactionEuing transactionEuing);

    void edit(TransactionEuing transactionEuing);

    void remove(TransactionEuing transactionEuing);

    TransactionEuing find(Object id);

    List<TransactionEuing> findAll();

    List<TransactionEuing> findRange(int[] range);

    int count();

    ReferenceEUI giveRerence();

    GenericsResponse<CalculTarifInternational> calculTarifInterTransaction(Long companyId, String companyName, Long serviceId, String serviceName, String destinationCountryCode, String destinationCountryLibelle, int mode, double amount, BigDecimal senderId, String originCurrency, String destinationCurrency);

    GenericsResponse<TransactionEuing> createNewInterTransactionC2C(TransactionEuing trx);

    GenericsResponse<TransactionEuing> findTransaction(BigDecimal id) throws EmailNotFoundException;

    GenericsResponse<TransactionEuing> updateTransaction(TransactionEuing trx);

    List<TransactionEuing> searchTransactions(Date searchDateStart, Date searchDateEnd, Succursale searchGroupeId, Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId,
                                              String searchReference, String searchCodeClient, String searchExpediteur, String searchBeneficiaire, Pays searchDestinationCode,
                                              String searchStatut, Service searchServiceId, String searchIdSender, String searchIdBenef, String searchPhoneSender,
                                              String searchPhoneBenef);

    GenericsResponse<TransactionEuing> searchTransactionByReferenceEuiToPaid(String code, String destinationCode);

    GenericsResponse<TransactionEuing> payTransactionC2CInternationalWithReferenceEui(TransactionEuing trx);

    GenericsResponse<TransactionEuing> payTransactionC2COPI(TransactionEuing trx);

    GenericsResponse<String> precheckPaymentTransaction(TransactionEuing trx);

    GenericsResponse<TransactionEuing> cancelTransaction(TransactionEuing trx, int user_id, int keep_fees);

    GenericsResponse<BigDecimal> checkSoldeCompany(BigDecimal company_id, String dev_paiement, BigDecimal amountToPaid, int ptrans_service_id);

    GenericsResponse<TransactionEuing> payTransaction(TransactionEuing trx);

    GenericsResponse<TransactionEuing> searchTransaction(String reference);

    public GenericsResponse<Ville> getCityByName(String name);

    public GenericsResponse<Pays> getCountryByCode(String name);

    GenericsResponse<ResponseSimulation> simulatePayC2COpi(Long guichetId, String guichet, Long companyId, String company, String casher, Long userId, String originCountry, String originCountryId, String destCountry, String destCountryId, String originCur, String destCur, Long serviceId, String serviceName, double amountToPaid, Long paramWs);

    GenericsResponse<ResponseSimulation> simulatePayC2CIntern(Long guichetId, String guichet, String casher, Long userId, String originCountry, String originCountryId, String destCountry, String destCountryId, String originCur, String destCur, Long serviceId, String serviceName, double amountToPaid, Long transId);

    GenericsResponse<TransactionEuing> searchTransactionByReferenceEuiToMAJ(String code, String destinationCode);

    GenericsResponse<TransactionEuing> searchTransactionByReferenceEui(String code);

    GenericsResponse<String> precheckEnvoiInterC2C(TransactionEuing trx);
}
