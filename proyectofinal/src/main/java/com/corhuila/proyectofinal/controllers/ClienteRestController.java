package com.corhuila.proyectofinal.controllers;

import com.corhuila.proyectofinal.models.dto.ClienteDto;
import com.corhuila.proyectofinal.models.entity.Cliente;
import com.corhuila.proyectofinal.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/cliente")
    public List<Cliente> index(){
        return clienteService.findAll();
    }

    @GetMapping("/cliente/{idCliente}")
    public Cliente show(@PathVariable Integer idCliente){
        return clienteService.findById(idCliente);
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody ClienteDto cliente){

        return clienteService.save(cliente);
    }

    @PutMapping("/cliente/{idCliente}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody ClienteDto cliente, @PathVariable Integer idCliente){
        return clienteService.update(cliente, idCliente);
    }

    @DeleteMapping("/cliente/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idCliente){
        clienteService.delete(idCliente);
    }

}
