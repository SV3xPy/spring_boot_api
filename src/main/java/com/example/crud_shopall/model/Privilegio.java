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
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_privilegio;
    private String privilegio;
}
