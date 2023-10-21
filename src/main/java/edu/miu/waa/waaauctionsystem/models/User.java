package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
//    @Column(name="firstname")
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    private List<Role> roles;
    @OneToMany(mappedBy = "userSeller")
    private List<Product> productsCreated;
    @ManyToMany(mappedBy = "userCustomer")
    private List<Product> productsBid;
    public Role addRole(Role role){
        this.roles.add(role);
        return role;
    }
}
