package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.CompagnieServiceSms;

import java.util.List;

public interface CompagnieServiceSmsService {

    void create(CompagnieServiceSms compagnieServiceSms);

    void edit(CompagnieServiceSms compagnieServiceSms);

    void remove(CompagnieServiceSms compagnieServiceSms);

    CompagnieServiceSms find(Object id);

    CompagnieServiceSms findByCompagnieAndService(Long cmp,Long serv);

    List<CompagnieServiceSms> findAll();

    List<CompagnieServiceSms> findRange(int[] range);

    int count();
}
