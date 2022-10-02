package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.GetTransfinQuoteRequest;
import com.ufi.euing.client.entity.TransactionDetailsEntity;

public interface GimacService {

    GenericsResponse doGetTarif(GetTransfinQuoteRequest request);

    GenericsResponse doSendTransaction(TransactionDetailsEntity trxEntity);
}
