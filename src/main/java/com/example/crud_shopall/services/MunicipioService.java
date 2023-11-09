package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Municipio;
import com.example.crud_shopall.repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {
    HashMap<String,Object> datos;
    private final MunicipioRepository municipioRepository;
    @Autowired
    public MunicipioService (MunicipioRepository municipioRepository){this.municipioRepository = municipioRepository;}
    public List<Municipio> getMunicipio(){return this.municipioRepository.findAll();}
    public ResponseEntity<Object> newMunicipio(Municipio municipio) {
        datos = new HashMap<>();
        municipioRepository.save(municipio);
        datos.put("data",municipio);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                municipio,
                HttpStatus.CREATED
        );
    }
    public ResponseEntity<Object> updateMunicipio(Municipio municipio, Long id) {
        datos = new HashMap<>();
        Optional<Municipio> municipioOpcional=this.municipioRepository.findById(id);
        if(municipioOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un municipio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Municipio oldMunicipio = municipioOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldMunicipio.setMunicipio(municipio.getMunicipio());
        oldMunicipio.setEstado(municipio.getEstado());
        municipioRepository.save(oldMunicipio);
        datos.put("message","Municipio con ID: "+id+" actualizado con exito.");
        datos.put("data",oldMunicipio);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    public ResponseEntity<Object> deleteMunicipio(Long id) {
        datos = new HashMap<>();
        boolean exists=this.municipioRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un municipio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        municipioRepository.deleteById(id);
        datos.put("message","Municipio con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
