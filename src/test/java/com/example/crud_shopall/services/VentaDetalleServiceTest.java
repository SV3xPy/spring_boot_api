package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Producto;
import com.example.crud_shopall.model.Resenia;
import com.example.crud_shopall.model.Venta;
import com.example.crud_shopall.model.VentaDetalle;
import com.example.crud_shopall.repositories.VentaDetalleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaDetalleServiceTest {
    @Mock
    private VentaDetalleRepository ventaDetalleRepository;
    private VentaDetalle ventaDetalle;
    @BeforeEach
    void setUp() {
        ventaDetalle = new VentaDetalle();
        ventaDetalle.setId_venta_detalle(1L);
        ventaDetalle.setVenta(new Venta());
        ventaDetalle.setProducto(new Producto());
        ventaDetalle.setCantidad(4);
        ventaDetalle.setPorcentaje_descuento(44.5);
        ventaDetalle.setFecha_envio(new Date());
        ventaDetalle.setFecha_entrega(new Date());
        ventaDetalle.setResenia(new Resenia());
    }
    @InjectMocks
    private VentaDetalleService ventaDetalleService;
    @Test
    void getVentaDetalle() {
        when(ventaDetalleRepository.findAll()).thenReturn(Arrays.asList(ventaDetalle));
        assertNotNull(ventaDetalleService.getVentaDetalle());
        verify(ventaDetalleRepository,times(1)).findAll();
    }

    @Test
    void newVentaDetalle() {
        when(ventaDetalleRepository.save(any(VentaDetalle.class))).thenReturn(ventaDetalle);
        ResponseEntity<Object> response = ventaDetalleService.newVentaDetalle(ventaDetalle);
        assertNotNull(ventaDetalleService.newVentaDetalle(ventaDetalle));
        //Verificamos que de el mensaje de creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void deleteVentaDetalle() {
        //Configurar el comportamiento del repositorio mock
        when(ventaDetalleRepository.existsById(1L)).thenReturn(true);
        //Llamar al metodo que se va a probar
        ResponseEntity<Object> response = ventaDetalleService.deleteVentaDetalle(1L);

        //Verificación del resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());

        //Verificar que el metodo deleteById se haya llamado con el ID correcto
        verify(ventaDetalleRepository,times(1)).deleteById(1L);
    }

    @Test
    void updateVentaDetalle() {
        //Configurar el comportamiento del repositorio mock
        when(ventaDetalleRepository.findById(1L)).thenReturn(Optional.of(ventaDetalle));
        when(ventaDetalleRepository.save(any(VentaDetalle.class))).thenReturn(ventaDetalle);
        //Llamado del metodo a probar
        ResponseEntity<Object> response = ventaDetalleService.updateVentaDetalle(ventaDetalle,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo save se llamo con el objecto correcto
        verify(ventaDetalleRepository,times(1)).save(ventaDetalle);
    }
}