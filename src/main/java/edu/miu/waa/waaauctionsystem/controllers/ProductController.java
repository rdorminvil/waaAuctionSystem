package edu.miu.waa.waaauctionsystem.controllers;

import edu.miu.waa.waaauctionsystem.libs.ResponseHandler;
import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.models.User;
import edu.miu.waa.waaauctionsystem.models.authentication.AuthenticationUserInfo;
import edu.miu.waa.waaauctionsystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;
    private final ResponseHandler responseHandler;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id){
        try{
            return responseHandler.response(productService.getById(id), "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam int page, @RequestParam int pageSize){
        try{
            Page<Product> productPage= productService.getProductByPage(AuthenticationUserInfo.getUser().getId(), page, pageSize);
            return responseHandler.response(productPage, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/released")
    public ResponseEntity<Object> getReleasedProducts(@RequestParam boolean status){
        try{
            List<Product> products= productService.getProductByRelease(AuthenticationUserInfo.getUser().getId(), status);
            System.out.println(AuthenticationUserInfo.getUser().getId());
            return responseHandler.response(products, "Success", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<Object> getProductsByName(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int pageSize){
        try{
            Page<Product> productPage= productService.getAllByName(name, AuthenticationUserInfo.getUser().getId(), page, pageSize);
            return responseHandler.response(productPage, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        product.setUserSeller(AuthenticationUserInfo.getUser());
        try{
            Product createdProduct=productService.creatProduct(product);
            return responseHandler.response(createdProduct, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return responseHandler.response(null, "Success", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product){
        try{
            Product updatedProduct= productService.updateProduct(id, product);
            if (null!= updatedProduct){
                return responseHandler.response(updatedProduct, "Success", HttpStatus.OK);
            }
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.NOT_MODIFIED);
        }
        return responseHandler.response(null, "Failed", HttpStatus.NOT_MODIFIED);
    }
}
