package com.example.crud_shopall.services;
import com.example.crud_shopall.model.TipoPago;
import com.example.crud_shopall.model.Venta;
import com.example.crud_shopall.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class VentaService {
    HashMap<String,Object> datos;
    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> getVenta(){ return this.ventaRepository.findAll(); }

    public ResponseEntity<Object> addVenta(Venta venta){

        datos = new HashMap<>();
        this.ventaRepository.save(venta);
        datos.put("data",venta);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                venta,
                HttpStatus.CREATED
        );

    }

    public ResponseEntity<Object> updateVenta(Venta venta, Long id){
        datos = new HashMap<>();
        Optional<Venta> ventaOpcional=this.ventaRepository.findById(id);
        if(ventaOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Venta oldVenta = ventaOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldVenta.setTienda(venta.getTienda());
        oldVenta.setEmpleado(venta.getEmpleado());
        oldVenta.setCliente(venta.getCliente());
        oldVenta.setFecha(venta.getFecha());
        oldVenta.setVenta_status(venta.getVenta_status());
        ventaRepository.save(oldVenta);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldVenta);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public  ResponseEntity<Object>  deleteVenta(Long id_venta) {

        datos = new HashMap<>();
        boolean exists=this.ventaRepository.existsById(id_venta);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        ventaRepository.deleteById(id_venta);
        datos.put("message","Telefono con ID: "+id_venta+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }
}
