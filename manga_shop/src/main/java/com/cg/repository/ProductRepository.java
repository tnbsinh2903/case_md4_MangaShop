package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.cg.model.dto.CategoryDTO(" +
            "c.id," +
            "c.name) " +
            "FROM Category AS c")
    List<CategoryDTO> getAllCategoryDTO();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(" +
            "p.id ," +
            "p.name, " +
            "p.image, " +
            "p.price," +
            "p.quantity," +
            "p.category) " +
            "FROM Product AS p WHERE p.deleted = false")
    List<ProductDTO> findAllProductDTO();

    List<Product> findAllByDeletedIsFalse();

    @Query("SELECT NEW com.cg.model.dto.ProductDTO(" +
            "p.id, " +
            "p.name, " +
            "p.image, " +
            "p.price, " +
            "p.quantity," +
            "p.category) " +
            "FROM Product AS p WHERE p.id = :id")
    Optional<ProductDTO> findProductDTOById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Product AS p SET p.deleted = true WHERE p.id = :id")
    void deleteProductById(@Param("id") Long id);


    @Modifying
    @Query("SELECT NEW com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.name, " +
            "p.image, " +
            "p.price, " +
            "p.quantity," +
            "p.category) " +
            "FROM Product AS p WHERE p.name LIKE %?1% " )
    List<ProductDTO> searchProductByName(String search);
}
