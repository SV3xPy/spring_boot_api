package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Venta_operacion;
        import com.example.crud_shopall.repositories.Venta_operacionRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class Venta_operacionService {
    private final Venta_operacionRepository venta_operacionRepository;

    @Autowired
    public Venta_operacionService(Venta_operacionRepository venta_operacionRepository) {
        this.venta_operacionRepository = venta_operacionRepository;
    }

    public List<Venta_operacion> getVenta_operacion(){ return this.venta_operacionRepository.findAll(); }

    public Venta_operacion setVenta_operacion(Venta_operacion venta_operacion){
        return this.venta_operacionRepository.save(venta_operacion);
    }

    public void deleteVenta_operacion(Long id_venta_operacion) { this.venta_operacionRepository.deleteById(id_venta_operacion); }
}