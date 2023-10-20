package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Empleado;
import com.example.crud_shopall.model.Tipo_pago;
import com.example.crud_shopall.repositories.EmpleadoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class EmpleadoService {

    HashMap<String,Object> datos;
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {

        this.empleadoRepository = empleadoRepository;
    }


    public List<Empleado> getEmpleado(){
        return (List<Empleado>) empleadoRepository.findAll();
    }

    public ResponseEntity<Object> addEmpleado(Empleado empleado){
        datos = new HashMap<>();
        this.empleadoRepository.save(empleado);
        datos.put("data",empleado);
        datos.put("message","Registro insertado con Éxito");
        return new ResponseEntity<>(
                empleado,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateEmpleado(Empleado empleado, Long id){
        datos = new HashMap<>();
        Optional<Empleado> empleadoOpcional=this.empleadoRepository.findById(id);
        if(empleadoOpcional.isEmpty()){
            datos.put("error",true);
            datos.put("message","No existe un tipo de pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        this.empleadoRepository.save(empleado);
        Empleado oldEmpleado = empleadoOpcional.get();
        //SE ACTUALIZA EL NOMBRE
        oldEmpleado.setPrimer_apellido(empleado.getPrimer_apellido());
        oldEmpleado.setSegundo_apellido(empleado.getSegundo_apellido());
        oldEmpleado.setNombre(empleado.getNombre());
        oldEmpleado.setRfc(empleado.getRfc());
        oldEmpleado.setNacimiento(empleado.getNacimiento());
        oldEmpleado.setCurp(empleado.getCurp());
        oldEmpleado.setTelefono(empleado.getTelefono());
        oldEmpleado.setUsuario(empleado.getUsuario());
        oldEmpleado.setSexo(empleado.getSexo());
        oldEmpleado.setTienda(empleado.getTienda());
        /*
        Los demás
        */

        empleadoRepository.save(oldEmpleado);
        datos.put("message","Tipo de pago con ID: "+id+" actualizado con exito.");
        datos.put("data",oldEmpleado);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object>  deleteEmpleado(Long id) {
        datos = new HashMap<>();
        boolean exists=this.empleadoRepository.existsById(id);

        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un empleado con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        this.empleadoRepository.deleteById(id);
        empleadoRepository.deleteById(id);
        datos.put("message","Empleado con ID: "+id+" eliminado con exito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}