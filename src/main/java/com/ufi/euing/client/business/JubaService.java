package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;
import com.ufi.euing.client.integration.juba.response.DoGetBeneficiaryNameDetails;
import com.ufi.euing.client.integration.juba.response.GetAgentsDetailsResponse;
import com.ufi.euing.client.integration.juba.response.ListeToUseDetailsResponse;

public interface JubaService {

    GetAgentsDetailsResponse doGetAgent(String countryCode);

    ListeToUseDetailsResponse doGetPurpose();

    ListeToUseDetailsResponse doGetSourceOfIncome();

    ListeToUseDetailsResponse doGetCollectionMode();

    GenericsResponse<GetTransfinQuote> doGetTarif(GetTransfinQuoteRequest request);

    GenericsResponse<TransactionEuing> doSendTransaction(TransactionDetailsEntity trxEntity);

    GenericsResponse<TransactionDetailsEntity> doGetTransaction(CriterialSearchPayment criterial);

    GenericsResponse<TransactionEuing> doPaymentConfirmation(TransactionDetailsEntity trxEntity);

    GenericsResponse<DoGetBeneficiaryNameDetails> jubaDoGetBeneficiaryName(String mobileNo);
}
