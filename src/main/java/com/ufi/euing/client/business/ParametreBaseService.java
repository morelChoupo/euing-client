package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.ParametreBase;

import java.util.List;

public interface ParametreBaseService {

    void create(ParametreBase parametreBase);

    void edit(ParametreBase parametreBase);

    void remove(ParametreBase parametreBase);

    ParametreBase find(Object id);

    List<ParametreBase> findAll();

    List<ParametreBase> findRange(int[] range);

    int count();

    List<ParametreBase> findParametreSMS();

    List<ParametreBase> findByCle(String cle);
}
