package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Venta;
import com.example.crud_shopall.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> getVenta(){ return this.ventaRepository.findAll(); }

    public Venta setVenta(Venta venta){
        return this.ventaRepository.save(venta);
    }

    public void deleteVenta(Long id_venta) { this.ventaRepository.deleteById(id_venta); }
}