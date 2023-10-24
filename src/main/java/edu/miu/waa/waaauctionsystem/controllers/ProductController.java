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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;
    private final ResponseHandler responseHandler;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id){
        Product product=productService.getById(id).orElse(null);
        if(null!=product){
            return responseHandler.response(product, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Not Found", HttpStatus.NOT_FOUND);
    }
    @GetMapping
    @CrossOrigin
    public ResponseEntity<Object> getProducts(@RequestParam int page, @RequestParam int pageSize){

        try{
            Page<Product> productPage= productService.getProductByPage(page, pageSize);
            return responseHandler.response(productPage, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductsByName(@PathVariable String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int pageSize){
        try{
            Page<Product> productPage= productService.getAllByName(name, page, pageSize);
            return responseHandler.response(productPage, "Success", HttpStatus.OK);
        }catch (Exception e){
            return responseHandler.response(null, ""+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    private ResponseEntity<Object> getObjectResponseEntity(Page<Product> productPage) {
        Map<String, Object> response=new HashMap<>();
        response.put("products", productPage.getContent());
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        return responseHandler.response(response, "Success", HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        Product createdProduct=productService.creatProduct(product);
        if (null!=createdProduct){
            return responseHandler.response(createdProduct, "Success", HttpStatus.OK);
        }
        return responseHandler.response(null, "Failed", HttpStatus.EXPECTATION_FAILED);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@RequestParam Long id){
        productService.deleteProductById(id);
        return responseHandler.response(null, "Success", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestParam Long id, @RequestParam Product product){
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
