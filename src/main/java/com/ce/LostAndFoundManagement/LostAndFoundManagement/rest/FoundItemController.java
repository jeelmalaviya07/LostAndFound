package com.ce.LostAndFoundManagement.LostAndFoundManagement.rest;

import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.FoundItem;
import com.ce.LostAndFoundManagement.LostAndFoundManagement.service.FoundItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/found-items")
public class FoundItemController {

    private final FoundItemService foundItemService;

    public FoundItemController(FoundItemService foundItemService) {
        this.foundItemService = foundItemService;
    }

    @GetMapping
    public ResponseEntity<List<FoundItem>> getAllFoundItems() {
        return ResponseEntity.ok(foundItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoundItem> getFoundItemById(@PathVariable Long id) {
        return foundItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FoundItem>> getFoundItemsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(foundItemService.findByUserId(userId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<FoundItem>> getFoundItemsByName(@PathVariable String name) {
        return ResponseEntity.ok(foundItemService.findByName(name));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<FoundItem>> getFoundItemsByLocation(@PathVariable String location) {
        List<FoundItem> foundItems = foundItemService.findByLocation(location);
        return ResponseEntity.ok(foundItems);
    }

    @PostMapping
    public ResponseEntity<FoundItem> createFoundItem(@RequestBody FoundItem foundItem) {
        return ResponseEntity.status(201).body(foundItemService.saveFoundItem(foundItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoundItem(@PathVariable Long id) {
        foundItemService.deleteFoundItem(id);
        return ResponseEntity.noContent().build();
    }
}
