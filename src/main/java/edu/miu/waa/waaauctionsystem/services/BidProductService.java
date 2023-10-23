package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.models.BidProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BidProductService {
    public BidProduct createBidProduct(Long userId, Long productId, float deposit);
    public BidProduct updateBidProduct();
    public BidProduct deleteBidProduct();
    public Page<BidProduct> getAllBidProduct();
    public Page<BidProduct> getAllBidProductByCustomer();
    public Page<BidProduct> getBidsByUserId(Long id, int page, int pageSize);
    public Page<BidProduct> getBidsByProductId(Long id, int page, int pageSize);
}
