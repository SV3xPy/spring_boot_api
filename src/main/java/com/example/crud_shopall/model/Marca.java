/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.crud_shopall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * 
 * @author Anthony Gomez Cabañas
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Marca
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;
    @Column(unique = true)
    private String marca;

    @OneToMany(targetEntity = Producto.class, fetch = FetchType.LAZY, mappedBy = "marca")
    @JsonIgnore
    private List<Producto> productos;
}
