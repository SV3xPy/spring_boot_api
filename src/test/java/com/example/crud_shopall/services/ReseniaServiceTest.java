package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Cliente;
import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.Resenia;
import com.example.crud_shopall.repositories.ReseniaRepository;
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
class ReseniaServiceTest {
    @Mock
    private ReseniaRepository reseniaRepository;
    private Resenia resenia;
    @BeforeEach
    void setUp() {
        resenia = new Resenia();
        resenia.setId_resenia(1L);
        resenia.setResenia("La");
        resenia.setValoracion(11);
        //resenia.setVenta_detalle();
        resenia.setCliente(new Cliente());
        resenia.setProducto(new Producto());
    }
    @InjectMocks
    private ReseniaService reseniaService;
    @Test
    void getResenia() {
        when(reseniaRepository.findAll()).thenReturn(Arrays.asList(resenia));
        assertNotNull(reseniaService.getResenia());
    }

    @Test
    void newResenia() {
        when(reseniaRepository.save(any(Resenia.class))).thenReturn(resenia);
        assertNotNull(reseniaService.newResenia(resenia));
    }

    @Test
    void deleteResenia() {
        when(reseniaRepository.existsById(1L)).thenReturn(true);
        //Llamado al metodo a probar
        ResponseEntity<Object> response = reseniaService.deleteResenia(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        verify(reseniaRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateResenia() {
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(resenia));
        when(reseniaRepository.save(any(Resenia.class))).thenReturn(resenia);
        ResponseEntity<Object> response = reseniaService.updateResenia(resenia,1L);
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        verify(reseniaRepository, times(1)).save(resenia);
    }
}