package edu.miu.waa.waaauctionsystem.services.servicesImplementations;

import edu.miu.waa.waaauctionsystem.models.Bid;
import edu.miu.waa.waaauctionsystem.models.BidProduct;
import edu.miu.waa.waaauctionsystem.repositories.BidProductRepository;
import edu.miu.waa.waaauctionsystem.services.BidProductService;
import edu.miu.waa.waaauctionsystem.services.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class BidProductServiceImpl implements BidProductService {
    private final BidService  bidService;
    private final BidProductRepository bidProductRepository;

    @Override
    public BidProduct createBidProduct(Long userId, Long productId, float deposit) {
/*        List<BidProduct> bidProducts=bidProductRepository.findAllByUser_Id(userId).getContent();
        BidProduct bidProduct=null;
        if(!bidProducts.isEmpty() ) {
            for (BidProduct elem : bidProducts
            ) {
                if (elem.getProduct().getId().equals(productId)) {
                    bidProduct = elem;
                    break;
                }
            }
        }
        if(null!=bidProduct && !bidProduct.isCompleted()){

        }
*//*        bidProduct.forEach(elem-> {
            if (Objects.equals(elem.getProduct().getId(), productId))
                isFound=true;
        });*//*
        if(!isFound){
            bidProduct=bidProductRepository.save(new BidProduct(userId, productId));
        }
        Bid bid=new Bid();
        bid.setBidProducts( List.of(bidProduct);
        bidService.createBid(bidProduct, deposit, LocalDate.now())*/

        return null;
    }

    @Override
    public BidProduct updateBidProduct() {
        return null;
    }

    @Override
    public BidProduct deleteBidProduct() {
        return null;
    }

    @Override
    public Page<BidProduct> getAllBidProduct() {
        return null;
    }

    @Override
    public Page<BidProduct> getAllBidProductByCustomer() {
        return null;
    }

    @Override
    public Page<BidProduct> getBidsByUserId(Long id, int page, int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return bidProductRepository.findAllByUser_Id(id, pageable);
    }

    @Override
    public Page<BidProduct> getBidsByProductId(Long id, int page, int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return bidProductRepository.findAllByProduct_Id(id, pageable);
    }
}
