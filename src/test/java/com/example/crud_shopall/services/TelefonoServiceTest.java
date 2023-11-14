package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.repositories.TelefonoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelefonoServiceTest {
    @Mock
    private TelefonoRepository telefonoRepository;
    private Telefono telefono;

    @BeforeEach
    void setUp() {
        telefono = new Telefono();
        telefono.setId_telefono(1L);
        telefono.setTelefono("4641029380");
        telefono.setLada(new Lada());
    }

    @InjectMocks
    private TelefonoService telefonoService;

    @Test
    void getTelefono() {
        when(telefonoRepository.findAll()).thenReturn(Arrays.asList(telefono));
        List<Telefono> result = telefonoService.getTelefono();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(telefonoRepository, times(1)).findAll();
    }

    @Test
    void newTelefono() {
        when(telefonoRepository.save(any(Telefono.class))).thenReturn(telefono);
        ResponseEntity<Object> response = telefonoService.newTelefono(telefono);
        assertNotNull(telefonoService.newTelefono(new Telefono()));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(telefonoRepository, times(1)).save(telefono);
    }

    @Test
    void updateTelefono() {
        when(telefonoRepository.findById(1L)).thenReturn(Optional.of(telefono));
        when(telefonoRepository.save(any(Telefono.class))).thenReturn(telefono);
        ResponseEntity<Object> response = telefonoService.updateTelefono(telefono, 1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(telefonoRepository, times(1)).save(telefono);
    }

    @Test
    void deleteTelefono() {
        when(telefonoRepository.existsById(1L)).thenReturn(true);
        ResponseEntity<Object> response = telefonoService.deleteTelefono(1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(telefonoRepository, times(1)).deleteById(1L);
    }
}