package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Cliente;
import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.repositories.ClienteRepository;
import com.example.crud_shopall.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    HashMap<String,Object> datos;

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getCliente(){
        return this.clienteRepository.findAll();
    }

    public ResponseEntity<Object> newCliente(Cliente cliente) {
        datos = new HashMap<>();
        clienteRepository.save(cliente);
        datos.put("data",cliente);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                cliente,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteCliente(Long id) {
        datos = new HashMap<>();
        boolean exists=this.clienteRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un cliente con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        clienteRepository.deleteById(id);
        datos.put("message","Cliente con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateCliente(Cliente cliente, Long id) {
        datos = new HashMap<>();
        Optional<Cliente> clienteOpcional=this.clienteRepository.findById(id);
        if(clienteOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un cliente con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Cliente oldCliente = clienteOpcional.get();
        //SE ACTUALIZA EL NOMBRE ??
        oldCliente.setNombre(cliente.getNombre());
        clienteRepository.save(oldCliente);
        datos.put("message","Cliente con ID: "+id+" actualizado con exito.");
        datos.put("data",oldCliente);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
