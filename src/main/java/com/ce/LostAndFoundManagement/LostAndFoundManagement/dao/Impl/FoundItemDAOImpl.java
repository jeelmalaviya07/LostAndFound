package com.ce.LostAndFoundManagement.LostAndFoundManagement.dao.Impl;

import com.ce.LostAndFoundManagement.LostAndFoundManagement.dao.FoundItemDAO;
import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.FoundItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FoundItemDAOImpl implements FoundItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<FoundItem> findById(Long id) {
        return Optional.ofNullable(entityManager.find(FoundItem.class, id));
    }

    @Override
    public List<FoundItem> findByUserId(Long userId) {
        return entityManager.createQuery("SELECT f FROM FoundItem f WHERE f.user.id = :userId", FoundItem.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<FoundItem> findByLocation(String location) {
        return entityManager.createQuery("SELECT f FROM FoundItem f WHERE f.location = :location", FoundItem.class)
                .setParameter("location", location)
                .getResultList();
    }

    @Override
    public List<FoundItem> findAll() {
        return entityManager.createQuery("SELECT f FROM FoundItem f", FoundItem.class).getResultList();
    }

    @Override
    public List<FoundItem> findByName(String name) {
        return entityManager.createQuery("SELECT f FROM FoundItem f WHERE f.name = :name", FoundItem.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public FoundItem save(FoundItem foundItem) {
        if (foundItem.getId() == null) {
            entityManager.persist(foundItem);
            return foundItem;
        } else {
            return entityManager.merge(foundItem);
        }
    }

    @Override
    public void deleteById(Long id) {
        FoundItem foundItem = entityManager.find(FoundItem.class, id);
        if (foundItem != null) {
            entityManager.remove(foundItem);
        }
    }
}
