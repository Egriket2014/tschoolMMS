package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Resolution;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ResolutionRepositoryImpl implements ResolutionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Resolution> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resolution> criteriaQuery = criteriaBuilder.createQuery(Resolution.class);
        Root<Resolution> resolutionRoot = criteriaQuery.from(Resolution.class);
        criteriaQuery.select(resolutionRoot);
        TypedQuery<Resolution> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Resolution findById(long id) {
        return entityManager.find(Resolution.class, id);
    }

    @Override
    public Resolution findByName(String resolution) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resolution> criteriaQuery = criteriaBuilder.createQuery(Resolution.class);
        Root<Resolution> resolutionRoot = criteriaQuery.from(Resolution.class);

        criteriaQuery
                .select(resolutionRoot)
                .where(criteriaBuilder.equal(resolutionRoot.get("resolution"), resolution));

        TypedQuery<Resolution> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Resolution resolution) {
        entityManager.persist(resolution);
    }
}
