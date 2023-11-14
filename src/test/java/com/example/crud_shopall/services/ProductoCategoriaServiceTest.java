package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Categoria;
import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.ProductoCategoria;
import com.example.crud_shopall.repositories.ProductoCategoriaRepository;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoCategoriaServiceTest {

    @Mock
    private ProductoCategoriaRepository productoCategoriaRepository;

    private ProductoCategoria productoCategoria;

    private Categoria categoria1;
    private Categoria categoria2;
    private Categoria categoria3;

    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    @BeforeEach
    void setUp() {
        // Inicializa las categorías y productos con sus relaciones
        categoria1 = new Categoria(1L, "Categoria1", null);
        categoria2 = new Categoria(2L, "Categoria2", null);
        categoria3 = new Categoria(3L, "Categoria3", null);

        producto1 = new Producto(1L, "Producto1", null, 10.0, 5.0, "Descripción1", null);
        producto2 = new Producto(2L, "Producto2", null, 15.0, 8.0, "Descripción2", null);
        producto3 = new Producto(3L, "Producto3", null, 20.0, 12.0, "Descripción3", null);

        categoria1.setProductoCategoria(Arrays.asList(new ProductoCategoria(1L, producto1, categoria1)));
        categoria2.setProductoCategoria(Arrays.asList(new ProductoCategoria(2L, producto2, categoria2)));
        categoria3.setProductoCategoria(Arrays.asList(new ProductoCategoria(3L, producto3, categoria3)));

        producto1.setProductoCategoria(Arrays.asList(new ProductoCategoria(1L, producto1, categoria1)));
        producto2.setProductoCategoria(Arrays.asList(new ProductoCategoria(2L, producto2, categoria2)));
        producto3.setProductoCategoria(Arrays.asList(new ProductoCategoria(3L, producto3, categoria3)));
    }

    @InjectMocks
    private ProductoCategoriaService productoCategoriaService;

    @Test
    void getProductoCategoria() {
        // Simula la respuesta del repositorio con la lista ficticia
        List<ProductoCategoria> productoCategoriaList = Arrays.asList(
                new ProductoCategoria(1L, producto1, categoria1),
                new ProductoCategoria(2L, producto2, categoria2),
                new ProductoCategoria(3L, producto3, categoria3)
        );

        // Simula el comportamiento del repositorio
        when(productoCategoriaRepository.findAll()).thenReturn(productoCategoriaList);

        // Llama al método bajo prueba
        List<ProductoCategoria> result = productoCategoriaService.getProductoCategoria();

        // Verifica que el resultado sea el esperado
        assertEquals(productoCategoriaList.size(), result.size());
        // Puedes agregar más aserciones según la lógica específica de tu servicio.
    }

    @Test
    void newProductoCategoria() {
        // Simula el comportamiento del repositorio
        when(productoCategoriaRepository.save(any())).thenReturn(new ProductoCategoria());

        // Crea una instancia de ProductoCategoria para la prueba
        ProductoCategoria productoCategoria = new ProductoCategoria();
        productoCategoria.setProducto(new Producto());
        productoCategoria.setCategoria(new Categoria());

        // Llama al método bajo prueba
        ResponseEntity<Object> response = productoCategoriaService.newProductoCategoria(productoCategoria);

        // Verifica que la respuesta sea la esperada
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));
        assertFalse(responseData.containsKey("Error"));
    }

    @Test
    void updateProductoCategoria() {
        // Simula la existencia del ProductoCategoria en el repositorio
        when(productoCategoriaRepository.findById(any())).thenReturn(Optional.of(new ProductoCategoria()));

        // Simula el comportamiento del repositorio
        when(productoCategoriaRepository.save(any())).thenReturn(new ProductoCategoria());

        // Crea una instancia de ProductoCategoria para la prueba
        ProductoCategoria productoCategoria = new ProductoCategoria();
        productoCategoria.setProducto(producto1);
        productoCategoria.setCategoria(categoria1);

        // Llama al método bajo prueba
        ResponseEntity<Object> response = productoCategoriaService.updateProductoCategoria(productoCategoria, 1L);

        // Verifica que la respuesta sea la esperada
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));
        assertTrue(responseData.containsKey("Data"));

    }

    @Test
    void deleteProductoCategoria() {
        // Simula la existencia del ProductoCategoria en el repositorio
        when(productoCategoriaRepository.existsById(any())).thenReturn(true);

        // Crea una instancia de ProductoCategoria para la prueba
        Long idToDelete = 1L;

        // Llama al método bajo prueba
        ResponseEntity<Object> response = productoCategoriaService.deleteProductoCategoria(idToDelete);

        // Verifica que la respuesta sea la esperada
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<?, ?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));

        // Verifica que se llamó al método deleteById del repositorio con el ID correcto
        verify(productoCategoriaRepository, times(1)).deleteById(idToDelete);
    }
}