package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.Empleado;
import com.example.crud_shopall.model.Tipo_pago;
import com.example.crud_shopall.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/all")
    public List<Empleado> getEmpleado(){
        return this.empleadoService.getEmpleado();
    }

    @PostMapping
    public ResponseEntity<Object> addEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.addEmpleado(empleado);
    }

    @PutMapping(path = "{empleadoId}")
    public ResponseEntity<Object> updateEmpleado(@RequestBody Empleado empleado, @PathVariable("empleadoId") Long id){
        return this.empleadoService.updateEmpleado(empleado, id);
    }

    @DeleteMapping(path = "{empleadoId}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable("empleadoId") Long id){
        return this.empleadoService.deleteEmpleado(id);
    }
}
