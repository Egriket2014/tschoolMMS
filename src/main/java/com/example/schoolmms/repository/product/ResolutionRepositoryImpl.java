package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Resolution;
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
public class ResolutionRepositoryImpl implements IRepository<Resolution, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    @Override
    public long count() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Resolution.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Resolution> findAll() {
        CriteriaQuery<Resolution> criteriaQuery = criteriaBuilder.createQuery(Resolution.class);
        Root<Resolution> resolutionRoot = criteriaQuery.from(Resolution.class);

        criteriaQuery
                .select(resolutionRoot);

        TypedQuery<Resolution> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Resolution> findById(Long id) {
        return Optional.of(entityManager.find(Resolution.class, id));
    }

    public Optional<Resolution> findByName(String resolution) {
        CriteriaQuery<Resolution> criteriaQuery = criteriaBuilder.createQuery(Resolution.class);
        Root<Resolution> resolutionRoot = criteriaQuery.from(Resolution.class);

        criteriaQuery
                .select(resolutionRoot)
                .where(criteriaBuilder.equal(resolutionRoot.get("resolution"), resolution));

        TypedQuery<Resolution> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Resolution entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Resolution> optional = findById(id);
        optional.ifPresent(resolution -> entityManager.remove(resolution));
    }

    @Override
    public void save(Resolution resolution) {
        entityManager.persist(resolution);
    }

    @Override
    public void saveAll(Iterable<Resolution> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Resolution resolution) {
        entityManager.merge(resolution);
    }
}
