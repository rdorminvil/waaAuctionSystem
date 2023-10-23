package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class BidProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCompleted;
    @ManyToOne
    private User user;
    @OneToOne
    private Product product;
    public BidProduct(Long userId, Long productId) {
    }
}
