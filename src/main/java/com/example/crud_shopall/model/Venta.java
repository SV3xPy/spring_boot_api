package com.example.crud_shopall.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_venta;

    @ManyToOne
    @JoinColumn(name = "id_tienda")
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_venta_status")
    private VentaStatus venta_status;
}
