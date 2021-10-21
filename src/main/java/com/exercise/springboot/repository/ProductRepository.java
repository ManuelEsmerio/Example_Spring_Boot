package com.exercise.springboot.repository;

import com.exercise.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findByIdCategoryOrderByIdDesc(int idCategory);

    Optional<List<Product>> findByStockGreaterThanEqual(int quantity);

    @Query(
            value = "SELECT * FROM productos GROUP BY id_categoria, codigo_barras, id_producto",
            nativeQuery = true
    )
    Optional<List<Product>> usingGroupBy();

}
