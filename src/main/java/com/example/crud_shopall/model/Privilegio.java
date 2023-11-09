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
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_privilegio;
    private String privilegio;

    @OneToMany(targetEntity = RolPrivilegio.class, fetch = FetchType.LAZY, mappedBy = "privilegio")
    @JsonIgnore
    private List<RolPrivilegio> rolPrivilegios;
}
