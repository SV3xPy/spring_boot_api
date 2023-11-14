package com.example.crud_shopall.services;
import com.example.crud_shopall.model.TipoPago;
import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.TipoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class TipoPagoService {
    HashMap<String,Object> datos;
    private final TipoPagoRepository tipoPagoRepository;

    @Autowired
    public TipoPagoService(TipoPagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    public List<TipoPago> getTipoPago(){
        return this.tipoPagoRepository.findAll();
    }

    public ResponseEntity<Object>  addTipoPago(TipoPago tipo_pago){
        datos = new HashMap<>();
        this.tipoPagoRepository.save(tipo_pago);
        datos.put("data",tipo_pago);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                tipo_pago,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object>  updateTipoPago(TipoPago tipopago, Long id){
        datos = new HashMap<>();
        Optional<TipoPago> tpOpcional=this.tipoPagoRepository.findById(id);
        if(tpOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        TipoPago oldTipoPago = tpOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldTipoPago.setTipo_pago(tipopago.getTipo_pago());
        tipoPagoRepository.save(oldTipoPago);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldTipoPago);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteTipoPago( Long id) {
        datos = new HashMap<>();
        boolean exists=this.tipoPagoRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        tipoPagoRepository.deleteById(id);
        datos.put("message","Telefono con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
