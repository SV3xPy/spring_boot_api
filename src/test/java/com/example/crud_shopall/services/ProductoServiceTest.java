package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Marca;
import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.Venta;
import com.example.crud_shopall.repositories.ProductoRepository;
import com.example.crud_shopall.repositories.MarcaRepository;
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
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private MarcaRepository marcaRepository;
    private Producto producto;
    private Marca marca;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId_producto(1L);
        producto.setProducto("Producto");
        producto.setMarca(new Marca());
        producto.setPrecio_referencia(1.1);
        producto.setCosto_referencia(2.2);
        producto.setDescripcion("Descripción");
    }


    @InjectMocks
    private ProductoService productoService;

    @Test
    void getProducto() {
        //Comportamiento
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        //Método a probar
        List<Producto> result = productoService.getProducto();
        //Verificar resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        //Verificar llamada del método
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void newProducto() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        ResponseEntity<Object> response = productoService.newProducto(producto);
        assertNotNull(productoService.newProducto(new Producto()));
        //Verificamos que de el mensaje de creado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Verificar que el método save se haya llamado con el objeto correcto
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void updateProducto() {
        // Configurar el comportamiento del repositorio mock
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        // Llamar al método que se va a probar
        ResponseEntity<Object> response = productoService.updateProducto(producto, 1L);
        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método save se haya llamado con el objeto correcto
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void deleteProducto() {
        // Configurar el comportamiento del repositorio mock
        when(productoRepository.existsById(1L)).thenReturn(true);

        // Llamar al método que se va a probar
        ResponseEntity<Object> response = productoService.deleteProducto(1L);

        // Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        // Verificar que el método deleteById se haya llamado con el ID correcto
        verify(productoRepository, times(1)).deleteById(1L);
    }
}