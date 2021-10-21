package com.exercise.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false, unique = true)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private  String name;

    @Column(name = "id_categoria", nullable = false)
    private Integer idCategory;

    @Column(name = "codigo_barras", length = 150)
    private String barcode;

    @Column(name = "precio_venta")
    private Double price;

    @Column(name = "cantidad_stock")
    private Integer stock;

    @Column(name = "estado")
    private Boolean active;
}
