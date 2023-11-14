package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Estado;
import com.example.crud_shopall.model.Municipio;
import com.example.crud_shopall.repositories.MunicipioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MunicipioServiceTest {
    @Mock
    private MunicipioRepository municipioRepository;
    private Municipio muncipio;
    @BeforeEach
    void setUp() {
        muncipio = new Municipio();
        muncipio.setId_municipio(1L);
        muncipio.setEstado(new Estado());
        muncipio.setMunicipio("Lolo");
    }
    @InjectMocks
    private MunicipioService municipioService;

    @Test
    void getMunicipio() {
        when(municipioRepository.findAll()).thenReturn(Arrays.asList(muncipio));
        //Metodo a probar
        List<Municipio> result = municipioService.getMunicipio();
        //Verificar el resultado
        assertNotNull(result);
        verify(municipioRepository,times(1)).findAll();
    }

    @Test
    void newMunicipio() {
        when(municipioRepository.save(any(Municipio.class))).thenReturn(muncipio);
        ResponseEntity<Object> response = municipioService.newMunicipio(muncipio);
        assertNotNull(municipioService.newMunicipio(muncipio));
        //Verificar que de el mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void updateMunicipio() {
        //Configurar el comportamiento
        when(municipioRepository.findById(1L)).thenReturn(Optional.of(muncipio));
        when(municipioRepository.save(any(Municipio.class))).thenReturn(muncipio);
        //Llamar el metodo a probar
        ResponseEntity<Object> response = municipioService.updateMunicipio(muncipio,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        verify(municipioRepository,times(1)).save(muncipio);
    }

    @Test
    void deleteMunicipio() {
        //Configurar el comportamiento
        when(municipioRepository.existsById(1L)).thenReturn(true);

        //Llamar el metodo a probar
        ResponseEntity<Object> response = municipioService.deleteMunicipio(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que deleteById se llamo adecuadamente
        verify(municipioRepository,times(1)).deleteById(1L);
    }
}