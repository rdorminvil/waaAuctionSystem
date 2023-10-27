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

    @Query("SELECT p FROM Product p WHERE p.userSeller.id=?1 and p.prodRelease=?2")
    List<Product> findProductsByRelease(Long id, boolean release);

    @Query("SELECT p FROM Product p WHERE p.name=?1 and p.userSeller.id!=?2")
    Page<Product> findProdByNameAndUser( String name, Long seller_Id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.prodRelease=true")
    Page<Product> findAllProductsByIsReleasedTrue(Pageable pageable);
    @Query("SELECT p FROM Product p")
    List<Product> findAllProductsByIsReleasedTr();
}
