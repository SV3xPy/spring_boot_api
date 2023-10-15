package com.example.crud_shopall.controllers;

import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.model.Empleado;
import com.example.crud_shopall.model.Tipo_pago;
import com.example.crud_shopall.services.EmpleadoService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/telefono")
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

    @PostMapping("/add")
    public Empleado addEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.setEmpleado(empleado);
    }

    @PutMapping("/update")
    public Empleado updateEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.setEmpleado(empleado);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmpleado(@PathVariable("id") Long id){
        this.empleadoService.deleteEmpleado(id);
    }
}
