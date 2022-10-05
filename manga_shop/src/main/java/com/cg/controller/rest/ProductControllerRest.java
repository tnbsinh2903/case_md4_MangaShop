package com.cg.controller.rest;


import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.product.IProductService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

 import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductControllerRest {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> listProduct() {
//        List<ProductDTO> productDTOS =new ArrayList<>();
        List<ProductDTO> productList = productService.findAllProduct();

//        for (Product product : productList){
//            Category category = product.getCategory();
//            CategoryDTO categoryDTO =category.toCategoryDTO();
//            ProductDTO productDTO = product.toProductDTO();  //categoryDTO.toCategory()
//            productDTOS.add(productDTO);
//        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id ) {

        Optional<ProductDTO> productDTOOptional = productService.findProductById(id);

        if (!productDTOOptional.isPresent()) {
            return new ResponseEntity<>("id này ko tồn tại !", HttpStatus.NOT_FOUND);
        }


        Product product = productDTOOptional.get().toProduct();

        ProductDTO productDTO = product.toProductDTO();

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<?> createProduct (@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult){

        new ProductDTO().validate(productDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return appUtils.errors(bindingResult);
        }

        Optional<ProductDTO> productDTOOptional = productService.findProductById(productDTO.getId());

        if (!productDTOOptional.isPresent()) {
            return new ResponseEntity<>("id này ko tồn tại !", HttpStatus.NOT_FOUND);
        }
         try {
            Product product = productDTO.toProduct();
//            product.setId(0L);
            product.setDeleted(false);
            productService.save(product);

            return new ResponseEntity<>(product.toProductDTO(), HttpStatus.CREATED);
        }catch(Exception e){
           return new ResponseEntity<>("error", HttpStatus.CREATED);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO){

        Optional<ProductDTO> productDTOOptional = productService.findProductById(productDTO.getId());

        productDTO.setId(productDTOOptional.get().getId());

        Product product = productDTO.toProduct();
        product.setDeleted(false);
        productService.save(product);

        return new ResponseEntity<>(product.toProductDTO(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
         try {
//             Optional<ProductDTO> productDTOOptional = productService.findProductById(productDTO.getId());
             productService.remove(id);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete Error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search/{name}")
    public ResponseEntity<?> searchProduct(@PathVariable String name){
               try {
                   List<ProductDTO> productDTOList = productService.searchProductByName(name);
                   return new ResponseEntity<>( productDTOList , HttpStatus.OK);
               }catch (Exception e){
                   return new ResponseEntity<>( "loi", HttpStatus.BAD_REQUEST);
               }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategoryDTO() {
        List<CategoryDTO> categoryDTOList = productService.getAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

}
