package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Diagonal;
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
public class DiagonalRepositoryImpl implements DiagonalRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Diagonal> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Diagonal> criteriaQuery = criteriaBuilder.createQuery(Diagonal.class);
        Root<Diagonal> diagonalRoot = criteriaQuery.from(Diagonal.class);
        criteriaQuery.select(diagonalRoot);
        TypedQuery<Diagonal> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Diagonal findById(long id) {
        return entityManager.find(Diagonal.class, id);
    }

    @Override
    public Diagonal findByName(Integer diagonal) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Diagonal> criteriaQuery = criteriaBuilder.createQuery(Diagonal.class);
        Root<Diagonal> diagonalRoot = criteriaQuery.from(Diagonal.class);

        criteriaQuery
                .select(diagonalRoot)
                .where(criteriaBuilder.equal(diagonalRoot.get("diagonal"), diagonal));

        TypedQuery<Diagonal> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Diagonal diagonal) {
        entityManager.persist(diagonal);
    }
}
