package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Brand;
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
public class BrandRepositoryImpl implements BrandRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Brand> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
        Root<Brand> brandRoot = criteriaQuery.from(Brand.class);
        criteriaQuery.select(brandRoot);
        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Brand findById(long id) {
        return entityManager.find(Brand.class, id);
    }

    @Override
    public Brand findByName(String brandName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
        Root<Brand> brandRoot = criteriaQuery.from(Brand.class);

        criteriaQuery
                .select(brandRoot)
                .where(criteriaBuilder.equal(brandRoot.get("brandName"), brandName));

        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Brand brand) {
        entityManager.persist(brand);
    }
}
