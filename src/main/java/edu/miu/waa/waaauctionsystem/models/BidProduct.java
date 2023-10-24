package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class BidProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCompleted;
    @ManyToOne
    @JoinTable(name = "bidproductUser")
    private User user;
    @OneToOne
    private Product product;
    @OneToMany(mappedBy = "bidProduct", cascade = CascadeType.PERSIST)
    private List<Bid> bids;
    public BidProduct(User user, Product product) {
    }
    public void addBid(Bid bid){
        this.bids.add(bid);
    }
}
