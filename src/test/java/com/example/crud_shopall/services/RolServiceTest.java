package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.repositories.RolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {
    @Mock
    private RolRepository rolRepository;
    private Rol rol;
    @BeforeEach
    void setUp() {
        rol = new Rol();
        rol.setId_rol(1L);
        rol.setRol("algo");
    }
    @InjectMocks
    private RolService rolService;

    @Test
    void getRol() {
        //Configurar el comportamiento
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rol));
        //Llamar el metodo a probar
        List<Rol> result = rolService.getRol();
        //Verificar
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que el findAll se llamo adecuadamente
        verify(rolRepository,times(1)).findAll();
    }

    @Test
    void newRol() {
        when(rolRepository.save(any(Rol.class))).thenReturn(rol);
        ResponseEntity<Object> response = rolService.newRol(rol);
        assertNotNull(rolService.newRol(rol));
        //Verifcar el mensaje
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void deleteRol() {
        //Configurar el comportamiento del repositorio
        when(rolRepository.existsById(1L)).thenReturn(true);
        //Metodo a probar
        ResponseEntity<Object> response = rolService.deleteRol(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo deleteById se haya llamado con el ID correcto
        verify(rolRepository,times(1)).deleteById(1L);
    }

    @Test
    void updateRol() {
        //Configurar el repositorio
        when(rolRepository.findById(1L)).thenReturn(Optional.of(rol));
        when(rolRepository.save(any(Rol.class))).thenReturn(rol);
        //Llamar al metodo
        ResponseEntity<Object> response = rolService.updateRol(rol,1L);
        //Verificar
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo sav se haya llamado adecuadamente
        verify(rolRepository,times(1)).save(rol);
    }
}