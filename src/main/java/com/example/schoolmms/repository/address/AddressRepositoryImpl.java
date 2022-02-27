package com.example.schoolmms.repository.address;

import com.example.schoolmms.entity.Address;
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
public class AddressRepositoryImpl implements AddressRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Address> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> criteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = criteriaQuery.from(Address.class);
        criteriaQuery.select(addressRoot);
        TypedQuery<Address> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    @Transactional
    public void save(Address address) {
        entityManager.persist(address);
    }
}
