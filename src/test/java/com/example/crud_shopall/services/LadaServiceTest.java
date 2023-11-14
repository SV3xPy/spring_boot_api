package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Lada;
import com.example.crud_shopall.repositories.LadaRepository;
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
class LadaServiceTest {

    @Mock
    private LadaRepository ladaRepository;

    private Lada lada;

    @BeforeEach
    void setUp() {
        lada = new Lada();
        lada.setId_lada(1L);
        lada.setLada(12);
    }

    @InjectMocks
    private LadaService ladaService;

    @Test
    void getLada() {
        when(ladaRepository.findAll()).thenReturn(Arrays.asList(lada));
        assertNotNull((ladaService.getLada()));
    }

    @Test
    void newLada() {
        when(ladaRepository.save(any(Lada.class))).thenReturn(lada);
        assertNotNull(ladaService.newLada(new Lada()));
    }

    @Test
    void deleteLada() {
        when(ladaRepository.existsById(1L)).thenReturn(true);
        ResponseEntity<Object> response = ladaService.deleteLada(1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void updateLada() {
        // Configura el comportamiento del repositorio para devolver una Lada existente al buscar por ID
        when(ladaRepository.findById(1L)).thenReturn(Optional.of(lada));

        // Configura el comportamiento del repositorio para devolver la Lada actualizada al guardar
        when(ladaRepository.save(any(Lada.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llama al método bajo prueba
        Lada updatedLada = new Lada();
        updatedLada.setId_lada(1L);  // Asegúrate de configurar el ID correctamente
        updatedLada.setLada(34);
        ResponseEntity<Object> response = ladaService.updateLada(updatedLada, 1L);

        // Verifica que la respuesta sea la esperada
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("message"));
        assertTrue(responseData.containsKey("data"));

        // Verifica que la Lada se haya actualizado correctamente
        Lada resultLada = (Lada) responseData.get("data");
        assertNotNull(resultLada);
        assertEquals(updatedLada.getLada(), resultLada.getLada());
    }
}