package com.corhuila.proyectofinal.models.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ClienteDto {
    private Integer idPersona;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String email;
    private Integer tipoDocumento;
    private String numeroDocumento;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
