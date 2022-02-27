package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Matrix;
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
public class MatrixRepositoryImpl implements MatrixRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Matrix> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matrix> criteriaQuery = criteriaBuilder.createQuery(Matrix.class);
        Root<Matrix> matrixRoot = criteriaQuery.from(Matrix.class);
        criteriaQuery.select(matrixRoot);
        TypedQuery<Matrix> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Matrix findById(long id) {
        return entityManager.find(Matrix.class, id);
    }

    @Override
    public Matrix findByName(String matrixName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matrix> criteriaQuery = criteriaBuilder.createQuery(Matrix.class);
        Root<Matrix> matrixRoot = criteriaQuery.from(Matrix.class);

        criteriaQuery
                .select(matrixRoot)
                .where(criteriaBuilder.equal(matrixRoot.get("matrixName"), matrixName));

        TypedQuery<Matrix> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Matrix matrix) {
        entityManager.persist(matrix);
    }
}
