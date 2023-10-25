package edu.miu.waa.waaauctionsystem.controllers;

import edu.miu.waa.waaauctionsystem.libs.ResponseHandler;
import edu.miu.waa.waaauctionsystem.models.BidConvertorObject;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.services.BidProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bidproduct")
@RequiredArgsConstructor
public class BidProductController {
    private final BidProductService bidProductService;
    private final ResponseHandler responseHandler;

    @PostMapping
    public ResponseEntity<Object> createBidProduct(@RequestBody BidConvertorObject bidConvertorObject) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user= (User) auth.getPrincipal();
        try {
            return responseHandler.response(bidProductService.createBidProduct(user.getEmail(), bidConvertorObject.getProductId(),
                            bidConvertorObject.getDeposit()), "Success", HttpStatus.OK);

        } catch (Exception e) {
            return responseHandler.response( null, "Failed :"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping()
    public ResponseEntity<Object> findBidProductByUserId(int page, int pageSize){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user= (User) auth.getPrincipal();
        try {
            return responseHandler.response(bidProductService.findBidProductByUserId(user.getId(), page, pageSize), "Success", HttpStatus.OK);

        } catch (Exception e) {
            return responseHandler.response( null, "Failed :"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> findBidProductByUserIdByProduct(@PathVariable Long id , @RequestParam int page, @RequestParam int pageSize){

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        User user= (User) auth.getPrincipal();
        try {
            return responseHandler.response(bidProductService.findBidProductByUserIdAndProductId(user.getId(), id, page, pageSize), "Success", HttpStatus.OK);

        } catch (Exception e) {
            return responseHandler.response( null, "Failed :"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
