package com.example.crud_shopall.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "producto_categoria")
public class ProductoCategoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto_categoria;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    //@JsonProperty("producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    //@JsonProperty("categoria")
    private Categoria categoria;
}
