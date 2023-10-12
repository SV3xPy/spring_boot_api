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
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    int id_estado;
    @Column(name = "estado")
    String estado;
}
