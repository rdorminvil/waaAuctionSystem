package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "bidId")
    private Long id;
    private float Deposit;
    private LocalDate biddingDate;
    @OneToMany
    @JoinColumn(name = "bidId")
    private List<BidProduct> bidProducts;
}
