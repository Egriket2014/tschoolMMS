package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Product;
import com.example.schoolmms.repository.product.searchCriteria.ProductSearchQueryCriteriaConsumer;
import com.example.schoolmms.repository.product.searchCriteria.SearchCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

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


    @Override
    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findByParam(List<SearchCriteria> params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate predicate = criteriaBuilder.conjunction();
        ProductSearchQueryCriteriaConsumer productConsumer =
                new ProductSearchQueryCriteriaConsumer(predicate, criteriaBuilder, productRoot);
        params.stream().forEach(productConsumer);
        predicate = productConsumer.getPredicate();

        criteriaQuery
                .where(criteriaBuilder.equal(productRoot.get("active"), true),
                        predicate);

        List<Product> result = entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    @Override
    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }
}
