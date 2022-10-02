package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;

public interface ThunesService {

    GenericsResponse<GetTransfinQuote> doGetTarif(GetTransfinQuoteRequest request);

    GenericsResponse<TransactionEuing> doSendTransaction(TransactionDetailsEntity trxEntity);
}
