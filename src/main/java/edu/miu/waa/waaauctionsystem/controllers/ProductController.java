package edu.miu.waa.waaauctionsystem.controllers;

import edu.miu.waa.waaauctionsystem.libs.ResponseHandler;
import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ResponseHandler responseHandler;
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id){
        Product product=productService.getById(id).orElse(null);
        if(null!=product){
            return responseHandler.response(product, "Succeed", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam int page, @RequestParam int pageSize){
        Pageable pageable= PageRequest.of(page, pageSize);
        Page<Product> productPage=productService.getProductByPage(pageable);
        if(null!=productPage){
            return responseHandler.response(productPage, "Succeed", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        Product createdProduct=productService.creatProduct(product);
        if (null!=createdProduct){
            return responseHandler.response(createdProduct, "Succeed", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@RequestParam Long id){
        
    }
}
