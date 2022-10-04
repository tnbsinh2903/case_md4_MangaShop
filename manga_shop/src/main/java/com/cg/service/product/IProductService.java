package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    List<ProductDTO> findAllProduct();

    Optional<ProductDTO> findProductById(Long id);

    List<Product> findProductAction();

    List<ProductDTO> searchProductByName(String search);

    List<CategoryDTO> getAllCategoryDTO();

}


