package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Matrix;
import com.example.schoolmms.repository.IRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
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
        return Optional.of(entityManager.find(Matrix.class, id));
    }

    public Optional<Matrix> findByName(String matrixName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matrix> criteriaQuery = criteriaBuilder.createQuery(Matrix.class);
        Root<Matrix> matrixRoot = criteriaQuery.from(Matrix.class);

        criteriaQuery
                .select(matrixRoot)
                .where(criteriaBuilder.equal(matrixRoot.get("matrixName"), matrixName));

        TypedQuery<Matrix> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    @Transactional
    public void delete(Matrix entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Matrix> optional = findById(id);
        optional.ifPresent(matrix -> entityManager.remove(matrix));
    }

    @Override
    @Transactional
    public void save(Matrix matrix) {
        entityManager.persist(matrix);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Matrix> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    @Transactional
    public void update(Matrix matrix) {
        entityManager.merge(matrix);
    }
}
