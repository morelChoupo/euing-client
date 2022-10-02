package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Pays;

import java.util.List;

public interface PaysService {

    void create(Pays pays);

    void edit(Pays pays);

    void remove(Pays pays);

    Pays find(Object id);

    List<Pays> findAll();

    List<Pays> findRange(int[] range);

    int count();

    List<Pays> getAllCountries();

    Pays getCountryByCode(String code);

    GenericsResponse<Pays> findPaysByCodeIso(String codeIso);
}
