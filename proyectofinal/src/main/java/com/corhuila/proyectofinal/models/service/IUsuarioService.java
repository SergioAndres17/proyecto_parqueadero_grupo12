package com.corhuila.proyectofinal.models.service;

import com.corhuila.proyectofinal.models.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface IUsuarioService {

    @Transactional(readOnly = true)
    List<Usuario> findAll();

    @Transactional(readOnly = true)
    Usuario findById(Integer idUsuario);

    @Transactional
    Usuario save(Usuario usuario);

    @Transactional
    void delete(Integer idUsuario);

    String encriptarPassword(String password);

    String hashContrasenia(String password);
}
