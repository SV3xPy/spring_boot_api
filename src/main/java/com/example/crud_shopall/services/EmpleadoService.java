package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
        import com.example.crud_shopall.model.Empleado;
import com.example.crud_shopall.repositories.EmpleadoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> getEmpleado(){ return this.empleadoRepository.findAll(); }

    public Empleado setEmpleado(Empleado empleado){
        return this.empleadoRepository.save(empleado);
    }

    public void deleteEmpleado(Long id_empleado) { this.empleadoRepository.deleteById(id_empleado); }
}