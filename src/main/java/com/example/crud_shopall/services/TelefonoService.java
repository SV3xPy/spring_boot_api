package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.repositories.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TelefonoService {

    HashMap<String,Object> datos;
    private final TelefonoRepository telefonoRepository;

    @Autowired
    public TelefonoService(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    public List<Telefono> getTelefono(){
        return this.telefonoRepository.findAll();
    }

    public ResponseEntity<Object> newTelefono(Telefono telefono) {
        datos = new HashMap<>();
        telefonoRepository.save(telefono);
        datos.put("data",telefono);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                telefono,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateTelefono(Telefono telefono, Long id) {
        datos = new HashMap<>();
        Optional<Telefono> telefonoOpcional=this.telefonoRepository.findById(id);
        if(telefonoOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un telefono con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Telefono oldTelefono = telefonoOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldTelefono.setTelefono(telefono.getTelefono());
        oldTelefono.setLada(telefono.getLada());
        telefonoRepository.save(oldTelefono);
        datos.put("message","Telefono con ID: "+id+" actualizado con exito.");
        datos.put("data",oldTelefono);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    public ResponseEntity<Object> deleteTelefono(Long id) {
        datos = new HashMap<>();
        boolean exists=this.telefonoRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un telefono con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        telefonoRepository.deleteById(id);
        datos.put("message","Telefono con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
