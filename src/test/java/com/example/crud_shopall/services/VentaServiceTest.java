package com.example.crud_shopall.services;

import com.example.crud_shopall.model.*;
import com.example.crud_shopall.repositories.VentaRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {
    @Mock
    private VentaRepository ventaRepository;

    private Venta venta;
    @BeforeEach
    void setUp() {
        venta = new Venta();
        venta.setId_venta(1L);
        venta.setTienda(new Tienda());
        venta.setEmpleado(new Empleado());
        venta.setCliente(new Cliente());
        venta.setFecha(new Date());
        venta.setVenta_status(new VentaStatus());
    }
    @InjectMocks
    private VentaService ventaService;
    @Test
    void getVenta() {
        // Configurar el comportamiento del repositorio mock
        when(ventaRepository.findAll()).thenReturn(Arrays.asList(venta));
        // Llamar al método que se va a probar
        List<Venta> result = ventaService.getVenta();
        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        // Verificar que el método findAll se haya llamado
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void addVenta() {
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        ResponseEntity<Object> response = ventaService.addVenta(venta);
        assertNotNull(ventaService.addVenta(new Venta()));
        //Verificamos que de el mensaje de creado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Verificar que el método save se haya llamado con el objeto correcto
        verify(ventaRepository, times(1)).save(venta);
    }

    @Test
    void updateVenta() {
        // Configurar el comportamiento del repositorio mock
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        // Llamar al método que se va a probar
        ResponseEntity<Object> response = ventaService.updateVenta(venta, 1L);
        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método save se haya llamado con el objeto correcto
        verify(ventaRepository, times(1)).save(venta);
    }

    @Test
    void deleteVenta() {
        // Configurar el comportamiento del repositorio mock
        when(ventaRepository.existsById(1L)).thenReturn(true);

        // Llamar al método que se va a probar
        ResponseEntity<Object> response = ventaService.deleteVenta(1L);

        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método deleteById se haya llamado con el ID correcto
        verify(ventaRepository, times(1)).deleteById(1L);
    }
}