package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.repositories.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SexoService {
    HashMap<String,Object> datos;
    private final SexoRepository sexoRepository;

    @Autowired
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<Sexo> getSexo(){
        return this.sexoRepository.findAll();
    }

    public ResponseEntity<Object> newSexo(Sexo sexo) {
        datos = new HashMap<>();
        sexoRepository.save(sexo);
        datos.put("data",sexo);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                sexo,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteSexo(Long id) {
        datos = new HashMap<>();
        boolean exists=this.sexoRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un sexo con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sexoRepository.deleteById(id);
        datos.put("message","Sexo con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateSexo(Sexo sexo, Long id) {
        datos = new HashMap<>();
        Optional<Sexo> sexoOpcional=this.sexoRepository.findById(id);
        if(sexoOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un sexo con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Sexo oldSexo = sexoOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldSexo.setSexo(sexo.getSexo());
        sexoRepository.save(oldSexo);
        datos.put("message","Sexo con ID: "+id+" actualizado con exito.");
        datos.put("data",oldSexo);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
