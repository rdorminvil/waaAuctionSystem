package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public Page<User> findAll(Pageable pageable);
    public Optional<User> findByEmail(String email);
    @Query("select u from  User u where u.email=?1")
    public User findAllByEmail(String email);

}
