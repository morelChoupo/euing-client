package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.Compagnie;

import java.util.List;

public interface CompagnieService {

    void create(Compagnie compagnie);

    void edit(Compagnie compagnie);

    void remove(Compagnie compagnie);

    Compagnie find(Object id);

    List<Compagnie> findAll();

    List<Compagnie> findRange(int[] range);

    int count();

     Compagnie findByCodePartenaire(String code);

     List<Compagnie> findBySuccursale(Long code);

     List<Compagnie> findByPays(String code);

    List<Compagnie> getCompagnieByStatut(short status);
}
