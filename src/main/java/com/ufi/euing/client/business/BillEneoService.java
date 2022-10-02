package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.BillSearchResponse;
import com.ufi.euing.client.integration.billpay.CheckBillResponse;
import com.ufi.euing.client.integration.billpay.LoginResponse;

import java.math.BigDecimal;

public interface BillEneoService {

    BillSearchResponse consultBillsEneo(String codePartenaire, String type, String value, String requestType);

    GenericsResponse<Facture> calculFeesEneo(Compagnie compagnie, Guichet guichet, Long serviceID, String ServiceName, Facture facture, BigDecimal userID, String userLogin);

    GenericsResponse<CheckBillResponse> precheckFeesEneo(Compagnie compagnie, Guichet guichet, Service service, BigDecimal montant, Utilisateur user);

    GenericsResponse<TransactionBill> payBillEneo(Compagnie compagnie, Guichet guichet, Facture facture, Service service, Utilisateur user, String depositorName, String depositorPhone, String requestType);

    GenericsResponse<TransactionBill> payBillEneoRecall(TransactionBill transaction, String requestType);

    LoginResponse authentifiationEneo(Parametrews parametrews);

    CheckBillResponse checkBillStatus(String transactionId, String token, String url);
}
