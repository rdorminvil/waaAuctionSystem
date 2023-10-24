package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.BidProduct;
import org.springframework.data.domain.Page;

public interface BidProductService {
    public BidProduct createBidProduct(String user, Long productId, float deposit);
    public BidProduct updateBidProduct(Long id, BidProduct bidProduct);
    public Page<BidProduct> findBidProductByUserId(Long id);
    public Page<BidProduct> findBidProductByUserIdAndProductId(Long userId, Long productId);

    public BidProduct deleteBidProduct();
    public Page<BidProduct> getAllBidProduct();
    public Page<BidProduct> getAllBidProductByCustomer();
    public Page<BidProduct> getBidsByUserId(Long id, int page, int pageSize);
    public Page<BidProduct> getBidsByProductId(Long id, int page, int pageSize);
}
