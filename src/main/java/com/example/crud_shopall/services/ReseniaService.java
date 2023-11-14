package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.Resenia;
import com.example.crud_shopall.repositories.EstadoRepository;
import com.example.crud_shopall.repositories.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReseniaService {
    HashMap<String,Object> datos;
    private final ReseniaRepository reseniaRepository;

    @Autowired
    public ReseniaService(ReseniaRepository reseniaRepository) {
        this.reseniaRepository = reseniaRepository;
    }

    public List<Resenia> getResenia(){
        return this.reseniaRepository.findAll();
    }

    public ResponseEntity<Object> newResenia(Resenia resenia) {
        datos = new HashMap<>();
        reseniaRepository.save(resenia);
        datos.put("data",resenia);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                resenia,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteResenia(Long id) {
        datos = new HashMap<>();
        boolean exists=this.reseniaRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe una reseña con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        reseniaRepository.deleteById(id);
        datos.put("message","Reseña con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateResenia(Resenia resenia, Long id) {
        datos = new HashMap<>();
        Optional<Resenia> reseniaOpcional=this.reseniaRepository.findById(id);
        if(reseniaOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe una reseña con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Resenia oldResenia = reseniaOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldResenia.setResenia(resenia.getResenia());
        reseniaRepository.save(oldResenia);
        datos.put("message","Resenia con ID: "+id+" actualizado con exito.");
        datos.put("data",oldResenia);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
