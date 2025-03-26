package com.corhuila.proyectofinal.models.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EmpleadoDto {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String email;
    private String tipoDocumento;
    private String numeroDocumento;
    private String password;
}
