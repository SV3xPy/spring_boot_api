package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.UsuarioRepository;
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
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setUsuario("Pinpon");
        usuario.setCorreo("Algo");
        usuario.setContrasena("lkkk");
        usuario.setToken("dddddd");
    }
    @InjectMocks
    private UsuarioService usuarioService;
    @Test
    void getUsuario() {
        //Configurar el comportamiento del repositorio
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));
        //Llamar al metodo que se va a probar
        List<Usuario> result = usuarioService.getUsuario();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que el metodo FindAll se haya llamado
        verify(usuarioRepository,times(1)).findAll();
    }

    @Test
    void newUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        ResponseEntity<Object> response = usuarioService.newUsuario(usuario);
        assertNotNull(usuarioService.newUsuario(usuario));
        //Verificar que de el mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }


    @Test
    void deleteUsuario() {
        //Configurar el comportamiento
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        //Llamar al metodo
        ResponseEntity<Object>response = usuarioService.deleteUsuario(1L);
        //Verificación
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo deleteById se haya llamado con el id adecuado
        verify(usuarioRepository,times(1)).deleteById(1L);
    }

    @Test
    void updateUsuario() {
        //Configurar el repositorio
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        //Llamar al metodo
        ResponseEntity<Object> response = usuarioService.updateUsuario(usuario,1L);
        //Verificamos
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo save se llamo adeuadamente
        verify(usuarioRepository,times(1)).save(usuario);
    }
}