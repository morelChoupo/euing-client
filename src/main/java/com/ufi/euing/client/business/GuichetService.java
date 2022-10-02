package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Guichet;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;

import java.util.List;

public interface GuichetService {

    void create(Guichet guichet);

    void edit(Guichet guichet);

    void remove(Guichet guichet);

    Guichet find(Object id);

    Guichet getById(Long id) throws EmailNotFoundException;

    List<Guichet> findAll();

    List<Guichet> findRange(int[] range);

    int count();

    public List<Guichet> findByAgence(Long code);

    List<Guichet> getGuichetByStatut(short status);
}
