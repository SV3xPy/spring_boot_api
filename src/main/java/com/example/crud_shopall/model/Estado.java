package com.example.crud_shopall.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

/*@Getter
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
}*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_estado;
    private String estado;

    @OneToMany(targetEntity = Municipio.class, fetch = FetchType.LAZY, mappedBy = "estado")
    @JsonIgnore
    private List<Municipio> municipios;
}
