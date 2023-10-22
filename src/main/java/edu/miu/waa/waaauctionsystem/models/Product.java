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
    private float bidStartPrice;
    private float deposit;
    private LocalDate bidDueDate;
    private LocalDate bidPaymentDueDate;
    private Boolean releaseMode;
    @OneToMany
    private List<Category> categories;
    @ManyToOne
    //@JoinTable(name="sellerProduct", joinColumns = {@JoinColumn(name ="productId")}, inverseJoinColumns = {@})
    private User user;
}
