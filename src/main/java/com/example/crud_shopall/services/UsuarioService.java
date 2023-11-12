package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    HashMap<String,Object> datos;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuario(){
        return this.usuarioRepository.findAll();
    }

    public ResponseEntity<Object> newUsuario(Usuario usuario) {
        datos = new HashMap<>();
        usuarioRepository.save(usuario);
        datos.put("data",usuario);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                usuario,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteUsuario(Long id) {
        datos = new HashMap<>();
        boolean exists=this.usuarioRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un usuario con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuarioRepository.deleteById(id);
        datos.put("message","Usuario con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updateUsuario(Usuario usuario, Long id) {
        datos = new HashMap<>();
        Optional<Usuario> UsuarioOpcional=this.usuarioRepository.findById(id);
        if(UsuarioOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un usuario con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Usuario oldUsuario = UsuarioOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldUsuario.setUsuario(usuario.getUsuario());
        oldUsuario.setCorreo(usuario.getCorreo());
        oldUsuario.setContrasena(usuario.getContrasena());
        oldUsuario.setToken(usuario.getToken());
        usuarioRepository.save(oldUsuario);
        datos.put("message","Usuario con ID: "+id+" actualizado con exito.");
        datos.put("data",oldUsuario);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> getUsuarioRoles(Long id){
        datos = new HashMap<>();
        boolean exists=this.usuarioRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un usuario con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        } else {
            List<Rol> rolesDeUsuario = usuarioRepository.findRolesByUsuarioId(id);
            return new ResponseEntity<>(
                    rolesDeUsuario,
                    HttpStatus.ACCEPTED
            );
        }
    }

}
