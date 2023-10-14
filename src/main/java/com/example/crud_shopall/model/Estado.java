package com.example.crud_shopall.model;
import jakarta.persistence.*;
import lombok.*;

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
}