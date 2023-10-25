package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isRelease=?1")
    List<Product> findProductsByRelease(boolean release);

    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isRelease=true and p.bidDueDate > current_date")
    Page<Product> findAllProductsByIsReleased(Pageable pageable);
}
