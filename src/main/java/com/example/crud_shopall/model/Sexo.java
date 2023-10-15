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
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sexo;
    private String sexo;

    //HABILITAR ESTA SECCIÓN HASTA QUE SE AÑADAN LOS MODELOS DE EMPLEADO Y CLIENTE
    /*@OneToMany(targetEntity = Empleado.class, fetch = FetchType.LAZY, mappedBy = "sexo")
    @JsonIgnore
    private List<Empleado> Empleado;

    @OneToMany(targetEntity = Cliente.class, fetch = FetchType.LAZY, mappedBy = "sexo")
    @JsonIgnore
    private List<Cliente> Cliente;*/
}
