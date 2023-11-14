package com.example.crud_shopall.services;

import com.example.crud_shopall.model.TipoPago;
import com.example.crud_shopall.repositories.TipoPagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoPagoServiceTest {
    @Mock
    private TipoPagoRepository tipoPagoRepository;
    private TipoPago tipoPago;
    @BeforeEach
    void setUp() {
        tipoPago = new TipoPago();
        tipoPago.setTipo_pago("Con lana");
        tipoPago.setId_tipo_pago(33L);
    }

    @InjectMocks
    private TipoPagoService tipoPagoService;
    @Test
    void getTipoPago() {
        when(tipoPagoRepository.findAll()).thenReturn(Arrays.asList(tipoPago));
        assertNotNull(tipoPagoService.getTipoPago());
    }

    @Test
    void addTipoPago() {
        when(tipoPagoRepository.save(any(TipoPago.class))).thenReturn(tipoPago);
        assertNotNull(tipoPagoService.addTipoPago(new TipoPago()));
    }

    @Test
    void updateTipoPago() {
        // Configurar el comportamiento del repositorio mock
        when(tipoPagoRepository.findById(33L)).thenReturn(Optional.of(tipoPago));
        when(tipoPagoRepository.save(any(TipoPago.class))).thenReturn(tipoPago);
        ResponseEntity<Object> response = tipoPagoService.updateTipoPago(tipoPago, 33L);

        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método save se haya llamado con el objeto correcto
        verify(tipoPagoRepository, times(1)).save(tipoPago);
    }

    @Test
    void deleteTipoPago() {
        when(tipoPagoRepository.existsById(33L)).thenReturn(true);
        ResponseEntity<Object> response = tipoPagoService.deleteTipoPago(33L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método deleteById se haya llamado con el ID correcto
        verify(tipoPagoRepository, times(1)).deleteById(33L);
    }
}