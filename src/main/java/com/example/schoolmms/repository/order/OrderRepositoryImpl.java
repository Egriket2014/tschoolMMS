package com.example.schoolmms.repository.order;

import com.example.schoolmms.entity.Order;
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
public class OrderRepositoryImpl implements IRepository<Order, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Order.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Order> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);

        criteriaQuery
                .select(orderRoot);

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.of(entityManager.find(Order.class, id));
    }

    @Override
    @Transactional
    public void delete(Order entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Order> optional = findById(id);
        optional.ifPresent(order -> entityManager.remove(order));
    }

    @Override
    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Order> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    @Transactional
    public void update(Order order) {
        entityManager.merge(order);
    }

    public List<Order> findByUserId(long userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);

        criteriaQuery
                .select(orderRoot)
                .where(criteriaBuilder.equal(orderRoot.get("user").get("id"), userId));

        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
