package edu.miu.waa.waaauctionsystem.services;

import edu.miu.waa.waaauctionsystem.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAll();
    public Optional<Product> getById(Long id);
    public List<Product> getAllByName(String name);
    public Product creatProduct(Product product);
    public void deleteProductById(Long id);
    public Product updateProduct(Long id, Product product);
    Page<Product> getProductByPage(int page, int pageSize);

}
