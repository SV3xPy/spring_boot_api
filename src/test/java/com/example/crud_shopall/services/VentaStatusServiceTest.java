package com.example.crud_shopall.services;

import com.example.crud_shopall.model.VentaStatus;
import com.example.crud_shopall.repositories.VentaStatusRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VentaStatusServiceTest {
    @Mock
    private VentaStatusRepository ventaStatusRepository;
    private VentaStatus ventaStatus;
    @BeforeEach
    void setUp() {
        ventaStatus = new VentaStatus();
        ventaStatus.setVenta_status("Finiquitado");
        //Long (66) esta depreciado ahora solo se coloca L de long al lado
        ventaStatus.setId_venta_status(666L);
    }
    @InjectMocks
    private VentaStatusService ventaStatusService;

    @Test
    void getVentaStatus() {
        when(ventaStatusRepository.findAll()).thenReturn(Arrays.asList(ventaStatus));
        assertNotNull(ventaStatusService.getVentaStatus());
    }

    @Test
    void newVentaStatus() {
        when(ventaStatusRepository.save(any(VentaStatus.class))).thenReturn(ventaStatus);
        assertNotNull(ventaStatusService.newVentaStatus(new VentaStatus()));
    }

    @Test
    void deleteVentaStatus() {
        when(ventaStatusRepository.existsById(666L)).thenReturn(true);

        //LLAMADO AL METODO A PROBAR
        ResponseEntity<Object> response = ventaStatusService.deleteVentaStatus(666L);
        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void updateVentaStatus() {
        // Configurar el comportamiento del repositorio mock
        when(ventaStatusRepository.findById(666L)).thenReturn(Optional.of(ventaStatus));
        when(ventaStatusRepository.save(any(VentaStatus.class))).thenReturn(ventaStatus);
        ResponseEntity<Object> response = ventaStatusService.updateVentaStatus(ventaStatus,666L);
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        // Verificaciones extra
        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseData = (Map<String, Object>) response.getBody();
        assertEquals("VentaStatus con ID: 666 actualizado con exito.", responseData.get("message"));
        //No funciona de la manera esperada, revisar el import
        //notify(ventaStatusRepository, times(1)).save(ventaStatus);
    }
}