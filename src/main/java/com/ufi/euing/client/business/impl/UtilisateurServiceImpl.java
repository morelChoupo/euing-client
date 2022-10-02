package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.UtilisateurService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.Utilisateur;
import com.ufi.euing.client.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class UtilisateurServiceImpl extends AbstractFacade<Utilisateur> implements UtilisateurService {

    final UtilisateurRepository repository;

    public UtilisateurServiceImpl(UtilisateurRepository repository) {
        super(Utilisateur.class);
        this.repository = repository;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Autowired
    private EntityManager em;

    @Override
    public GenericsResponse<Utilisateur> updateUtilisateur(Utilisateur user) {
        return null;
    }

    @Override
    public List<Utilisateur> getUtilisateur() {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> getUtilisateurById(Long id) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> getUtilisateurByLogin(String login) {
        return null;
    }

    @Override
    public List<Utilisateur> searchUtilisateurs(String login, String nom, String prenom, String fctCode, int status) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> activateUtilisateur(Utilisateur user) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> desactivateUtilisateur(Utilisateur user) {
        return null;
    }

    @Override
    public List<Utilisateur> searchUsersOnline(String login, String nom, String prenom, String fctCode, int status) {
        return null;
    }

    @Override
    public List<Utilisateur> searchUsersDesactivated(String login, String nom, String prenom, String fctCode) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> resetPassword(Utilisateur user) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> setUnitOrgUser(Long idUser, String typeUo, int uoId) {
        return null;
    }

    @Override
    public GenericsResponse<Boolean> checkSessionUser(Long idUser) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> findByUoAndTypeUo(int uoId, String typeUo) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> login(String email, String password) {
        return null;
    }

    @Override
    public GenericsResponse<Utilisateur> createUtilisateur(Utilisateur user) {
        return null;
    }
}
