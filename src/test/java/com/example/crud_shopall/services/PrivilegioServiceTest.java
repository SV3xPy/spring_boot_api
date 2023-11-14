package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.repositories.PrivilegioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrivilegioServiceTest {
    @Mock
    private PrivilegioRepository privilegioRepository;
    private Privilegio privilegio;

    @BeforeEach
    void setUp() {
        privilegio = new Privilegio();
        privilegio.setPrivilegio("PruebaPrivilegio");
        privilegio.setId_privilegio(1L);
    }

    @InjectMocks
    private PrivilegioService privilegioService;

    @Test
    void getPrivilegio() {
        when(privilegioRepository.findAll()).thenReturn(Arrays.asList(privilegio));
        assertNotNull(privilegioService.getPrivilegio());
    }

    @Test
    void newPrivilegio() {
        when(privilegioRepository.save(any(Privilegio.class))).thenReturn(privilegio);
        assertNotNull(privilegioService.newPrivilegio(new Privilegio()));
    }

    @Test
    void deletePrivilegio() {
        when(privilegioRepository.existsById(1L)).thenReturn(true);
        ResponseEntity<Object> response = privilegioService.deletePrivilegio(1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void updatePrivilegio() {
        when(privilegioRepository.findById(1L)).thenReturn(Optional.of(privilegio));
        when(privilegioRepository.save(any(Privilegio.class))).thenReturn(privilegio);
        ResponseEntity<Object> response = privilegioService.updatePrivilegio(privilegio, 1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(privilegioRepository, times(1)).save(privilegio);
    }
}