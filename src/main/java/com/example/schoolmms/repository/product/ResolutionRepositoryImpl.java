package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Resolution;
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
public class ResolutionRepositoryImpl implements IRepository<Resolution, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Resolution.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Resolution> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Resolution> criteriaQuery = criteriaBuilder.createQuery(Resolution.class);
        Root<Resolution> resolutionRoot = criteriaQuery.from(Resolution.class);

        criteriaQuery
                .select(resolutionRoot)
                .where(criteriaBuilder.equal(resolutionRoot.get("resolution"), resolution));

        TypedQuery<Resolution> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    @Transactional
    public void delete(Resolution entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Resolution> optional = findById(id);
        optional.ifPresent(resolution -> entityManager.remove(resolution));
    }

    @Override
    @Transactional
    public void save(Resolution resolution) {
        entityManager.persist(resolution);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Resolution> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Resolution resolution) {
        entityManager.merge(resolution);
    }
}
