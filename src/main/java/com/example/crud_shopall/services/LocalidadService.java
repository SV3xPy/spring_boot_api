package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service

public class LocalidadService {
    HashMap<String,Object> datos;
    private final LocalidadRepository localidadRepository;

    @Autowired
    public LocalidadService(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    public List<Localidad> getLocalidad() {
        return this.localidadRepository.findAll();
    }

    public ResponseEntity<Object> newLocalidad(Localidad localidad) {
        datos = new HashMap<>();
        localidadRepository.save(localidad);
        datos.put("data",localidad);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                localidad,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateLocalidad(Localidad localidad, Long id) {
        datos = new HashMap<>();
        Optional<Localidad> localidadOpcional=this.localidadRepository.findById(id);
        if(localidadOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un localidad con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Localidad oldLocalidad = localidadOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldLocalidad.setLocalidad(localidad.getLocalidad());
        oldLocalidad.setLocalidad(localidad.getLocalidad());
        localidadRepository.save(oldLocalidad);
        datos.put("message","Localidad con ID: "+id+" actualizado con exito.");
        datos.put("data",oldLocalidad);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteLocalidad(Long id) {
        datos = new HashMap<>();
        boolean exists=this.localidadRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un localidad con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        localidadRepository.deleteById(id);
        datos.put("message","Localidad con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    }
