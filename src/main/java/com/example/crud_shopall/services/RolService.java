package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    HashMap<String,Object> datos;
    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> getRol(){
        return this.rolRepository.findAll();
    }

    public ResponseEntity<Object> newRol(Rol rol) {
        datos = new HashMap<>();
        rolRepository.save(rol);
        datos.put("data",rol);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                rol,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteRol(Long id) {
        datos = new HashMap<>();
        boolean exists=this.rolRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un rol con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        rolRepository.deleteById(id);
        datos.put("message","Rol con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateRol(Rol rol, Long id) {
        datos = new HashMap<>();
        Optional<Rol> RolOpcional=this.rolRepository.findById(id);
        if(RolOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un rol con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Rol oldRol = RolOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldRol.setRol(rol.getRol());
        rolRepository.save(oldRol);
        datos.put("message","Rol con ID: "+id+" actualizado con exito.");
        datos.put("data",oldRol);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
