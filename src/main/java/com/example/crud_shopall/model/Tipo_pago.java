package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "tipo_pago")
public class Tipo_pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tipo_pago;
    private String tipo_pago;
}