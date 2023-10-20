package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Role> roles;
    @OneToMany(mappedBy = "userSeller")
    private List<Product> productsCreated;
    @ManyToMany(mappedBy = "userCustomer")
    private List<Product> productsBid;
}
