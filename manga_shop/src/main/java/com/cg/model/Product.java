package com.cg.model;

import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private BigDecimal price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public ProductDTO toProductDTO( ){
    return new ProductDTO()
            .setId(id)
            .setName(name)
            .setImage(image)
            .setPrice(price)
            .setQuantity(quantity)
            .setCategory(category.toCategoryDTO());
            }
}
