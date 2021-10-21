package com.exercise.springboot.controller;

import com.exercise.springboot.entity.Product;
import com.exercise.springboot.service.ProductService;
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
    public String Greeting(){
        return "Product Controller Working";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll().get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") int idProduct){
        Optional<Product> findProduct = productService.findById(idProduct);
        if (!findProduct.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(findProduct, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int idProduct){
        return new ResponseEntity( (productService.delete(idProduct)) ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }
}
