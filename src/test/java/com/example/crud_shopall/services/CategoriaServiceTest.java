package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Categoria;
import com.example.crud_shopall.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;
    private Categoria categoria;
    @BeforeEach
    void setup(){
        categoria = new Categoria();
        categoria.setCategoria("Mockitado");
        categoria.setId_categoria(166L);
    }
    @InjectMocks
    private CategoriaService categoriaService;
    @Test
    void getCategoria() {
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));
        assertNotNull(categoriaService.getCategoria());
    }

    @Test
    void newCategoria() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        assertNotNull(categoriaService.newCategoria(new Categoria()));
    }

    @Test
    void updateCategoria() {
        // Configurar el comportamiento del repositorio mock
        when(categoriaRepository.findById(166L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        ResponseEntity<Object> response = categoriaService.updateCategoria(categoria,166L);
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        // Verificaciones extra
        assertTrue(response.getBody() instanceof Map);
        Map<String, Object> responseData = (Map<String, Object>) response.getBody();
        assertEquals("Se ha actualizado la categoría.", responseData.get("Message"));
        assertNotNull(responseData.get("Data"));
    }

    @Test
    void deleteCategoria() {
        when(categoriaRepository.existsById(166L)).thenReturn(true);

        //LLAMADO AL METODO A PROBAR
        ResponseEntity<Object> response = categoriaService.deleteCategoria(166L);
        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

    }
}