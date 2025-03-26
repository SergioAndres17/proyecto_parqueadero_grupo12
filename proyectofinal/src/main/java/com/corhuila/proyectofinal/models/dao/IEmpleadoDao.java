package com.corhuila.proyectofinal.models.dao;

import com.corhuila.proyectofinal.models.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer> {
}
