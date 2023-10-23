package edu.miu.waa.waaauctionsystem.services.servicesImplementations;

import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.repositories.ProductRepository;
import edu.miu.waa.waaauctionsystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> getAllByName(String name, int page, int pageSize) {
        Pageable pageable=PageRequest.of(page, pageSize);
        System.out.println("this is output"+productRepository.findAllByName(name, pageable));
        return productRepository.findAllByName(name, pageable);
    }

    @Override
    public Product creatProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> updatedProduct= productRepository.findById(id);
         updatedProduct.ifPresent(prod->{
                if (!prod.getIsRelease()) {
                    prod.setName(product.getName());
                    prod.setDescription(product.getDescription());
                    prod.setBidDueDate(product.getBidDueDate());
                    prod.setBidPaymentDueDate(product.getBidPaymentDueDate());
                    prod.setBidStartPrice(product.getBidStartPrice());
                    prod.setIsRelease(product.getIsRelease());
                    productRepository.save(prod);
                }else {
                    throw new RuntimeException("Product has been release");
                }
            }
        );
         return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> getProductByPage(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable);
    }
}