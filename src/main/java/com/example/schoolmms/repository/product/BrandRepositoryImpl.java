package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Brand;
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
public class BrandRepositoryImpl implements IRepository<Brand, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    @Override
    public long count() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Brand.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Brand> findAll() {
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
        Root<Brand> brandRoot = criteriaQuery.from(Brand.class);

        criteriaQuery
                .select(brandRoot);

        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.of(entityManager.find(Brand.class, id));
    }

    public Optional<Brand> findByName(String brandName) {
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);
        Root<Brand> brandRoot = criteriaQuery.from(Brand.class);

        criteriaQuery
                .select(brandRoot)
                .where(criteriaBuilder.equal(brandRoot.get("brandName"), brandName));

        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Brand entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Brand> optional = findById(id);
        optional.ifPresent(brand -> entityManager.remove(brand));
    }

    @Override
    public void saveAll(Iterable<Brand> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Brand brand) {
        entityManager.merge(brand);
    }

    @Override
    public void save(Brand brand) {
        entityManager.persist(brand);
    }
}
