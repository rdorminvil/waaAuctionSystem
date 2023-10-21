package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
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
    public List<Product> getAllByName(String name) {
        return productRepository.findAllByName(name);
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
                prod.setName(product.getName());
                prod.setDescription(product.getDescription());
                prod.setDeposit(product.getDeposit());
                prod.setBidDueDate(product.getBidDueDate());
                prod.setBidPaymentDueDate(product.getBidPaymentDueDate());
                prod.setStartPrice(product.getStartPrice());
                productRepository.save(prod);
            }
        );
         return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> getProductByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
