package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.CalculTarifBill;
import com.ufi.euing.client.integration.billpay.CheckBillResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionBillService {

    void create(TransactionBill transactionBill);

    void edit(TransactionBill transactionBill);

    void remove(TransactionBill transactionBill);

    TransactionBill find(Object id);

    List<TransactionBill> findAll();

    List<TransactionBill> findRange(int[] range);

    int count();

    List<TransactionBill> findByStatus(String transactionStatus);

    TransactionBill findById(Long transId);

    TransactionBill save(TransactionBill transaction);

    List<TransactionBill> searchTransactions(Date searchDateStart, Date searchDateEnd, String searchStatut, Succursale searchGroupeId,
                                             Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId, String searchReference, Service searchServiceId);

    GenericsResponse<CalculTarifBill> calculFee(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, BigDecimal amount, BigDecimal userID, String userLogin);

    GenericsResponse<CheckBillResponse> precheckBillPayment(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, BigDecimal amount, BigDecimal userID, String userLogin);

    GenericsResponse<TransactionBill> insertTrxBillPay(TransactionBill transaction);

    GenericsResponse cancelTransaction(Long transId, BigDecimal userId, Integer keepFees, String commentaire);
}
