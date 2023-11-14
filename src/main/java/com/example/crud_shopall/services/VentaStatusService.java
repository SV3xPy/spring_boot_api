package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.VentaDetalle;
import com.example.crud_shopall.model.VentaStatus;
import com.example.crud_shopall.repositories.EstadoRepository;
import com.example.crud_shopall.repositories.VentaStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VentaStatusService {
    HashMap<String,Object> datos;
    private final VentaStatusRepository ventaStatusRepository;

    @Autowired
    public VentaStatusService(VentaStatusRepository ventaStatusRepository) {
        this.ventaStatusRepository = ventaStatusRepository;
    }

    public List<VentaStatus> getVentaStatus(){
        return this.ventaStatusRepository.findAll();
    }

    public ResponseEntity<Object> newVentaStatus(VentaStatus ventaStatus) {
        datos = new HashMap<>();
        ventaStatusRepository.save(ventaStatus);
        datos.put("data",ventaStatus);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                ventaStatus,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteVentaStatus(Long id) {
        datos = new HashMap<>();
        boolean exists=this.ventaStatusRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe una VentaStatus con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        ventaStatusRepository.deleteById(id);
        datos.put("message","VentaStatus con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateVentaStatus(VentaStatus ventaStatus, Long id) {
        datos = new HashMap<>();
        Optional<VentaStatus> ventaStatusOpcional=this.ventaStatusRepository.findById(id);
        if(ventaStatusOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe una VentaStatus con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        VentaStatus oldVentaStatus = ventaStatusOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldVentaStatus.setVenta_status(ventaStatus.getVenta_status());
        ventaStatusRepository.save(oldVentaStatus);
        datos.put("message","VentaStatus con ID: "+id+" actualizado con exito.");
        datos.put("data",oldVentaStatus);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
