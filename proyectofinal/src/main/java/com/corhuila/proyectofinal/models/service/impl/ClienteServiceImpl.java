package com.corhuila.proyectofinal.models.service.impl;

import com.corhuila.proyectofinal.models.dao.IClienteDao;
import com.corhuila.proyectofinal.models.dao.IUsuarioDao;
import com.corhuila.proyectofinal.models.dto.ClienteDto;
import com.corhuila.proyectofinal.models.entity.Cliente;
import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IClienteService;
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
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Integer idCliente) {
        return clienteDao.findById(idCliente).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(ClienteDto cliente) {
        Usuario usuario = new Usuario();
        usuario.setNombre(cliente.getNombre());
        usuario.setPrimerApellido(cliente.getPrimerApellido());
        usuario.setSegundoApellido(cliente.getSegundoApellido());
        usuario.setNumeroDocumento(cliente.getNumeroDocumento());
        usuario.setTipoDocumento(cliente.getTipoDocumento());
        usuario.setEmail(cliente.getEmail());
        usuario.setTelefono(cliente.getTelefono());

        // Encripta la contraseña antes de guardar el usuario
        if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
            usuario.setPassword(hashPassword(cliente.getPassword()));
        } else {
            throw new RuntimeException("La contraseña no puede estar vacía");
        }

        Usuario usuarioCreado = usuarioDao.save(usuario);
        Cliente clienteAgregar = new Cliente();
        clienteAgregar.setUsuario(usuarioCreado);
        clienteAgregar.setCreatedAt(new Date());

        return clienteDao.save(clienteAgregar);
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
    public Cliente update(ClienteDto cliente, Integer idCliente) {
        Cliente clienteConsulta = this.findById(idCliente);
        if (clienteConsulta == null) {
            throw new RuntimeException("Cliente no encontrado con id: " + idCliente);
        }

        Usuario usuario = usuarioService.findById(clienteConsulta.getUsuario().getIdUsuario());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado para el cliente con id: " + idCliente);
        }

        usuario.setNombre(cliente.getNombre());
        usuario.setPrimerApellido(cliente.getPrimerApellido());
        usuario.setSegundoApellido(cliente.getSegundoApellido());
        usuario.setEmail(cliente.getEmail());
        usuario.setTelefono(cliente.getTelefono());

        // Si la nueva contraseña no es nula ni vacía, la encripta antes de actualizarla
        if (cliente.getPassword() != null && !cliente.getPassword().isEmpty()) {
            usuario.setPassword(hashPassword(cliente.getPassword()));
        }

        usuarioDao.save(usuario);
        clienteConsulta.setUpdatedAt(new Date());

        return clienteDao.save(clienteConsulta);
    }

    @Override
    @Transactional
    public void delete(Integer idCliente) {
        Cliente cliente = this.findById(idCliente);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con id: " + idCliente);
        }
        clienteDao.deleteById(idCliente);
    }
}
