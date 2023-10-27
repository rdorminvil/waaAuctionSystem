package edu.miu.waa.waaauctionsystem.services.servicesImplementations;

import edu.miu.waa.waaauctionsystem.models.Product;
import edu.miu.waa.waaauctionsystem.repositories.ProductRepository;
import edu.miu.waa.waaauctionsystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Product getById(Long id) throws Exception {
        if (null!=productRepository.getProduct(id)){
            return productRepository.getProduct(id);
        }else {
            throw new Exception("Product not found");
        }
    }

    @Override
    public Page<Product> getAllByName(String name, Long id, int page, int pageSize) {
        Pageable pageable=PageRequest.of(page, pageSize);
        return productRepository.findProdByNameAndUser(name, id, pageable);
    }

    @Override
    public Product creatProduct(Product product) throws Exception {
        if (product.getBidPaymentDueDate().isAfter(LocalDate.now())) {
            return productRepository.save(product);
        }else {
            throw new Exception("Bid Payment Due Date should be greater the creation Date of the product");
        }
    }

    @Override
    public void deleteProductById(Long id) {
         productRepository.deleteById(id);
    }
    public Product updateProductWhenCreateBid(Long id, Product product) {
        Optional<Product> updatedProduct= productRepository.findById(id);
        updatedProduct.ifPresent(prod->{
                        prod.setName(product.getName());
                        prod.setDescription(product.getDescription());
                        prod.setBidDueDate(product.getBidDueDate());
                        prod.setBidPaymentDueDate(product.getBidPaymentDueDate());
                        prod.setBidStartPrice(product.getBidStartPrice());
                        prod.setProdRelease(product.isProdRelease());
                        prod.setCategory(product.getCategory());
                        prod.setUserSeller(product.getUserSeller());
                        prod.setBidCount(product.getBidCount());
                        prod.setDepositAmount(product.getDepositAmount());
                        productRepository.save(prod);
                }
        );
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> updatedProduct= productRepository.findById(id);
         updatedProduct.ifPresent(prod->{
                if (!prod.isProdRelease()) {
                    prod.setName(product.getName());
                    prod.setDescription(product.getDescription());
                    prod.setBidDueDate(product.getBidDueDate());
                    prod.setBidPaymentDueDate(product.getBidPaymentDueDate());
                    prod.setBidStartPrice(product.getBidStartPrice());
                    prod.setProdRelease(product.isProdRelease());
                    prod.setCategory(product.getCategory());
                    prod.setUserSeller(product.getUserSeller());
                    prod.setBidCount(product.getBidCount());
                    prod.setDepositAmount(product.getDepositAmount());
                    productRepository.save(prod);
                }else {
                    throw new RuntimeException("Product has been release");
                }
            }
        );
         return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> getProductByPage(Long userId, int page, int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return productRepository.findAllProductsByIsReleasedTrue(userId, pageable);
    }

    @Override
    public List<Product> getProductByRelease(Long id, boolean release) {
        return productRepository.findProductsByRelease(id, release);
    }
}
