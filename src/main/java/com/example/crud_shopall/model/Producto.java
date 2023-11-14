package com.example.crud_shopall.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Producto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String producto;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_marca")
    private Marca marca;
    private double precio_referencia;
    private double costo_referencia;
    private String descripcion;

    @JsonIgnore
    @OneToMany(targetEntity = ProductoCategoria.class, fetch = FetchType.LAZY, mappedBy = "producto", cascade = CascadeType.REMOVE)
    private List<ProductoCategoria> productoCategoria;
}
