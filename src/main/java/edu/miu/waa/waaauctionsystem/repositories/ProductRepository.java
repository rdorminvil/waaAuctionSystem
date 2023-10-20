package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAll();
    public Optional<Product> findById(Long id);
    public List<Product> findAllByName(String name);
    public void deleteById(Long id);

}
