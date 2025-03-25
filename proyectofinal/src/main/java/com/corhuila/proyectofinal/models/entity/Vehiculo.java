package com.corhuila.proyectofinal.models.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(name = "placa")
    private Integer placa;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "marca")
    private String marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
