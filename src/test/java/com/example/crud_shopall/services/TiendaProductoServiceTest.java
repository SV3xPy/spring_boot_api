package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.Tienda;
import com.example.crud_shopall.model.TiendaProducto;
import com.example.crud_shopall.repositories.TiendaProductoRepository;
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
class TiendaProductoServiceTest {
    @Mock
    private TiendaProductoRepository tiendaProductoRepository;
    private TiendaProducto tiendaProducto;

    private Tienda tienda1;
    private Tienda tienda2;
    private Tienda tienda3;

    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    @BeforeEach
    void setUp(){
        //se inicializan las tiendas y productos con sus relaciones
        tienda1 = new Tienda(1L,"Lepastor",null,null);
        tienda2 = new Tienda(2L,"Mono",null,null);
        tienda3 = new Tienda(3L,"Algo",null,null);
        producto1 = new Producto(1L, "Producto1", null, 10.0, 5.0, "Descripción1", null,null);
        producto2 = new Producto(2L, "Producto2", null, 15.0, 8.0, "Descripción2", null,null);
        producto3 = new Producto(3L, "Producto3", null, 20.0, 12.0, "Descripción3", null,null);
        tienda1.setTiendaProducto(Arrays.asList(new TiendaProducto(1L,15,producto1,tienda1)));
        tienda2.setTiendaProducto(Arrays.asList(new TiendaProducto(2L,15,producto2,tienda2)));
        tienda3.setTiendaProducto(Arrays.asList(new TiendaProducto(3L,15,producto3,tienda3)));
    }
    @InjectMocks
    private TiendaProductoService tiendaProductoService;

    @Test
    void getTiendaProducto() {
        //Simulacion de la respuesta con la lista ficticia
        List<TiendaProducto> tiendaProductoList = Arrays.asList(
                new TiendaProducto(1L,15,producto1,tienda1),
                new TiendaProducto(2L,15,producto2,tienda2)
        );
        when(tiendaProductoRepository.findAll()).thenReturn(tiendaProductoList);
        //Lamado al metodo
        List<TiendaProducto> result = tiendaProductoService.getTiendaProducto();
        //Verificar el resultado
        assertEquals(tiendaProductoList.size(),result.size());
    }

    @Test
    void newTiendaProducto() {
        //Simular comportamiento
        when(tiendaProductoRepository.save(any())).thenReturn(new TiendaProducto()) ;
        //Crear una instancia de TiendaProducto para la prueba
        TiendaProducto tiendaProducto = new TiendaProducto();
        tiendaProducto.setProducto(producto1);
        tiendaProducto.setTienda(tienda1);

        //Llamar al metodo a probar
        ResponseEntity<Object> response = tiendaProductoService.newTiendaProducto(tiendaProducto);
        //Verificar que la respuesta es la correcta
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<?,?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));
        assertFalse(responseData.containsKey("Error"));
    }

    @Test
    void updateTiendaProducto() {
        //Simula la existencia dentro del repositorio
        when(tiendaProductoRepository.findById(any())).thenReturn(Optional.of(new TiendaProducto()));

        //Simula el repositorio
        when(tiendaProductoRepository.save(any())).thenReturn(new TiendaProducto());

        //Crear una instancia
        TiendaProducto tiendaProducto = new TiendaProducto();
        tiendaProducto.setTienda(tienda1);
        tiendaProducto.setProducto(producto1);
        //Llamado al metodo
        ResponseEntity<Object> response = tiendaProductoService.updateTiendaProducto(tiendaProducto,1L);
        //Verificar la respuestaa
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<?,?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));
        assertTrue(responseData.containsKey("Data"));
    }

    @Test
    void deleteTiendaProducto() {
        //simila a existencia de Tienda producto
        when(tiendaProductoRepository.existsById(any())).thenReturn(true);
        //Crear una instancia de prueba
        Long idToDelete = 1L;
        //Llamar al metodo
        ResponseEntity<Object> response = tiendaProductoService.deleteTiendaProducto(idToDelete);

        //Verificar respuesta
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<?,?> responseData = (Map<?, ?>) response.getBody();
        assertTrue(responseData.containsKey("Message"));
        //Verificar que se llamo al metodo con el id adecuado
        verify(tiendaProductoRepository,times(1)).deleteById(idToDelete);
    }
}