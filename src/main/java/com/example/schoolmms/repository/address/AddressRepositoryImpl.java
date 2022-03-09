package com.example.schoolmms.repository.address;

import com.example.schoolmms.entity.Address;
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
public class AddressRepositoryImpl implements IRepository<Address, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Address.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Address> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = criteriaQuery.from(Address.class);

        criteriaQuery
                .select(addressRoot);

        TypedQuery<Address> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    public void delete(Address entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(address -> entityManager.remove(address));
    }

    @Override
    public void save(Address address) {
        entityManager.persist(address);
    }

    @Override
    public void saveAll(Iterable<Address> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }

    public List<Address> findByUserId(Long userId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = criteriaQuery.from(Address.class);

        criteriaQuery
                .select(addressRoot)
                .where(criteriaBuilder.equal(addressRoot.get("user").get("id"), userId));

        TypedQuery<Address> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
