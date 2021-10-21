package com.exercise.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Integer id;

    @Column(name = "descripcion", length = 45, nullable = false)
    private String description;

    @Column(name = "estado", nullable = false)
    private Boolean active;
}
