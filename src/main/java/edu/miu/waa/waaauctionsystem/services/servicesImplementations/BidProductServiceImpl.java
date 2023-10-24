package edu.miu.waa.waaauctionsystem.services.servicesImplementations;

import edu.miu.waa.waaauctionsystem.models.Bid;
import edu.miu.waa.waaauctionsystem.models.BidProduct;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.repositories.BidProductRepository;
import edu.miu.waa.waaauctionsystem.services.BidProductService;
import edu.miu.waa.waaauctionsystem.services.BidService;
import edu.miu.waa.waaauctionsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class BidProductServiceImpl implements BidProductService {
    private final BidService  bidService;
    private final BidProductRepository bidProductRepository;
    private final UserService userService;

    public List<BidProduct> getBidProductByEmail(String email){
        User user=userService.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email+" Not Found"));
        Long userId;
        if (null!=user){
            userId=user.getId();
            return bidProductRepository.findAllByUser_Id(userId);
        }
        return null;
    }
    @Override
    public BidProduct createBidProduct(String email, Long productId, float deposit) throws Exception {
        List<BidProduct> bidProducts=getBidProductByEmail(email);
        BidProduct bidProduct=null;
        if (!bidProducts.isEmpty()){
            for (BidProduct elem:bidProducts
                 ) {
                if (elem.getProduct().getId().equals(productId)){
                    bidProduct=elem;
                    break;
                }
            }
            if (null!=bidProduct){
                if (!bidProduct.isCompleted()) {
                    Bid bid = new Bid(deposit, LocalDate.now(), bidProduct);
                    bidProduct.addBid(bid);
                    updateBidProduct(bidProduct.getId(),bidProduct);
                    bidService.createBid(bid);
                }
                else {
                    throw new Exception("Bid has been completed for this product");
                }
            }else {
                System.out.println();
            }
        }

/*      List<BidProduct> bidProducts=bidProductRepository.findAllByUser_Id(userId).getContent();
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
*//**//*        bidProduct.forEach(elem-> {
            if (Objects.equals(elem.getProduct().getId(), productId))
                isFound=true;
        });*//**//*
        if(!isFound){
            bidProduct=bidProductRepository.save(new BidProduct(userId, productId));
        }
        Bid bid=new Bid();
        bid.setBidProducts( List.of(bidProduct);
        bidService.createBid(bidProduct, deposit, LocalDate.now())*//*
*/
        return null;
    }

    @Override
    public BidProduct updateBidProduct(Long id, BidProduct bidProduct) {
        Optional<BidProduct> updateBidProduct= bidProductRepository.findById(id);
        updateBidProduct.ifPresent(uBP->{
            uBP.setProduct(bidProduct.getProduct());
            uBP.setUser(bidProduct.getUser());
            uBP.setCompleted(bidProduct.isCompleted());
            uBP.setBids(bidProduct.getBids());
            bidProductRepository.save(uBP);
        });
        return bidProductRepository.findById(id).orElse(null);
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
