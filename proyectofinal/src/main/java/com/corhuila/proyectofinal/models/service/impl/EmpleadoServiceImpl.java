package com.corhuila.proyectofinal.models.service.impl;

import com.corhuila.proyectofinal.models.dao.IEmpleadoDao;
import com.corhuila.proyectofinal.models.dao.IUsuarioDao;
import com.corhuila.proyectofinal.models.dto.EmpleadoDto;
import com.corhuila.proyectofinal.models.entity.Empleado;
import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IEmpleadoService;
import com.corhuila.proyectofinal.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Integer idEmpleado) {
        return empleadoDao.findById(idEmpleado).orElse(null);
    }

    @Override
    @Transactional
    public Empleado save(EmpleadoDto empleado) {
        Usuario usuario = new Usuario();
        usuario.setNombre(empleado.getNombre());
        usuario.setPrimerApellido(empleado.getPrimerApellido());
        usuario.setSegundoApellido(empleado.getSegundoApellido());
        usuario.setNumeroDocumento(empleado.getNumeroDocumento());
        usuario.setTipoDocumento(empleado.getTipoDocumento());
        usuario.setEmail(empleado.getEmail());
        usuario.setTelefono(empleado.getTelefono());
        usuario.setPassword(hashPassword(empleado.getPassword())); // Encriptar contraseña
        usuario.setCreatedAt(new Date());

        Usuario usuarioCreado = usuarioDao.save(usuario);
        Empleado empleadoAgregar = new Empleado();
        empleadoAgregar.setUsuario(usuarioCreado);
        empleadoAgregar.setCreatedAt(new Date());

        return empleadoDao.save(empleadoAgregar);
    }

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest instancia = MessageDigest.getInstance("SHA-256");
            byte[] hash = instancia.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash); // Devuelve el hash en formato hexadecimal
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    @Override
    @Transactional
    public Empleado update(EmpleadoDto empleado, Integer idEmpleado) {
        Empleado empleadoConsulta = this.findById(idEmpleado);
        if (empleadoConsulta == null) {
            throw new RuntimeException("Empleado no encontrado con id: " + idEmpleado);
        }

        Usuario usuario = usuarioService.findById(empleadoConsulta.getUsuario().getIdUsuario());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado para el empleado con id: " + idEmpleado);
        }

        usuario.setNombre(empleado.getNombre());
        usuario.setPrimerApellido(empleado.getPrimerApellido());
        usuario.setSegundoApellido(empleado.getSegundoApellido());
        usuario.setEmail(empleado.getEmail());
        usuario.setTelefono(empleado.getTelefono());

        if (empleado.getPassword() != null && !empleado.getPassword().isEmpty()) {
            usuario.setPassword(hashPassword(empleado.getPassword()));
        }

        usuarioDao.save(usuario);
        empleadoConsulta.setUpdatedAt(new Date());

        return empleadoDao.save(empleadoConsulta);
    }

    @Override
    @Transactional
    public void delete(Integer idEmpleado) {
        Empleado empleado = this.findById(idEmpleado);
        if (empleado == null) {
            throw new RuntimeException("Empleado no encontrado con id: " + idEmpleado);
        }
        empleadoDao.deleteById(idEmpleado);
    }
}
