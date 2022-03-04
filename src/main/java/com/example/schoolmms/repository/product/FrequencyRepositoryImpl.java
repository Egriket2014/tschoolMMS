package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Frequency;
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
public class FrequencyRepositoryImpl implements IRepository<Frequency, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Frequency.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Frequency> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Frequency> criteriaQuery = criteriaBuilder.createQuery(Frequency.class);
        Root<Frequency> frequencyRoot = criteriaQuery.from(Frequency.class);

        criteriaQuery
                .select(frequencyRoot);

        TypedQuery<Frequency> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Frequency> findById(Long id) {
        return Optional.of(entityManager.find(Frequency.class, id));
    }

    public Optional<Frequency> findByName(String frequency) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Frequency> criteriaQuery = criteriaBuilder.createQuery(Frequency.class);
        Root<Frequency> frequencyRoot = criteriaQuery.from(Frequency.class);

        criteriaQuery
                .select(frequencyRoot)
                .where(criteriaBuilder.equal(frequencyRoot.get("frequency"), Integer.valueOf(frequency)));

        TypedQuery<Frequency> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    @Transactional
    public void delete(Frequency entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Frequency> optional = findById(id);
        optional.ifPresent(frequency -> entityManager.remove(frequency));
    }

    @Override
    @Transactional
    public void save(Frequency frequency) {
        entityManager.persist(frequency);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Frequency> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    @Transactional
    public void update(Frequency frequency) {
        entityManager.merge(frequency);
    }
}
