package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.ListeToUse;

import java.math.BigDecimal;
import java.util.List;

public interface ListToUseService {

    void create(ListeToUse listeToUse);

    void edit(ListeToUse listeToUse);

    void remove(ListeToUse listeToUse);

    ListeToUse find(Object id);

    List<ListeToUse> findAll();

    List<ListeToUse> findRange(int[] range);

    int count();


    List<ListeToUse> findAllActive();

    List<ListeToUse> findAllActiveByUsage(String usage);

     GenericsResponse<ListeToUse> createListeToUse(ListeToUse listToUse);
     GenericsResponse<ListeToUse> updateListeToUse(ListeToUse listToUse);
     List<ListeToUse> getListeToUse();
     GenericsResponse<ListeToUse> getListeToUseById(BigDecimal id);
     List<ListeToUse> getListeToUseByUsage(String usage);

    List<ListeToUse> getListeToUseByUsageAndLibelle(String usage, String libelle);

}
