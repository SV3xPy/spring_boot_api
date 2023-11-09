package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.repositories.LadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LadaService {
    HashMap<String,Object> datos;

    private final LadaRepository ladaRepository;

    @Autowired
    public LadaService(LadaRepository ladaRepository) {
        this.ladaRepository = ladaRepository;
    }

    public List<Lada> getLada(){
        return this.ladaRepository.findAll();
    }

    public ResponseEntity<Object> newLada(Lada lada) {
        datos = new HashMap<>();
        ladaRepository.save(lada);
        datos.put("data",lada);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                lada,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteLada(Long id) {
        datos = new HashMap<>();
        boolean exists=this.ladaRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe una lada con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        ladaRepository.deleteById(id);
        datos.put("message","Lada con ID: "+id+" eliminada con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> updateLada(Lada lada, Long id) {
        datos = new HashMap<>();
        Optional<Lada> ladaOpcional=this.ladaRepository.findById(id);
        if(ladaOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe una lada con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Lada oldLada = ladaOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldLada.setLada(lada.getLada());
        ladaRepository.save(oldLada);
        datos.put("message","Lada con ID: "+id+" actualizada con exito.");
        datos.put("data",oldLada);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
