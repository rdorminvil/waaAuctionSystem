package edu.miu.waa.waaauctionsystem.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "userId")
    private Long id;
    private String name;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "userId"))
    private List<Role> roles;
    @OneToMany(mappedBy = "userSeller")
    private List<Product> products;
    @OneToMany(mappedBy = "user")
    private List<BidProduct> bidProducts;
    private float accountBalance=10000;
    public Role addRole(Role role){
        this.roles.add(role);
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
