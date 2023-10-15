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
    private Long id_producto;
    private String producto;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_marca")
    private Marca marca;
    private double precio_referencia;
    private double costo_referencia;
    private String descripcion;
}
