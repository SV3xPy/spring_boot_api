package com.example.crud_shopall.services;

import com.example.crud_shopall.model.VentaOperacion;
import com.example.crud_shopall.repositories.VentaOperacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class VentaOperacionServiceTest {

    @Mock
    private VentaOperacionRepository ventaOperacionRepository;
    private VentaOperacion ventaOperacion;
    @BeforeEach
    void setUp() {
        ventaOperacion = new VentaOperacion();
        ventaOperacion.setId_venta_operacion(77L);
        ventaOperacion.setFolio("Algo");
        ventaOperacion.setMonto(77);
        ventaOperacion.setAutorizado((byte) 1);
    }
    @InjectMocks
    private VentaOperacionService ventaOperacionService;
    @Test
    void getVenta_operacion() {
        when(ventaOperacionRepository.findAll()).thenReturn(Arrays.asList(ventaOperacion));
        assertNotNull(ventaOperacionService.getVenta_operacion());
    }

    @Test
    void addVenta_operacion() {
        when(ventaOperacionRepository.save(any(VentaOperacion.class))).thenReturn(ventaOperacion);
        assertNotNull(ventaOperacionService.addVenta_operacion(new VentaOperacion()));
    }

    @Test
    void updateVenta_operacion() {
    //Configurar el comportamiento del repositorio mock
        when(ventaOperacionRepository.findById(77L)).thenReturn(Optional.of(ventaOperacion));
        when(ventaOperacionRepository.save(any(VentaOperacion.class))).thenReturn(ventaOperacion);
        ResponseEntity<Object> response = ventaOperacionService.updateVenta_operacion(ventaOperacion,77L);
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
    }

    @Test
    void deleteVenta_operacion() {
        when(ventaOperacionRepository.existsById(77L)).thenReturn(true);
        ResponseEntity<Object> response = ventaOperacionService.deleteVenta_operacion(77L);
        //Verificar el estado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
    }
}