package com.example.crud_shopall.services;
import com.example.crud_shopall.model.TipoPago;
import com.example.crud_shopall.model.VentaOperacion;
import com.example.crud_shopall.repositories.VentaOperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class VentaOperacionService {
    HashMap<String,Object> datos;
    private final VentaOperacionRepository ventaOperacionRepository;

    @Autowired
    public VentaOperacionService(VentaOperacionRepository ventaOperacionRepository) {
        this.ventaOperacionRepository = ventaOperacionRepository;
    }
    public List<VentaOperacion> getVenta_operacion(){ return this.ventaOperacionRepository.findAll(); }

    public ResponseEntity<Object> addVenta_operacion(VentaOperacion venta_operacion){
        datos = new HashMap<>();
        this.ventaOperacionRepository.save(venta_operacion);

        datos.put("data", venta_operacion);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                venta_operacion,
                HttpStatus.CREATED
        );
    }
    public ResponseEntity<Object> updateVenta_operacion(VentaOperacion venta_operacion, Long id){
        datos = new HashMap<>();
        Optional<VentaOperacion> venta_operacionOpcional=this.ventaOperacionRepository.findById(id);
        if(venta_operacionOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        VentaOperacion oldVenta_operacion = venta_operacionOpcional.get();

        //SE ACTUALIZA EL NOMBRE
        oldVenta_operacion.setVenta(venta_operacion.getVenta());
        oldVenta_operacion.setTipo_pago(venta_operacion.getTipo_pago());
        oldVenta_operacion.setFolio(venta_operacion.getFolio());
        oldVenta_operacion.setMonto(venta_operacion.getMonto());
        oldVenta_operacion.setAutorizado(venta_operacion.getAutorizado());
        ventaOperacionRepository.save(oldVenta_operacion);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldVenta_operacion);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteVenta_operacion(Long id) {

        datos = new HashMap<>();
        boolean exists=this.ventaOperacionRepository.existsById(id);

        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un empleado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        this.ventaOperacionRepository.deleteById(id);
        ventaOperacionRepository.deleteById(id);
        datos.put("message","Empleado con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

}
