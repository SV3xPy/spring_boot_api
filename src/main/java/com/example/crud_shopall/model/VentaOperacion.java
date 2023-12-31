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

public class VentaOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_venta_operacion;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pago")
    private TipoPago tipo_pago;

    private String folio;
    private float monto;
    //quiza es mejor cambiarlo a boolean
    private byte autorizado;
}
