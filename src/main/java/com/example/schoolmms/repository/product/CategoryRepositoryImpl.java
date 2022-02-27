package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Category;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);
        criteriaQuery.select(categoryRoot);
        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Category findById(long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category findByName(String categoryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        criteriaQuery
                .select(categoryRoot)
                .where(criteriaBuilder.equal(categoryRoot.get("categoryName"), categoryName));

        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }
}
