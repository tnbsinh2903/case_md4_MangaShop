package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.utils.ValidDateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    @NotBlank(message = "The Name not Empty")
    @Size(min = 3, message = "Name must greater than 8 characters")
    private String name;

    @NotBlank(message = "The Image not Empty")
    @Size(max = 1600, message = "Image must less than 1000 characters")
    private String image;

    private BigDecimal price;

    private int quantity;

    private CategoryDTO category;

    public ProductDTO(Long id, String name, String image, BigDecimal price, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category.toCategoryDTO();
    }

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setName(name)
                .setImage(image)
                .setPrice(price)
                .setQuantity(quantity)
                .setCategory(category.toCategory());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO productDTO = (ProductDTO) o;
        String price = productDTO.getPrice().toString();
        String quantity = String.valueOf(productDTO.getQuantity());


        if (!ValidDateUtils.isNumberValid(price)) {

        if (price == null || price.equals("")) {
            errors.rejectValue("price", "400", "Gi?? kh??ng ???????c ????? tr???ng!");
        }

        if (quantity.equals("")) {
            errors.rejectValue("price", "400", "S??? l?????ng  ???????c ????? tr???ng!");
        }
        assert price != null;
        if (price.length() > 10) {
            errors.rejectValue("price", "400", "Gi?? kh??ng ???????c ??m !");
        }
        long validPrice = Long.parseLong(price);
        if (validPrice < 0) {
            errors.rejectValue("price", "400", "Gi?? s???n ph???m kh??ng ???????c ??m!");
        }

//            else {
//                errors.rejectValue("price", "400", "Vui l??ng nh???p gi?? h???p l???!");
//            }

//        } else {
//        if (price.length() > 10) {
//            errors.rejectValue("price", "400", "Gi?? t???i ??a c???a m???t s???n ph???m l?? 1.000.000.000??!");
//        } else {

//            long validPrice = Long.parseLong(price);
//            if (validPrice < 0) {
//                errors.rejectValue("price", "400", "Gi?? s???n ph???m kh??ng ???????c ??m!");
//            }

//            if (validPrice > 1000000000) {
//                errors.rejectValue("price", "400", "Gi?? t???i ??a c???a m???t s???n ph???m l?? 1.000.000.000??!");
//            }
//            }
        }
    }
}
