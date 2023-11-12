package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiendaService {
    //private final TiendaRepository tiendaRepository;
    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    public TiendaService(TiendaRepository tiendaRepository){this.tiendaRepository = tiendaRepository;}
    public List<Tienda> getTienda(){
        return this.tiendaRepository.findAll();
    }

}
