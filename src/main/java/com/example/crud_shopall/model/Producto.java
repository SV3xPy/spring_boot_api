package com.example.crud_shopall.model;
import jakarta.persistence.*;
import lombok.*;

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
    private long id_producto;
    private String producto;
    private long id_marca;
    private double precio_referencia;
    private double costo_referencia;
    private String descripcion;
}
