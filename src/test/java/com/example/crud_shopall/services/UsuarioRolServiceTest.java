package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.model.UsuarioRol;
import com.example.crud_shopall.repositories.UsuarioRolRepository;
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
class UsuarioRolServiceTest {
    @Mock
    private UsuarioRolRepository usuarioRolRepository;
    private UsuarioRol usuarioRol;
    @BeforeEach
    void setUp() {
        usuarioRol = new UsuarioRol();
        usuarioRol.setId_usuario_rol(1);
        usuarioRol.setUsuario(new Usuario());
        usuarioRol.setRol(new Rol());
    }
    @InjectMocks
    private UsuarioRolService usuarioRolService;
    @Test
    void getUsuarioRol() {
        //Configurar el comportamiento
        when(usuarioRolRepository.findAll()).thenReturn(Arrays.asList(usuarioRol));
        //Llamar el metodo a probar
        List<UsuarioRol> result = usuarioRolService.getUsuarioRol();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que findAll se llamo
        verify(usuarioRolRepository,times(1)).findAll();
    }

    @Test
    void newUsuarioRol() {
        when(usuarioRolRepository.save(any(UsuarioRol.class))).thenReturn(usuarioRol);
        ResponseEntity<Object> response = usuarioRolService.newUsuarioRol(usuarioRol);
        assertNotNull(usuarioRolService.newUsuarioRol(usuarioRol));
        //Verificar el mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void updateUsuarioRol() {
        //Configurar el comportamiento
        when(usuarioRolRepository.findById(1L)).thenReturn(Optional.of(usuarioRol));
        when(usuarioRolRepository.save(any(UsuarioRol.class))).thenReturn(usuarioRol);
        //Llamar el metodo a probar
        ResponseEntity<Object> response = usuarioRolService.updateUsuarioRol(usuarioRol,1L);
        //Verificar
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar el metodo save
        verify(usuarioRolRepository,times(1)).save(usuarioRol);
    }

    @Test
    void deleteUsuarioRol() {
        //Configurar el comportamiento
        when(usuarioRolRepository.existsById(1L)).thenReturn(true);
        //Llamar al metodo a probar
        ResponseEntity<Object> response = usuarioRolService.deleteUsuarioRol(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo deleteById se llamo adecuadamente
        verify(usuarioRolRepository,times(1)).deleteById(1L);
    }
}