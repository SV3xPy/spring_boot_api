package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "venta_detalle")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_detalle")
    private long id_venta_detalle;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_venta")
    Venta venta;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_producto")
    Producto producto;

    @Column(name = "cantidad")
    private double cantidad;

    @Column(name = "porcentaje_descuento")
    private double porcentaje_descuento;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_resenia")
    Resenia resenia;

    @Column(name = "fecha_envio")
    private Date fecha_envio;

    @Column(name = "fecha_entrega")
    private Date fecha_entrega;

}
