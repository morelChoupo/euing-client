package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.*;

import java.util.Date;
import java.util.List;

public interface VwTransactionEuingService {

    List<VwTransactionEuing> searchTransactions(Date searchDateStart, Date searchDateEnd, Succursale searchGroupeId, Compagnie searchCompagnieId, Agence searchAgenceId, Guichet searchGuichetId,
                                                String searchReference, String searchCodeClient, String searchExpediteur, String searchBeneficiaire, Pays searchDestinationCode,
                                                String searchStatut, Service searchServiceId, String searchIdSender, String searchIdBenef, String searchPhoneSender,
                                                String searchPhoneBenef);
}
