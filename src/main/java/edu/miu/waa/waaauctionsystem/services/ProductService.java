package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
    public Optional<Product> getById(Long id);
    public Page<Product> getAllByName(String name, int page, int pageSize);
    public Product creatProduct(Product product);
    public void deleteProductById(Long id);
    public Product updateProduct(Long id, Product product);
    public Page<Product> getProductByPage(int page, int pageSize);

    public List<Product> getProductByRelease(boolean release);

}
