package com.ufi.euing.client.business;

import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Horaire;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface HoraireService {

    void create(Horaire horaire);

    void edit(Horaire horaire);

    void remove(Horaire horaire);

    Horaire find(Object id);

    List<Horaire> findAll();

    List<Horaire> findRange(int[] range);

    int count();

    public GenericsResponse<Horaire> createHoraire(Horaire horaire);
    public GenericsResponse<Horaire> updateHoraire(Horaire horaire);
    public List<Horaire> getHoraire();
    public GenericsResponse<Horaire> getHoraireById(BigDecimal id);
    public GenericsResponse<Horaire> activateHoraire(Horaire horaire);
    public GenericsResponse<Horaire> desactivateHoraire(Horaire horaire);
    public GenericsResponse<Horaire> findHoraireByDayTypeUoUoId(BigInteger day, String typeUo, BigInteger uoId);
}
