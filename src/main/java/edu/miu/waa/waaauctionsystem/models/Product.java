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
    //@Column(name = "productId")
    private Long id;
    private String name;
    private String description;
    private float bidStartPrice;
    private LocalDate bidDueDate;
    private LocalDate bidPaymentDueDate;
    private int bidCount=0;
    private boolean isRelease;
/*    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> categories;*/
    private String category;
    @ManyToOne
    @JoinTable(name="sellerProduct", joinColumns = {@JoinColumn(name ="productId")}, inverseJoinColumns = {@JoinColumn(name ="sellerId")})
    private User userSeller;
    public void increaseBidCount(){
        this.bidCount++;
    }
}
