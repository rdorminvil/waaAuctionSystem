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

    @Query("SELECT p FROM Product p join p.userSeller u WHERE u.id=?1 and p.isRelease=?2")
    List<Product> findProductsByRelease(Long id, boolean release);

    @Query("SELECT p FROM Product p JOIN p.userSeller pu where LOWER(p.name) = LOWER(:name) and pu.id!=:seller_Id")
    Page<Product> findProdByNameAndUser( String name, Long seller_Id, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.isRelease=true")
    Page<Product> findAllProductsByIsReleased(Pageable pageable);
}
