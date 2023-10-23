package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.BidProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidProductRepository extends JpaRepository<BidProduct, Long> {
    public Page<BidProduct> findAllByProduct_Id(Long id, Pageable pageable);
    public Page<BidProduct> findAllByUser_Id(Long id, Pageable pageable);
}
