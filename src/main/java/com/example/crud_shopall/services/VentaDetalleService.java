package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.VentaDetalle;
import com.example.crud_shopall.repositories.EstadoRepository;
import com.example.crud_shopall.repositories.VentaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VentaDetalleService {
    HashMap<String,Object> datos;
    private final VentaDetalleRepository ventaDetalleRepository;

    @Autowired
    public VentaDetalleService(VentaDetalleRepository ventaDetalleRepository) {
        this.ventaDetalleRepository = ventaDetalleRepository;
    }

    public List<VentaDetalle> getVentaDetalle(){
        return this.ventaDetalleRepository.findAll();
    }

    public ResponseEntity<Object> newVentaDetalle(VentaDetalle ventaDetalle) {
        datos = new HashMap<>();
        ventaDetalleRepository.save(ventaDetalle);
        datos.put("data",ventaDetalle);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                ventaDetalle,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteVentaDetalle(Long id) {
        datos = new HashMap<>();
        boolean exists=this.ventaDetalleRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe una VentaDetalle con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        ventaDetalleRepository.deleteById(id);
        datos.put("message","VentaDetalle con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateVentaDetalle(VentaDetalle ventaDetalle, Long id) {
        datos = new HashMap<>();
        Optional<VentaDetalle> ventaDetalleOpcional=this.ventaDetalleRepository.findById(id);
        if(ventaDetalleOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe una VentaDetalle con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        VentaDetalle oldVentaDetalle = ventaDetalleOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldVentaDetalle.setCantidad(ventaDetalle.getCantidad());
        ventaDetalleRepository.save(oldVentaDetalle);
        datos.put("message","VentaDetalle con ID: "+id+" actualizado con exito.");
        datos.put("data",oldVentaDetalle);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
