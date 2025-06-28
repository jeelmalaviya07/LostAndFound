package com.ce.LostAndFoundManagement.LostAndFoundManagement.rest;

import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.LostItem;
import com.ce.LostAndFoundManagement.LostAndFoundManagement.service.LostItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
public class LostItemController {

    private final LostItemService lostItemService;

    public LostItemController(LostItemService lostItemService) {
        this.lostItemService = lostItemService;
    }

    @GetMapping
    public ResponseEntity<List<LostItem>> getAllLostItems() {
        return ResponseEntity.ok(lostItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LostItem> getLostItemById(@PathVariable Long id) {
        return lostItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LostItem>> getLostItemsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(lostItemService.findByUserId(userId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<LostItem>> getLostItemsByName(@PathVariable String name) {
        return ResponseEntity.ok(lostItemService.findByName(name));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<LostItem>> getLostItemsByLocation(@PathVariable String location) {
        List<LostItem> lostItems = lostItemService.findByLocation(location);
        return ResponseEntity.ok(lostItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LostItem> updateLostItem(@PathVariable Long id, @RequestBody LostItem updatedLostItem) {
        return ResponseEntity.ok(lostItemService.updateLostItem(id, updatedLostItem));
    }

    @PostMapping
    public ResponseEntity<LostItem> createLostItem(@RequestBody LostItem lostItem) {
        try {
            LostItem savedLostItem = lostItemService.saveLostItem(lostItem);
            return ResponseEntity.status(201).body(savedLostItem);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLostItem(@PathVariable Long id) {
        lostItemService.deleteLostItem(id);
        return ResponseEntity.noContent().build();
    }
}
