package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    HashMap<String,Object> datos;
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> getEstado(){
        return this.estadoRepository.findAll();
    }

    public ResponseEntity<Object> newEstado(Estado estado) {
        datos = new HashMap<>();
        estadoRepository.save(estado);
        datos.put("data",estado);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                estado,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteEstado(Long id) {
        datos = new HashMap<>();
        boolean exists=this.estadoRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un estado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        estadoRepository.deleteById(id);
        datos.put("message","Estado con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateEstado(Estado estado, Long id) {
        datos = new HashMap<>();
        Optional<Estado> estadoOpcional=this.estadoRepository.findById(id);
        if(estadoOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un estado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Estado oldEstado = estadoOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldEstado.setEstado(estado.getEstado());
        estadoRepository.save(oldEstado);
        datos.put("message","Estado con ID: "+id+" actualizado con exito.");
        datos.put("data",oldEstado);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
