package com.example.crud_shopall.services;

import com.example.crud_shopall.model.*;
import com.example.crud_shopall.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {
    @Mock
    private EmpleadoRepository empleadoRepository;
    private Empleado empleado;
    @BeforeEach
    void setUp() {
        empleado = new Empleado();
        empleado.setId_empleado(1L);
        empleado.setPrimer_apellido("La");
        empleado.setSegundo_apellido("Lo");
        empleado.setNombre("as");
        empleado.setRfc("222");
        empleado.setNacimiento("Revisar");
        empleado.setCurp("sss");
        empleado.setTelefono(new Telefono());
        empleado.setUsuario(new Usuario());
        empleado.setSexo(new Sexo());
        empleado.setTienda(new Tienda());
    }
    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void getEmpleado() {
        //Configurar el comportamiento del mock
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado));
        //Llamar el metodo a probar
        List<Empleado> result = empleadoService.getEmpleado();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que el metodo findAll se haya llamado
        verify(empleadoRepository,times(1)).findAll();
    }

    @Test
    void addEmpleado() {
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);
        ResponseEntity<Object> response = empleadoService.addEmpleado(empleado);
        assertNotNull(empleadoService.addEmpleado(empleado));
        //Verificacion del mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void updateEmpleado() {
        //Configurar el comportamiento del repositorio mock
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        //Llamar el metodo a probar
        ResponseEntity<Object> response = empleadoService.updateEmpleado(empleado,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());

    }

    @Test
    void deleteEmpleado() {
        //Configurar el comportamiento del repositorio mock
        when(empleadoRepository.existsById(1L)).thenReturn(true);
        // Llamar al metodo que se va a probar
        ResponseEntity<Object> response = empleadoService.deleteEmpleado(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}