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
@Table
public class Lada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_lada;
    private long lada;

    @OneToMany(targetEntity = Telefono.class, fetch = FetchType.LAZY, mappedBy = "lada")
    @JsonIgnore
    private List<Telefono> telefonos;
}
