package com.corhuila.proyectofinal.models.dao;

import com.corhuila.proyectofinal.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Integer> {
}