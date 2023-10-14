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
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localidad")
    private long id_localidad;
    @Column(name = "localidad")
    private String localidad;
    private long id_municipio;
    /*
    Por lo mientras unicamente se maneja como una columna
    @ManyToOne
    @JoinColumn(name = "id_municipio")
    Municipio municipio;
     */
}
