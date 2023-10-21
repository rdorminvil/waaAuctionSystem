package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidId")
    private Long id;
    private float biddingPrice;
    private LocalDate biddingDate;
    @ManyToOne
    private User user;
    @OneToOne
    private Product product;
}
