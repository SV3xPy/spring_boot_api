package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.repositories.EstadoRepository;
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
class EstadoServiceTest {
    @Mock
    private EstadoRepository estadoRepository;
    private Estado estado;
    @BeforeEach
    void setUp() {
        estado = new Estado();
        estado.setEstado("Ess");
        estado.setId_estado(1L);
    }
    @InjectMocks
    private EstadoService estadoService;
    @Test
    void getEstado() {
        //Configurar el comportamiento
        when(estadoRepository.findAll()).thenReturn(Arrays.asList(estado));
        //Llamar al metodo que se va a probar
        List<Estado> result = estadoService.getEstado();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que el metodo findAll se haya llamado
        verify(estadoRepository,times(1)).findAll();
    }

    @Test
    void newEstado() {
        when(estadoRepository.save(any(Estado.class))).thenReturn(estado);
        ResponseEntity<Object> response = estadoService.newEstado(estado);
        //Verificar creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        //Verificar que save se haya llamado con el objeto adecuado
        verify(estadoRepository,times(1)).save(estado);
    }

    @Test
    void deleteEstado() {
        //Configurar el comportamiento
        when(estadoRepository.existsById(1L)).thenReturn(true);

        //Llamar al metodo que se va a probar
        ResponseEntity<Object> response = estadoService.deleteEstado(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo deleteById se haya llamado con el ID adecuado
        verify(estadoRepository,times(1)).deleteById(1L);
    }

    @Test
    void updateEstado() {
        //Configurar el comportamiento del repositorio
        when(estadoRepository.findById(1L)).thenReturn(Optional.of(estado));
        when(estadoRepository.save(any(Estado.class))).thenReturn(estado);
        //Llamar al metodo a probar
        ResponseEntity<Object> response = estadoService.updateEstado(estado,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo save se llamo con el objeto adecuado
        verify(estadoRepository,times(1)).save(estado);
    }
}