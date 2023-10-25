package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
/*    @JoinTable(name = "bidproductUser", joinColumns = {@JoinColumn(name = "bidproductid")},
            inverseJoinColumns = {@JoinColumn(name = "userid", referencedColumnName = "id")})*/
    private User user;
    @OneToOne
    private Product product;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids=new ArrayList<>();;
    public BidProduct(User user, Product product, Bid bid) {
        this.user=user;
        this.product=product;
        this.isCompleted=false;
        this.bids.add(bid);
    }
    public void addBid(Bid bid){
        this.bids.add(bid);
    }
}
