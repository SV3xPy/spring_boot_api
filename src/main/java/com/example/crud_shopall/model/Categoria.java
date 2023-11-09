package com.example.crud_shopall.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;
    private String categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private Set<ProductoCategoria> productoCategorias = new HashSet<>();
}
