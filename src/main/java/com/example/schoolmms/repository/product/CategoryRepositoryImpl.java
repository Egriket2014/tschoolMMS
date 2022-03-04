package com.example.schoolmms.repository.product;

import com.example.schoolmms.entity.Category;
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
public class CategoryRepositoryImpl implements IRepository<Category, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    @Override
    public long count() {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Category.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Category> findAll() {
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        criteriaQuery
                .select(categoryRoot);

        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Category.class, id));
    }

    public Optional<Category> findByName(String categoryName) {
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        criteriaQuery
                .select(categoryRoot)
                .where(criteriaBuilder.equal(categoryRoot.get("categoryName"), categoryName));

        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public void delete(Category entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> optional = findById(id);
        optional.ifPresent(category -> entityManager.remove(category));
    }

    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void saveAll(Iterable<Category> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }
}
