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
@Table(name = "venta_status")
public class VentaStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_status")
    private long id_venta_status;

    @Column(name = "venta_status")
    private String venta_status;

    @OneToMany(targetEntity = Venta.class, fetch = FetchType.LAZY, mappedBy = "venta_status")
    @JsonIgnore
    private List<Venta> ventas;
}
