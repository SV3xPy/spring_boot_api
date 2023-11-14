package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Cliente;
import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.services.ClienteService;
import com.example.crud_shopall.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")

public class ClienteController {
    private final ClienteService clienteService;

    @Autowired //Señala que es una inyección de dependencias
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getCliente(){
        return this.clienteService.getCliente();
    }
    @PostMapping
    public ResponseEntity<Object> newCliente(@RequestBody Cliente cliente){
        return this.clienteService.newCliente(cliente);
    }
    @PutMapping(path = "{clienteId}")
    public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente,@PathVariable("clienteId") Long id){
        return this.clienteService.updateCliente(cliente,id);
    }
    @DeleteMapping(path="{clienteId}")
    public ResponseEntity<Object> deleteCliente(@PathVariable("clienteId") Long id){
        return this.clienteService.deleteCliente(id);
    }
}
