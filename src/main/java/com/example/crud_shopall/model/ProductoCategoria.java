package com.example.crud_shopall.model;
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

    private Long id_producto;
    private Long id_categoria;
}
