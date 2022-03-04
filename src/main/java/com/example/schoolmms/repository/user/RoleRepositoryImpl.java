package com.example.schoolmms.repository.user;

import com.example.schoolmms.entity.Role;
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
public class RoleRepositoryImpl implements IRepository<Role, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Role> findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);

        criteriaQuery
                .select(roleRoot)
                .where(criteriaBuilder.equal(roleRoot.get("name"), name));

        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(typedQuery.getSingleResult());
    }

    @Override
    public long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery
                .select(criteriaBuilder.count(criteriaQuery.from(Role.class)));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);

        criteriaQuery
                .select(roleRoot);

        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.of(entityManager.find(Role.class, id));
    }

    @Override
    @Transactional
    public void delete(Role entity) {
        entityManager.remove(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Role> optional = findById(id);
        optional.ifPresent(role -> entityManager.remove(role));
    }

    @Override
    @Transactional
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<Role> entities) {
        entities.forEach(entityManager::persist);
    }

    @Override
    @Transactional
    public void update(Role role) {
        entityManager.merge(role);
    }
}
