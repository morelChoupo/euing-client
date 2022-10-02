package com.ufi.euing.client.business.impl;

import com.ufi.euing.client.business.AbstractFacade;
import com.ufi.euing.client.business.ListToUseService;
import com.ufi.euing.client.entity.GenericsResponse;
import com.ufi.euing.client.entity.ListeToUse;
import com.ufi.euing.client.exceptions.domain.EmailNotFoundException;
import com.ufi.euing.client.repositories.ListToUseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ListToUseServiceImpl extends AbstractFacade<ListeToUse> implements ListToUseService {

    @Autowired
    ListToUseRepository listToUseRepository;

    @Autowired
    private EntityManager em;

    public ListToUseServiceImpl() {
        super(ListeToUse.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @Override
    public List<ListeToUse> findAllActive() {
        short sh = 1;
        Query query = em.createNamedQuery("ListeToUse.findByStatut");
        query.setParameter("statut", sh);
        return query.getResultList();
    }

    @Override
    public List<ListeToUse> findAllActiveByUsage(String usage) {
        short sh = 1;
        Query query = em.createNamedQuery("ListeToUse.findByUsage");
        query.setParameter("statut", sh);
        query.setParameter("usage", usage);
        return query.getResultList();
    }

    @Override
    public GenericsResponse<ListeToUse> createListeToUse(ListeToUse listeToUse) {
        try {

            List<ListeToUse> list = em.createNamedQuery("ListeToUse.checkDoublon").setParameter("libelle", listeToUse.getLibelle()).setParameter("usage", listeToUse.getUsage()).getResultList();
            if (!list.isEmpty()) {
                return new GenericsResponse<>(200, "Value already exists in List!", list.get(0));
            }

            listeToUse.setDateModif(new Date());
            listeToUse.setDateCreate(new Date());
            listToUseRepository.save(listeToUse);

            return new GenericsResponse<>(listeToUse);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new ListeToUse());
        }
    }

    @Override
    public GenericsResponse<ListeToUse> updateListeToUse(ListeToUse listeToUse) {
        try {
            listeToUse.setDateModif(new Date());
            listToUseRepository.save(listeToUse);

            return new GenericsResponse<>(listeToUse);
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new ListeToUse());
        }
    }

    @Override
    public List<ListeToUse> getListeToUse() {
        Query query = em.createNamedQuery("ListeToUse.findAll");
        return query.getResultList();

    }

    @Override
    public GenericsResponse<ListeToUse> getListeToUseById(BigDecimal id) {
        try {
            ListeToUse listeToUse = listToUseRepository.findById(id).orElseThrow(()-> new EmailNotFoundException("Liste to use not found"));
            if (listeToUse != null) {
                return new GenericsResponse<>(listeToUse);
            } else {
                return new GenericsResponse<>(404, "Data not exist with reference " + id, new ListeToUse());
            }
        } catch (Exception e) {
            return new GenericsResponse<>(500, "An error occured. Details : " + e.getMessage(), new ListeToUse());
        }
    }

    @Override
    public List<ListeToUse> getListeToUseByUsage(String usage) {
        Short sh = Short.valueOf("1");
        Query query = em.createNamedQuery("ListeToUse.findByUsageStatut").setParameter("usage", usage).setParameter("statut", sh);
        return query.getResultList();
    }

    @Override
    public List<ListeToUse> getListeToUseByUsageAndLibelle(String usage, String libelle) {
        Short sh = Short.valueOf("1");
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<ListeToUse> criteriaQuery = criteriaBuilder.createQuery(ListeToUse.class);
        final Root<ListeToUse> root = criteriaQuery.from(ListeToUse.class);
        final List<Predicate> where = new ArrayList<>();
        /**
         * chaining criterias
         */

        Optional.ofNullable(usage).ifPresent(req ->
                where.add(criteriaBuilder.like(root.get("usage"), "%" + usage + "%"))
        );

        Optional.ofNullable(libelle).ifPresent(req ->
                where.add(criteriaBuilder.like(root.get("libelle"), "%" + libelle + "%"))
        );

        Optional.ofNullable(sh).ifPresent(req ->
                where.add(criteriaBuilder.like(root.get("statut").as(String.class), "%" + sh + "%"))
        );

        criteriaQuery.select(root).where(where.stream().toArray(Predicate[]::new));
        final TypedQuery<ListeToUse> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }



}
