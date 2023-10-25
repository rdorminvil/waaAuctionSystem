package edu.miu.waa.waaauctionsystem.repositories;

import edu.miu.waa.waaauctionsystem.models.BidProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidProductRepository extends JpaRepository<BidProduct, Long> {
    public Page<BidProduct> findAllByProduct_Id(Long id, Pageable pageable);
    public Page<BidProduct> findAllByUser_Id(Long id, Pageable pageable);
    public List<BidProduct> findAllByUser_Id(Long id);
    public Page<BidProduct> findBidProductByUserId(Long id, Pageable pageable);
    public Page<BidProduct> findBidProductByUserIdAndProductId(Long userId, Long productId, Pageable pageable);

/*    @Query("select bi from BidProduct bp join Bid bi where bp.product.id=?1 and bp.user=?2")
    public Page<BidProduct> getBidByProductId(Long productId, Long userId);
    @Query("select ")*/
}
