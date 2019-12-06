package com.sda.auction.model;

import java.util.*;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "item")
@Data
@EqualsAndHashCode(exclude = "user")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int startingPrice;
    @Column
    private Date startDate;
    @Column
    private Date endDate;

    @Column
    private String category;

    @Lob // pt ca e stringul prea lung
    @Column
    private String photo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<>();

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_role",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();

    public String getOwnersName() {
        return owner.getFriendlyName();
    }

    public Integer getCurrentPrice() {
        if (bids.isEmpty()) {
            return startingPrice;
        }
        Bid maxBid = Collections.max(bids);
        return maxBid.getPrice();
    }

    public boolean hasNoBids() {
        return bids.isEmpty();
    }

    public Integer getHighestBidOf(String userEmail) {
        //varianta clasica, pe care o stim:
		int maxBidValue = -1;
		for (Bid each : bids) {
			if (each.getUser().getEmail().compareTo(userEmail) == 0) {
				if (maxBidValue < each.getPrice()) {
					maxBidValue = each.getPrice();
				}
			}
		}
		return maxBidValue;

        // varianta ce foloseste java 8 si face acelasi lucru ca in codul de mai sus:
        /*Optional<Bid> maxBid = bids.stream()
                .filter(bid -> bid.getUserEmail().compareTo(userEmail) == 0)
                .max(Comparator.comparing(Bid::getPrice));
        return maxBid.isPresent() ? maxBid.get().getPrice() : -1;*/


    }
}