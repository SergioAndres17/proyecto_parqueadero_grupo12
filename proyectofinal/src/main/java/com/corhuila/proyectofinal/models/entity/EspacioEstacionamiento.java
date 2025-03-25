package com.corhuila.proyectofinal.models.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "espacioEstacionamiento")
public class EspacioEstacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espacio_estacionamiento")
    private Integer idEspacioEstacionamiento;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private String estado;
}
