package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Diagonal;
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
public class DiagonalRepositoryImpl implements IRepository<Diagonal, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    @Override
    public long count() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Diagonal.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Diagonal> findAll() {
        CriteriaQuery<Diagonal> criteriaQuery = criteriaBuilder.createQuery(Diagonal.class);
        Root<Diagonal> diagonalRoot = criteriaQuery.from(Diagonal.class);

        criteriaQuery
                .select(diagonalRoot);

        TypedQuery<Diagonal> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Diagonal> findById(Long id) {
        return Optional.of(entityManager.find(Diagonal.class, id));
    }

    public Optional<Diagonal> findByName(String diagonal) {
        CriteriaQuery<Diagonal> criteriaQuery = criteriaBuilder.createQuery(Diagonal.class);
        Root<Diagonal> diagonalRoot = criteriaQuery.from(Diagonal.class);

        criteriaQuery
                .select(diagonalRoot)
                .where(criteriaBuilder.equal(diagonalRoot.get("diagonal"), Integer.valueOf(diagonal)));

        TypedQuery<Diagonal> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Diagonal entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Diagonal> optional = findById(id);
        optional.ifPresent(brand -> entityManager.remove(brand));
    }

    @Override
    public void save(Diagonal diagonal) {
        entityManager.persist(diagonal);
    }

    @Override
    public void saveAll(Iterable<Diagonal> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Diagonal diagonal) {
        entityManager.merge(diagonal);
    }
}
