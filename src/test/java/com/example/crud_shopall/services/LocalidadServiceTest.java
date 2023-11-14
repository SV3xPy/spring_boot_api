package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Localidad;
import com.example.crud_shopall.model.Municipio;
import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.repositories.LocalidadRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalidadServiceTest {
    @Mock
    private LocalidadRepository localidadRepository;
    private Localidad localidad;
    @BeforeEach
    void setUp() {
        localidad = new Localidad();
        localidad.setId_localidad(1L);
        localidad.setLocalidad("Cabeza");
        localidad.setMunicipio(new Municipio());
        //localidad.setTiendas((List<Tienda>) new Tienda());
    }
    @InjectMocks
    private LocalidadService localidadService;
    @Test
    void getLocalidad() {
        //Configurar el comportamiento del repositorio mock
        when(localidadRepository.findAll()).thenReturn(Arrays.asList(localidad));
        //Llamar al metodo que se va a probar
        List<Localidad> result = localidadService.getLocalidad();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
    }

    @Test
    void newLocalidad() {
        when(localidadRepository.save(any(Localidad.class))).thenReturn(localidad);
        ResponseEntity<Object> response = localidadService.newLocalidad(localidad);
        assertNotNull(localidadService.newLocalidad(localidad));
        //El mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void updateLocalidad() {
        //Configurar el comportamiento del respositorio mock
        when(localidadRepository.findById(1L)).thenReturn(Optional.of(localidad));
        when(localidadRepository.save(any(Localidad.class))).thenReturn(localidad);
        //LLamar al metodo que se va a probar
        ResponseEntity<Object> response = localidadService.updateLocalidad(localidad,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que save se llamo con el objeto correcto
        verify(localidadRepository,times(1)).save(localidad);


        //
    }

    @Test
    void deleteLocalidad() {
        //Configurar el comportamiento del respositorio mock
        when(localidadRepository.existsById(1L)).thenReturn(true);
         //Llamar metodo a probar
        ResponseEntity<Object> response = localidadService.deleteLocalidad(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el borrado se realiza con el id adecuado
        verify(localidadRepository,times(1)).deleteById(1L);

    }
}