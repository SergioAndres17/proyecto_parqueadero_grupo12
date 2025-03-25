package com.corhuila.proyectofinal.models.service;

import com.corhuila.proyectofinal.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoService extends CrudRepository<Empleado, Integer> {
}
