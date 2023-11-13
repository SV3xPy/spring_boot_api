package com.example.crud_shopall.services;

import com.example.crud_shopall.model.UsuarioRol;
import com.example.crud_shopall.repositories.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRolService {
    HashMap<String,Object> datos;
    private final UsuarioRolRepository usuarioRolRepository;

    @Autowired
    public UsuarioRolService(UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
    }

    public List<UsuarioRol> getUsuarioRol(){
        return this.usuarioRolRepository.findAll();
    }

    public ResponseEntity<Object> newUsuarioRol(UsuarioRol usuarioRol) {
        datos = new HashMap<>();
        usuarioRolRepository.save(usuarioRol);
        datos.put("data",usuarioRol);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                usuarioRol,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateUsuarioRol(UsuarioRol usuarioRol, Long id) {
        datos = new HashMap<>();
        Optional<UsuarioRol> suarioRolOpcional=this.usuarioRolRepository.findById(id);
        if(suarioRolOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un usuario_rol con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        UsuarioRol oldUsuarioRol = suarioRolOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldUsuarioRol.setUsuario(usuarioRol.getUsuario());
        oldUsuarioRol.setRol(usuarioRol.getRol());
        usuarioRolRepository.save(oldUsuarioRol);
        datos.put("message","UsuarioRol con ID: "+id+" actualizado con exito.");
        datos.put("data",oldUsuarioRol);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    public ResponseEntity<Object> deleteUsuarioRol(Long id) {
        datos = new HashMap<>();
        boolean exists=this.usuarioRolRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un usuario_rol con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuarioRolRepository.deleteById(id);
        datos.put("message","Usuario_rol con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
