package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Marca;
import com.example.crud_shopall.repositories.MarcaRepository;
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

@ExtendWith(MockitoExtension.class)
class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;
    private Marca marca;

    @BeforeEach
    void setUp() {
        marca = new Marca();
        marca.setMarca("PruebaMarca");
        marca.setId_marca(123L);
    }

    @InjectMocks
    private MarcaService marcaService;

    @Test
    void getMarca() {
        when(marcaRepository.findAll()).thenReturn(Arrays.asList(marca));
        assertNotNull(marcaService.getMarca());
    }

    @Test
    void newMarca() {
        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);
        assertNotNull(marcaService.newMarca(new Marca()));
    }

    @Test
    void updateMarca() {
        // Configurar el comportamiento del repositorio mock
        when(marcaRepository.findById(123L)).thenReturn(Optional.of(marca));
        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);
        ResponseEntity<Object> response = marcaService.updateMarca(marca,123L);
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        // Verificaciones extra
        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseData = (Map<String, Object>) response.getBody();
        assertEquals("Se ha actualizado la marca.", responseData.get("Message"));
        assertNotNull(responseData.get("Data"));
    }

    @Test
    void deleteMarca() {
        when(marcaRepository.existsById(123L)).thenReturn(true);
        //LLAMANDO AL MÉTODO A PROBAR
        ResponseEntity<Object> response = marcaService.deleteMarca(123L);
        //VERIFICAR RESULTADO
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}