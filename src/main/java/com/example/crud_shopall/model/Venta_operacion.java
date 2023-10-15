package com.example.crud_shopall.model;

import jakarta.persistence.*;
        import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "venta_operacion")
public class Venta_operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_venta_operacion;
    private long id_venta;
    private long id_tipo_pago;
    private String folio;
    private float monto;
    private byte autorizado;
}