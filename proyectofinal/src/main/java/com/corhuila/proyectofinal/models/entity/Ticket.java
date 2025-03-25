package com.corhuila.proyectofinal.models.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "hora_entrada")
    private Date horaEntrada;

    @Column(name = "hora_salida")
    private Date horaSalida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_placa")
    private Cliente vehiculo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio")
    private Cliente espacioEstacionamiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado")
    private Cliente empleado;
}
