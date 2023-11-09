package com.example.crud_shopall.services;


import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.repositories.RolPrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RolPrivilegioService {
    HashMap<String,Object> datos;
    private final RolPrivilegioRepository rolPrivilegioRepository;

    @Autowired
    public RolPrivilegioService(RolPrivilegioRepository rolPrivilegioRepository) {
        this.rolPrivilegioRepository = rolPrivilegioRepository;
    }

    public List<RolPrivilegio> getRolPrivilegio(){
        return this.rolPrivilegioRepository.findAll();
    }

    public ResponseEntity<Object> newRolPrivilegio(RolPrivilegio rolPrivilegio) {
        datos = new HashMap<>();
        rolPrivilegioRepository.save(rolPrivilegio);
        datos.put("data",rolPrivilegio);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                rolPrivilegio,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateRolPrivilegio(RolPrivilegio rolPrivilegio, Long id) {
        datos = new HashMap<>();
        Optional<RolPrivilegio> rolPrivilegioOpcional=this.rolPrivilegioRepository.findById(id);
        if(rolPrivilegioOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un rol_privilegio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        RolPrivilegio oldRolPrivilegio = rolPrivilegioOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldRolPrivilegio.setRol(rolPrivilegio.getRol());
        oldRolPrivilegio.setPrivilegio(rolPrivilegio.getPrivilegio());
        rolPrivilegioRepository.save(oldRolPrivilegio);
        datos.put("message","RolPrivilegio con ID: "+id+" actualizado con exito.");
        datos.put("data",oldRolPrivilegio);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    public ResponseEntity<Object> deleteRolPrivilegio(Long id) {
        datos = new HashMap<>();
        boolean exists=this.rolPrivilegioRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un rol_privilegio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        rolPrivilegioRepository.deleteById(id);
        datos.put("message","Rol_privilegio con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
