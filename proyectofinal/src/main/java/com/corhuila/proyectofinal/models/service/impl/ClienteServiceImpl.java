package com.corhuila.proyectofinal.models.service.impl;

import com.corhuila.proyectofinal.models.dao.IClienteDao;
import com.corhuila.proyectofinal.models.dao.IUsuarioDao;
import com.corhuila.proyectofinal.models.dto.ClienteDto;
import com.corhuila.proyectofinal.models.entity.Cliente;
import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IClienteService;
import com.corhuila.proyectofinal.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        Usuario usuarioCreada = usuarioDao.save(usuario);

        Cliente clienteAgregar = new Cliente();
        clienteAgregar.setUsuario(usuarioCreada);
        clienteAgregar.setCreatedAt(new Date());
        return clienteDao.save(clienteAgregar);
    }

    @Override
    @Transactional
    public Cliente update(ClienteDto cliente, Integer idCliente) {
        Cliente clienteConsulta = this.findById(idCliente);

        Usuario usuario = usuarioService.findById(clienteConsulta.getUsuario().getIdUsuario());
        usuario.setNombre(cliente.getNombre());
        usuario.setPrimerApellido(cliente.getPrimerApellido());
        usuario.setSegundoApellido(cliente.getSegundoApellido());
        usuario.setEmail(cliente.getEmail());
        usuario.setTelefono(cliente.getTelefono());
        usuarioDao.save(usuario);

        Cliente clienteAgregar = new Cliente();
        clienteAgregar.setUpdatedAt(new Date());
        return clienteDao.save(clienteAgregar);
    }


    @Override
    @Transactional
    public void delete(Integer idCliente) {
        clienteDao.deleteById(idCliente);
    }
}
