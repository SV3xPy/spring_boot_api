package com.example.crud_shopall.services;


import com.example.crud_shopall.model.Tipo_pago;
        import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.repositories.Tipo_pagoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class Tipo_pagoService {

    HashMap<String,Object> datos;
    private final Tipo_pagoRepository tpRepository;

    @Autowired
    public Tipo_pagoService(Tipo_pagoRepository tpRepository) {
        this.tpRepository = tpRepository;
    }

    public List<Tipo_pago> getTipo_pago(){
        return this.tpRepository.findAll();
    }

    public ResponseEntity<Object>  addTipo_pago(Tipo_pago tipo_pago){
        datos = new HashMap<>();
        this.tpRepository.save(tipo_pago);
        datos.put("data",tipo_pago);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                tipo_pago,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object>  updateTipo_pago(Tipo_pago tipo_pago, Long id){
        datos = new HashMap<>();
        Optional<Tipo_pago> tpOpcional=this.tpRepository.findById(id);
        if(tpOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Tipo_pago oldTipo_pago = tpOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldTipo_pago.setTipo_pago(tipo_pago.getTipo_pago());
        tpRepository.save(oldTipo_pago);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldTipo_pago);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteTipo_pago( Long id) {
        datos = new HashMap<>();
        boolean exists=this.tpRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        tpRepository.deleteById(id);
        datos.put("message","Telefono con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    
}