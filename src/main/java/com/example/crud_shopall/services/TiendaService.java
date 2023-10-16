package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {
    HashMap<String,Object> datos;
    private final TiendaRepository tiendaRepository;

    @Autowired
    public TiendaService(TiendaRepository tiendaRepository){this.tiendaRepository = tiendaRepository;}
    public List<Tienda> getTienda(){
        return this.tiendaRepository.findAll();
    }

    public ResponseEntity<Object> newTienda(Tienda tienda) {
        datos = new HashMap<>();
        tiendaRepository.save(tienda);
        datos.put("data",tienda);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                tienda,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateTienda(Tienda tienda, Long id) {
        datos = new HashMap<>();
        Optional<Tienda> tiendaOpcional=this.tiendaRepository.findById(id);
        if(tiendaOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tienda con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Tienda oldTienda = tiendaOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldTienda.setTienda(tienda.getTienda());
        oldTienda.setTienda(tienda.getTienda());
        tiendaRepository.save(oldTienda);
        datos.put("message","Tienda con ID: "+id+" actualizado con exito.");
        datos.put("data",oldTienda);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteTienda(Long id) {
        datos = new HashMap<>();
        boolean exists=this.tiendaRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un tienda con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        tiendaRepository.deleteById(id);
        datos.put("message","Tienda con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
