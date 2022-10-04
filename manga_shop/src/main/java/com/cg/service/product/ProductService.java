package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public List<Product> findProductAction() {
        return productRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return productRepository.findProductDTOById(id);
    }

    @Override
    public List<ProductDTO> searchProductByName(String search) {
      return  productRepository.searchProductByName(search);
    }

    @Override
    public List<CategoryDTO> getAllCategoryDTO() {
        return  productRepository.getAllCategoryDTO();
    }
}
