package com.example.crud_shopall.services;
import com.example.crud_shopall.model.Categoria;
import com.example.crud_shopall.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService
{
    HashMap<String, Object> datos;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService (CategoriaRepository categoriaRepository)
    {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getCategoria ()
    {
        return this.categoriaRepository.findAll();
    }

    public ResponseEntity<Object> newCategoria(Categoria categoria)
    {
        Optional<Categoria> res = categoriaRepository.findCategoriaByCategoria(categoria.getCategoria());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        categoriaRepository.save(categoria);
        datos.put("Data", categoria);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateCategoria(Categoria categoria, Long id)
    {
        datos = new HashMap<>();
        Optional<Categoria> res = this.categoriaRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoria con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Categoria oldCategoria = res.get();
        oldCategoria.setCategoria(categoria.getCategoria());
        categoriaRepository.save(oldCategoria);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldCategoria);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteCategoria (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.categoriaRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        categoriaRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
