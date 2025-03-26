package com.corhuila.proyectofinal.models.service;

import com.corhuila.proyectofinal.models.dto.EmpleadoDto;
import com.corhuila.proyectofinal.models.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> findAll();

    public Empleado findById(Integer idEmpleado);

    public Empleado save(EmpleadoDto empleadoDto);

    public String hashPassword(String password);

    public Empleado update(EmpleadoDto empleadoDto, Integer idEmpleado);

    public void delete(Integer idEmpleado);
}
