package com.example.crud_shopall.services;

import com.example.crud_shopall.model.TiendaProducto;
import com.example.crud_shopall.repositories.ProductoRepository;
import com.example.crud_shopall.repositories.TiendaProductoRepository;
import com.example.crud_shopall.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TiendaProductoService {
    HashMap<String,Object> datos;
    private final TiendaProductoRepository tiendaProductoRepository;
    private final TiendaRepository tiendaRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public TiendaProductoService(TiendaProductoRepository tiendaProductoRepository, ProductoRepository productoRepository, TiendaRepository tiendaRepository){
        this.tiendaProductoRepository = tiendaProductoRepository;
        this.tiendaRepository = tiendaRepository;
        this.productoRepository = productoRepository;
    }
    public List<TiendaProducto> getTiendaProducto(){return this.tiendaProductoRepository.findAll();}
    public ResponseEntity<Object> newTiendaProducto(TiendaProducto tiendaProducto){
        datos = new HashMap<>();
        tiendaProductoRepository.save(tiendaProducto);
        datos.put("Data",tiendaProducto);
        datos.put("Message", "Se ah agregado al inventario de una Tienda un Producto");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }
    public ResponseEntity<Object>updateTiendaProducto(TiendaProducto tiendaProducto, Long id){
        datos = new HashMap<>();
        Optional<TiendaProducto> res = this.tiendaProductoRepository.findById(id);
        if(res.isEmpty()){
            datos.put("Error",true);
            datos.put("Message","No existe un registro con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        TiendaProducto oldTiendaProducto = res.get();
        oldTiendaProducto.setTienda(tiendaProducto.getTienda());
        oldTiendaProducto.setProducto(tiendaProducto.getProducto());
        tiendaProductoRepository.save(oldTiendaProducto);
        datos.put("Message", "Se ha actuaalizado el registro.");
        datos.put("Data",oldTiendaProducto);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }
    public ResponseEntity<Object> deleteTiendaProducto (Long id){
        datos = new HashMap<>();
        boolean existe = this.tiendaProductoRepository.existsById(id);
        if(!existe){
            datos.put("Error",true);
            datos.put("Message", "No existe un registro con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        tiendaProductoRepository.deleteById(id);
        datos.put("Message", "El registro ha sido eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
