/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Anthony Gomez Cabañas
 */
@Getter
@Setter
@Entity
@Table(name = "tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tienda")
    private long id_tienda;
    @Column(name = "tienda")
    private String tienda;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
}
