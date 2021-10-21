package com.exercise.springboot.controller;

import com.exercise.springboot.entity.Product;
import com.exercise.springboot.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/greeting")
    @ApiOperation("Just greeting")
    public String Greeting(){
        return "Product Controller Working";
    }

    @GetMapping("/all")
    @ApiOperation("Get All Products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll().get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Product by it unique key")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No product found")
    })
    public ResponseEntity<Product> findById(@PathVariable("id") int idProduct){
        return productService.findById(idProduct).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int idProduct){
        return new ResponseEntity( (productService.delete(idProduct)) ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Find Product By IdCategory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No products found")
    })
    public ResponseEntity<List<Product>> findByIdCategory(@PathVariable("id") int idCategory){
        return  productService.findByIdCategory(idCategory).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/quantity/greaterOrEquals/{quantity}")
    public ResponseEntity<List<Product>> findByStockGreaterAndEquals(@PathVariable("quantity") int quantity){
        return new ResponseEntity<>(productService.findByStockGreaterAndEquals(quantity).get(), HttpStatus.OK);
    }

    @GetMapping("/groupby")
    public ResponseEntity<List<Product>> usingGroupBy(){
        return new ResponseEntity<>(productService.usingGroupBy().get(), HttpStatus.OK);
    }
}
