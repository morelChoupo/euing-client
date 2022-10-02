package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Facture;

import java.util.List;

public interface FactureService {

    void create(Facture facture);

    Facture createFctureDb(Facture facture);

    void edit(Facture facture);

    void remove(Facture facture);

    List<Facture> findByFactureNumber(String param);

    List<Facture> findByNumAbonne(String param);

    Facture findById(Long param);

    Facture find(Object id);

    List<Facture> findAll();

    List<Facture> findRange(int[] range);

    int count();
}
