package com.example.cslink.tenant.model.dao.impl;

import com.example.cslink.tenant.model.dao.TenantDao;
import com.example.cslink.tenant.model.entity.Tenant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TenantDaoImpl implements TenantDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Tenant> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Tenant.class, id));
    }

    @Override
    public Optional<Tenant> findByName(String name) {
        TypedQuery<Tenant> query=entityManager.createQuery(
                "SELECT t FROM Tenant t WHERE t.name = :name", Tenant.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public Tenant save(Tenant tenant) {
        entityManager.persist(tenant);
        return tenant;
    }

    @Override
    public void delete(Tenant tenant) {
        entityManager.remove(tenant);
    }

    @Override
    public List<Tenant> findAll() {
        TypedQuery<Tenant> query=entityManager.createQuery("SELECT t FROM Tenant t", Tenant.class);
        return query.getResultList();
    }

}
