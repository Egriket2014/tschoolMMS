package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Frequency;
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
public class FrequencyRepositoryImpl implements FrequencyRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Frequency> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Frequency> criteriaQuery = criteriaBuilder.createQuery(Frequency.class);
        Root<Frequency> frequencyRoot = criteriaQuery.from(Frequency.class);
        criteriaQuery.select(frequencyRoot);
        TypedQuery<Frequency> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Frequency findById(long id) {
        return entityManager.find(Frequency.class, id);
    }

    @Override
    public Frequency findByName(Integer frequency) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Frequency> criteriaQuery = criteriaBuilder.createQuery(Frequency.class);
        Root<Frequency> frequencyRoot = criteriaQuery.from(Frequency.class);

        criteriaQuery
                .select(frequencyRoot)
                .where(criteriaBuilder.equal(frequencyRoot.get("frequency"), frequency));

        TypedQuery<Frequency> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Frequency frequency) {
        entityManager.persist(frequency);
    }
}
