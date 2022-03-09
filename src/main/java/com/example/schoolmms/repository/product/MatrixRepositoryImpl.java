package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Matrix;
import com.example.schoolmms.repository.IRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class MatrixRepositoryImpl implements IRepository<Matrix, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Matrix.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Matrix> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matrix> criteriaQuery = criteriaBuilder.createQuery(Matrix.class);
        Root<Matrix> matrixRoot = criteriaQuery.from(Matrix.class);

        criteriaQuery
                .select(matrixRoot);

        TypedQuery<Matrix> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Matrix> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Matrix.class, id));
    }

    public Optional<Matrix> findByName(String matrixName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matrix> criteriaQuery = criteriaBuilder.createQuery(Matrix.class);
        Root<Matrix> matrixRoot = criteriaQuery.from(Matrix.class);

        criteriaQuery
                .select(matrixRoot)
                .where(criteriaBuilder.equal(matrixRoot.get("matrixName"), matrixName));

        TypedQuery<Matrix> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Matrix entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(matrix -> entityManager.remove(matrix));
    }

    @Override
    public void save(Matrix matrix) {
        entityManager.persist(matrix);
    }

    @Override
    public void saveAll(Iterable<Matrix> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Matrix matrix) {
        entityManager.merge(matrix);
    }
}
