package com.ce.LostAndFoundManagement.LostAndFoundManagement.dao;

import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.FoundItem;

import java.util.List;
import java.util.Optional;

public interface FoundItemDAO {
    Optional<FoundItem> findById(Long id);
    List<FoundItem> findByUserId(Long userId);
    List<FoundItem> findAll();
    List<FoundItem> findByName(String name);

    // ✅ New Method: Find items by location
    List<FoundItem> findByLocation(String location);

    FoundItem save(FoundItem foundItem);
    void deleteById(Long id);
}
