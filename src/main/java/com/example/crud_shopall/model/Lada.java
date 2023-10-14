package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

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
}
