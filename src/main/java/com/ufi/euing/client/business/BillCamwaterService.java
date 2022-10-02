package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.billpay.BillSearchResponseCamwater;

public interface BillCamwaterService {

    BillSearchResponseCamwater consultBillsCamwater(String codePartenaire, String type, String value, String requestType);

    GenericsResponse<TransactionBill> payBillCamwater(Compagnie compagnie, Guichet guichet, Facture facture, Service service, Utilisateur user, String depositorName,
                                                      String depositorPhone, String requestType);
}
