package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Product;
import com.example.schoolmms.repository.IRepository;
import com.example.schoolmms.repository.product.filter.ProductSearchQueryCriteriaConsumer;
import com.example.schoolmms.repository.product.filter.SearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements IRepository<Product, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Product.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Product> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        criteriaQuery
                .select(productRoot);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public Optional<Product> findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        criteriaQuery
                .select(productRoot)
                .where(criteriaBuilder.equal(productRoot.get("productTitle"), name));

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Product entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(product -> entityManager.remove(product));
    }

    public List<Product> findAllActive() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        criteriaQuery
                .select(productRoot)
                .where(criteriaBuilder.equal(productRoot.get("active"), true));

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public List<Product> findByParam(List<SearchCriteria> params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate predicate = criteriaBuilder.conjunction();
        ProductSearchQueryCriteriaConsumer productConsumer =
                new ProductSearchQueryCriteriaConsumer(predicate, criteriaBuilder, productRoot);
        params.forEach(productConsumer);
        predicate = productConsumer.getPredicate();

        criteriaQuery
                .where(criteriaBuilder.equal(productRoot.get("active"), true),
                        predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void saveAll(Iterable<Product> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }
}
