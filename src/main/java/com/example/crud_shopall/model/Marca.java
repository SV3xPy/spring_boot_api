/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.crud_shopall.model;

import jakarta.persistence.*;
import lombok.*;

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
    private long id_marca;
    private String marca;
}
