package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegioService {

    HashMap<String,Object> datos;
    private final PrivilegioRepository privilegioRepository;

    @Autowired
    public PrivilegioService(PrivilegioRepository privilegioRepository) {
        this.privilegioRepository = privilegioRepository;
    }

    public List<Privilegio> getPrivilegio(){
        return this.privilegioRepository.findAll();
    }

    public ResponseEntity<Object> newPrivilegio(Privilegio privilegio) {
        datos = new HashMap<>();
        privilegioRepository.save(privilegio);
        datos.put("data",privilegio);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                privilegio,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deletePrivilegio(Long id) {
        datos = new HashMap<>();
        boolean exists=this.privilegioRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un privilegio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        privilegioRepository.deleteById(id);
        datos.put("message","Privilegio con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

    public ResponseEntity<Object> updatePrivilegio(Privilegio privilegio, Long id) {
        datos = new HashMap<>();
        Optional<Privilegio> privilegioOpcional=this.privilegioRepository.findById(id);
        if(privilegioOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un privilegio con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        Privilegio oldPrivilegio = privilegioOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldPrivilegio.setPrivilegio(privilegio.getPrivilegio());
        privilegioRepository.save(oldPrivilegio);
        datos.put("message","Privilegio con ID: "+id+" actualizado con exito.");
        datos.put("data",oldPrivilegio);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
