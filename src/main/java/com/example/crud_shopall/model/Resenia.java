package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "resenia")
public class Resenia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resenia")
    private long id_resenia;

    @Column(name = "resenia")
    private String resenia;

    @Column(name = "valoracion")
    private long valoracion;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    Cliente cliente;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_producto")
    Producto producto;

    @OneToMany(targetEntity = VentaDetalle.class, fetch = FetchType.LAZY, mappedBy = "resenia")
    @JsonIgnore
    private List<VentaDetalle> venta_detalle;
}
