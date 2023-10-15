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
    private long id_tienda;
    private long id_empleado;
    private long id_cliente;
    private Date fecha;
    private long id_venta_status;
}