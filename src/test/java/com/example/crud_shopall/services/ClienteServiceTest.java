package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Cliente;
import com.example.crud_shopall.model.Sexo;
import com.example.crud_shopall.model.Telefono;
import com.example.crud_shopall.model.Usuario;
import com.example.crud_shopall.repositories.ClienteRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId_cliente(1L);
        cliente.setNombre("Juan");
        cliente.setPrimer_apellido("P");
        cliente.setSegundo_apellido("P");
        cliente.setNacimiento(new Date());
        cliente.setCurp("ccccc");
        cliente.setTelefono(new Telefono());
        cliente.setSexo(new Sexo());
        cliente.setUsuario(new Usuario());
    }
    @InjectMocks
    private ClienteService clienteService;
    @Test
    void getCliente() {
        //Configurar el comportamiento del repositorio mock
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        //Llamar al metodo a probar
        List<Cliente> result = clienteService.getCliente();
        //Verificar el resultado
        assertNotNull(result);
        assertEquals(1,result.size());
        //Verificar el llamado findAll
        verify(clienteRepository,times(1)).findAll();
    }

    @Test
    void newCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<Object> response = clienteService.newCliente(cliente);
        assertNotNull(clienteService.newCliente(cliente));
        //Verificacion del mensaje creado
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    void deleteCliente() {
        //Configurar el comportamiento del repositorio mock
        when(clienteRepository.existsById(1L)).thenReturn(true);

        //Llamar al metodo a probar
        ResponseEntity<Object> response = clienteService.deleteCliente(1L);
        //Verificar resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que el metodo deleteById se haya llamado con el ID correcto
        verify(clienteRepository,times(1)).deleteById(1L);
    }

    @Test
    void updateCliente() {
        //Configurar el comportamiento del repositorio mock
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        //Llamar el metodo a probar
        ResponseEntity<Object> response = clienteService.updateCliente(cliente,1L);
        //Verificar el resultado
        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        //Verificar que save se haya llamado con el objeto correcto
        verify(clienteRepository,times(1)).save(cliente);
    }
}