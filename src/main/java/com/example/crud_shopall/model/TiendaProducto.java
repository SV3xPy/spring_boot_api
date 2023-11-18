package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tienda_producto")
public class TiendaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tienda_producto;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    //@JsonProperty("producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    //@JsonProperty("tienda")
    private Tienda tienda;
}
