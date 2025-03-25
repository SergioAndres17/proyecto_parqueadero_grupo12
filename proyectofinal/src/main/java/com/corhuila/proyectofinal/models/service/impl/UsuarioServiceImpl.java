package com.corhuila.proyectofinal.models.service.impl;

import com.corhuila.proyectofinal.models.dao.IUsuarioDao;
import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer idUsuario) {
        return usuarioDao.findById(idUsuario).orElse(null);
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public void delete(Integer idUsuario) {
        usuarioDao.deleteById(idUsuario);
}
}
