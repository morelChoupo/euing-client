package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.TypePieceIdentiteService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.TypePieceIdentite;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.repositories.TypePieceIdentiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;


@Component
public class TypePieceIdentiteServiceImpl extends AbstractFacade<TypePieceIdentite> implements TypePieceIdentiteService {

    final TypePieceIdentiteRepository repository;

    @Autowired
    private EntityManager em;

    public TypePieceIdentiteServiceImpl(TypePieceIdentiteRepository repository) {
        super(TypePieceIdentite.class);
        this.repository = repository;
    }

    @Override
    public GenericsResponse<TypePieceIdentite> getTypePieceIdentiteByCode(String code) {
        try {
            TypePieceIdentite tf = repository.findById(code).orElseThrow(()-> new EmailNotFoundException("Identiter not found"));
            if (tf != null) {
                return new GenericsResponse<>(tf);
            } else {
                return new GenericsResponse<>(404, "Data not exist with reference " + code, new TypePieceIdentite());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new TypePieceIdentite());
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
