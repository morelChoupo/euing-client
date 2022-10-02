package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Facture;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.TransactionBill;
import com.ufi.euing.client.integration.billpay.BillResponse;
import com.ufi.euing.client.integration.billpay.ConsultBill;
import com.ufi.euing.client.integration.billpay.PayBill;

public interface BillPayService {

    BillResponse consultBill(ConsultBill consultBill);

    GenericsResponse<TransactionBill> PayBill(PayBill payBill, Facture facture);
}
