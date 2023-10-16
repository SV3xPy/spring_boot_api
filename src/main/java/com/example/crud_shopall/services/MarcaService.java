package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Marca;
import com.example.crud_shopall.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService
{
    HashMap<String, Object> datos;
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService (MarcaRepository marcaRepository)
    {
        this.marcaRepository = marcaRepository;
    }

    public List <Marca> getMarca ()
    {
        return this.marcaRepository.findAll();
    }

    public ResponseEntity<Object> newMarca(Marca marca)
    {
        Optional<Marca> res = marcaRepository.findMarcaByMarca(marca.getMarca());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa marca.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        marcaRepository.save(marca);
        datos.put("Data", marca);
        datos.put("Message", "La marca ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateMarca(Marca marca, Long id)
    {
        datos = new HashMap<>();
        Optional<Marca> res = this.marcaRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una marca con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Marca oldMarca = res.get();
        oldMarca.setMarca(marca.getMarca());
        marcaRepository.save(oldMarca);
        datos.put("Message", "Se ha actualizado la marca.");
        datos.put("Data", oldMarca);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteMarca (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.marcaRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una marca con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        marcaRepository.deleteById(id);
        datos.put("Message", "La marca ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
