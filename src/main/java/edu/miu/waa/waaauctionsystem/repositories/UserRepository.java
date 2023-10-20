package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
