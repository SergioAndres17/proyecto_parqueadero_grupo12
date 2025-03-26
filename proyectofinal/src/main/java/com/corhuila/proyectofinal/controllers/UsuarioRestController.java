package com.corhuila.proyectofinal.controllers;

import com.corhuila.proyectofinal.models.entity.Usuario;
import com.corhuila.proyectofinal.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuario")
    public List<Usuario> index() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuario/{idUsuario}")
    public Usuario show(@PathVariable Integer idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }
        return usuario;
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        if (usuario == null || usuario.getNombre() == null || usuario.getEmail() == null) {
            throw new RuntimeException("El cuerpo de la solicitud está incompleto o es inválido.");
        }
        return usuarioService.save(usuario);
    }

    @PutMapping("/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer idUsuario) {
        Usuario usuario1 = usuarioService.findById(idUsuario);
        if (usuario1 == null) {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }

        usuario1.setNombre(usuario.getNombre());
        usuario1.setPrimerApellido(usuario.getPrimerApellido());
        usuario1.setSegundoApellido(usuario.getSegundoApellido());
        usuario1.setTelefono(usuario.getTelefono());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setTipoDocumento(usuario.getTipoDocumento());
        usuario1.setNumeroDocumento(usuario.getNumeroDocumento());

        // Encripta la nueva contraseña si se proporciona
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario1.setPassword(usuarioService.hashContrasenia(usuario.getPassword()));
        }

        return usuarioService.save(usuario1);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con id: " + idUsuario);
        }
        usuarioService.delete(idUsuario);
    }
}
