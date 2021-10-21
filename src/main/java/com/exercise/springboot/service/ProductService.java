package com.exercise.springboot.service;

import com.exercise.springboot.entity.Product;
import com.exercise.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<List<Product>> getAll(){
        return Optional.of(productRepository.findAll());
    }

    public Optional<Product> findById(int idProduct){
        return productRepository.findById(idProduct);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int idProduct){
        return findById(idProduct).map(product -> {
            // productRepository.deleteById(product.getId());
            productRepository.deleteById(idProduct);
            return true;
        }).orElse(false);
    }
}
