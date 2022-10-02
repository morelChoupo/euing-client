package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.CriterialSearchPayment;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.TransactionDetailsEntity;
import com.ufi.euing.client.entity.TransactionEuing;

public interface WorldRemitService {

    GenericsResponse<TransactionDetailsEntity> doGetTransaction(CriterialSearchPayment criterial);

    GenericsResponse<TransactionEuing> doPayTransaction(TransactionDetailsEntity trxEntity);
}
