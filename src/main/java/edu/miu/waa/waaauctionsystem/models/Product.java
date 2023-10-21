package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;
    private String name;
    private String description;
    private float startPrice;
    private float deposit;
    private LocalDate bidDueDate;
    private LocalDate bidPaymentDueDate;
    private SaveMode saveMode;
    @OneToMany
    private List<Category> categories;
    @ManyToOne
    private User user;
}
