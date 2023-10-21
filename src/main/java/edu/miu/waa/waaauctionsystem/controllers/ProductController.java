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
    @GetMapping(path = "product")
    public ResponseEntity<Object> getProduct(@RequestParam Long id){
        Product product=productService.getById(id).orElse(null);
        if(null!=product){
            return responseHandler.response(product, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam int page, @RequestParam int pageSize){
        Page<Product> productPage=productService.getProductByPage(page, pageSize);
        if(null!=productPage){
            return responseHandler.response(productPage, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        Product createdProduct=productService.creatProduct(product);
        if (null!=createdProduct){
            return responseHandler.response(createdProduct, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Failed", HttpStatus.EXPECTATION_FAILED);
    }
    @DeleteMapping
    public void deleteProduct(@RequestParam Long id){
        productService.deleteProductById(id);
    }
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestParam Long id, @RequestParam Product product){
        Product updatedProduct= productService.updateProduct(id, product);
        if (null!= updatedProduct){
            return responseHandler.response(updatedProduct, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Failed", HttpStatus.NOT_MODIFIED);
    }
}
