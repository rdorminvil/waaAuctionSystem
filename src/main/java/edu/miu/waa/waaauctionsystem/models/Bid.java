package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "bidId")
    private Long id;
    private float deposit;
    private LocalDateTime biddingDate;
/*    @ManyToOne
    private BidProduct bidProduct;*/

    public Bid(float deposit, LocalDateTime now) {
        this.deposit=deposit;
        this.biddingDate=now;
        //this.bidProduct=bidProduct;
    }
}
