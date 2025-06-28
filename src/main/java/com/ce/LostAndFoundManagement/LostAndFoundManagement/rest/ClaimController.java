package com.ce.LostAndFoundManagement.LostAndFoundManagement.rest;

import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.Claim;
import com.ce.LostAndFoundManagement.LostAndFoundManagement.entity.ClaimStatus;
import com.ce.LostAndFoundManagement.LostAndFoundManagement.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        return claimService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Claim>> getClaimsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(claimService.findByUserId(userId));
    }

    @GetMapping("/lost-item/{lostItemId}")
    public ResponseEntity<List<Claim>> getClaimsByLostItemId(@PathVariable Long lostItemId) {
        return ResponseEntity.ok(claimService.findByLostItemId(lostItemId));
    }

    @GetMapping("/found-item/{foundItemId}")
    public ResponseEntity<List<Claim>> getClaimsByFoundItemId(@PathVariable Long foundItemId) {
        return ResponseEntity.ok(claimService.findByFoundItemId(foundItemId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Claim>> getClaimsByStatus(@PathVariable ClaimStatus status) {
        return ResponseEntity.ok(claimService.findByStatus(status));
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<Claim>> getClaimsByUserIdAndStatus(@PathVariable Long userId, @PathVariable ClaimStatus status) {
        List<Claim> claims = claimService.findByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/matching")
    public ResponseEntity<List<Claim>> getMatchingClaimsByNameAndLocation() {
        List<Claim> matchingClaims = claimService.findMatchingClaimsByNameAndLocation();
        return ResponseEntity.ok(matchingClaims);
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        return ResponseEntity.status(201).body(claimService.createClaim(claim));
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Map<String, Object>> updateClaimStatus(
            @PathVariable Long id, @PathVariable String status) {

        // Validate and convert to Enum
        ClaimStatus claimStatus;
        try {
            claimStatus = ClaimStatus.valueOf(status.toUpperCase()); // Convert input to uppercase for safety
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid status value"));
        }

        Claim updatedClaim = claimService.updateClaimStatus(id, claimStatus);

        if (updatedClaim == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Claim not found"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Claim status updated successfully",
                "claim", updatedClaim
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}
