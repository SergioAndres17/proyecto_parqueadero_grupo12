package com.corhuila.proyectofinal.models.dao;

import com.corhuila.proyectofinal.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
}
