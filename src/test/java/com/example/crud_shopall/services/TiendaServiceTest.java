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
class TiendaServiceTest {
    @Mock
    private TiendaRepository tiendaRepository;
    private Tienda tienda;
    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        tienda.setId_tienda(1L);
        tienda.setTienda("Papapum");
    }
    @InjectMocks
    private TiendaService tiendaService;

    @Test
    void getTienda() {
        //Configurar el comportamiento
        when(tiendaRepository.findAll()).thenReturn(Arrays.asList(tienda));
        //Metodo a probar
        List<Tienda> result = tiendaService.getTienda();
        //Verificar
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar que el metodo findAll se haya llamado
        verify(tiendaRepository,times(1)).findAll();
    }

    @Test
    void newTienda() {
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);
        ResponseEntity<Object> response = tiendaService.newTienda(tienda);
        assertNotNull(tiendaService.newTienda(tienda));
        //Verificar el mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void updateTienda() {
        //Configurar el repositorio
        when(tiendaRepository.findById(1L)).thenReturn(Optional.of(tienda));
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);
        //Llamar metodo
        ResponseEntity<Object> response = tiendaService.updateTienda(tienda,1L);
        //Verificar
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo se llamo adecuadamente
        verify(tiendaRepository,times(1)).save(tienda);
    }

    @Test
    void deleteTienda() {
        //Configurar el comportamiento
        when(tiendaRepository.existsById(1L)).thenReturn(true);
        //Llamar el metodo
        ResponseEntity<Object> response = tiendaService.deleteTienda(1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo se llamo adecuadamente
        verify(tiendaRepository,times(1)).deleteById(1L);
    }
}