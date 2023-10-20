package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Tipo_pago;
import com.example.crud_shopall.model.Venta_operacion;
        import com.example.crud_shopall.repositories.Venta_operacionRepository;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class Venta_operacionService {

    HashMap<String,Object> datos;
    private final Venta_operacionRepository venta_operacionRepository;

    @Autowired
    public Venta_operacionService(Venta_operacionRepository venta_operacionRepository) {
        this.venta_operacionRepository = venta_operacionRepository;
    }

    public List<Venta_operacion> getVenta_operacion(){ return this.venta_operacionRepository.findAll(); }

    public ResponseEntity<Object> addVenta_operacion(Venta_operacion venta_operacion){
        datos = new HashMap<>();
        this.venta_operacionRepository.save(venta_operacion);

        datos.put("data", venta_operacion);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                venta_operacion,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateVenta_operacion(Venta_operacion venta_operacion, Long id){
        datos = new HashMap<>();
        Optional<Venta_operacion> venta_operacionOpcional=this.venta_operacionRepository.findById(id);
        if(venta_operacionOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Venta_operacion oldVenta_operacion = venta_operacionOpcional.get();

        //SE ACTUALIZA EL NOMBRE
        oldVenta_operacion.setVenta(venta_operacion.getVenta());
        oldVenta_operacion.setTipo_pago(venta_operacion.getTipo_pago());
        oldVenta_operacion.setFolio(venta_operacion.getFolio());
        oldVenta_operacion.setMonto(venta_operacion.getMonto());
        oldVenta_operacion.setAutorizado(venta_operacion.getAutorizado());
        venta_operacionRepository.save(oldVenta_operacion);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldVenta_operacion);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteVenta_operacion(Long id) {

        datos = new HashMap<>();
        boolean exists=this.venta_operacionRepository.existsById(id);

        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un empleado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        this.venta_operacionRepository.deleteById(id);
        venta_operacionRepository.deleteById(id);
        datos.put("message","Empleado con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }
}