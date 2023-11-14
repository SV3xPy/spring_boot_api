package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.repositories.SexoRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SexoServiceTest {

    @Mock
    private SexoRepository sexoRepository;
    private Sexo sexo;

    @BeforeEach
    void setUp() {
        sexo = new Sexo();
        sexo.setSexo("PruebaSexo");
        sexo.setId_sexo(1L);
    }

    @InjectMocks
    private SexoService sexoService;

    @Test
    void getSexo() {
        when(sexoRepository.findAll()).thenReturn(Arrays.asList(sexo));
        assertNotNull(sexoService.getSexo());
    }

    @Test
    void newSexo() {
        when(sexoRepository.save(any(Sexo.class))).thenReturn(sexo);
        assertNotNull(sexoService.newSexo(new Sexo()));
    }

    @Test
    void deleteSexo() {
        when(sexoRepository.existsById(1L)).thenReturn(true);
        ResponseEntity<Object> response = sexoService.deleteSexo(1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void updateSexo() {
        when(sexoRepository.findById(1L)).thenReturn(Optional.of(sexo));
        when(sexoRepository.save(any(Sexo.class))).thenReturn(sexo);
        ResponseEntity<Object> response = sexoService.updateSexo(sexo, 1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(sexoRepository, times(1)).save(sexo);
    }
}