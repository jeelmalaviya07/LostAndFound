package com.ce.LostAndFoundManagement.LostAndFoundManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lost_item_id")
    private LostItem lostItem;

    @ManyToOne
    @JoinColumn(name = "found_item_id")
    private FoundItem foundItem;

    @Column(nullable = false)
    private LocalDateTime claimDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LostItem getLostItem() {
		return lostItem;
	}

	public void setLostItem(LostItem lostItem) {
		this.lostItem = lostItem;
	}

	public FoundItem getFoundItem() {
		return foundItem;
	}

	public void setFoundItem(FoundItem foundItem) {
		this.foundItem = foundItem;
	}

	public LocalDateTime getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDateTime claimDate) {
		this.claimDate = claimDate;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}
    
    
}
