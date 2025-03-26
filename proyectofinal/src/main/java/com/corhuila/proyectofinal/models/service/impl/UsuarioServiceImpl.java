package com.corhuila.proyectofinal.models.service.impl;

import com.corhuila.proyectofinal.models.dao.IUsuarioDao;
import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
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
        // Verifica si la contraseña no está vacía antes de encriptarla
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            System.out.println("Contraseña antes de encriptar: " + usuario.getPassword());  // Para depuración
            usuario.setPassword(hashContrasenia(usuario.getPassword()));
            System.out.println("Contraseña encriptada: " + usuario.getPassword());  // Para depuración
        }
        return usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public void delete(Integer idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    public String encriptarPassword(String password) {
        return hashContrasenia(password);
    }

    @Override
    public String hashContrasenia(String password) {
        try {
            MessageDigest instancia = MessageDigest.getInstance("SHA-256");
            byte[] hash = instancia.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}
