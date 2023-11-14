package com.example.crud_shopall.services;

import com.example.crud_shopall.model.Privilegio;
import com.example.crud_shopall.model.ProductoCategoria;
import com.example.crud_shopall.model.Rol;
import com.example.crud_shopall.model.RolPrivilegio;
import com.example.crud_shopall.repositories.RolPrivilegioRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolPrivilegioServiceTest {
    @Mock
    private RolPrivilegioRepository rolPrivilegioRepository;
    private RolPrivilegio rolPrivilegio;
    private Rol rol1;
    private Rol rol2;
    private Rol rol3;

    private Privilegio privilegio1;
    private Privilegio privilegio2;
    private Privilegio privilegio3;

    @BeforeEach
    void setUp() {
        rol1 = new Rol(1L, "Rol1", null, null);
        rol2 = new Rol(2L, "Rol2", null, null);
        rol3 = new Rol(3L, "Rol3", null, null);

        privilegio1 = new Privilegio(1L, "Privilegio1", null);
        privilegio2 = new Privilegio(2L, "Privilegio2", null);
        privilegio3 = new Privilegio(3L, "Privilegio3", null);

        rol1.setRolPrivilegios(Arrays.asList(new RolPrivilegio(1l, rol1, privilegio1)));
        rol2.setRolPrivilegios(Arrays.asList(new RolPrivilegio(2l, rol2, privilegio2)));
        rol3.setRolPrivilegios(Arrays.asList(new RolPrivilegio(3l, rol3, privilegio3)));

        privilegio1.setRolPrivilegios(Arrays.asList(new RolPrivilegio(1L, rol1, privilegio1)));
        privilegio2.setRolPrivilegios(Arrays.asList(new RolPrivilegio(2L, rol2, privilegio2)));
        privilegio3.setRolPrivilegios(Arrays.asList(new RolPrivilegio(3L, rol3, privilegio3)));
    }

    @InjectMocks
    private RolPrivilegioService rolPrivilegioService;

    @Test
    void getRolPrivilegio() {
        List<RolPrivilegio> rolPrivilegioList = Arrays.asList(
                new RolPrivilegio(1L, rol1, privilegio1),
                new RolPrivilegio(2L, rol2, privilegio2),
                new RolPrivilegio(3L, rol3, privilegio3)
        );

        when(rolPrivilegioRepository.findAll()).thenReturn(rolPrivilegioList);
        List<RolPrivilegio> result = rolPrivilegioService.getRolPrivilegio();
        assertEquals(rolPrivilegioList.size(), result.size());

    }

    @Test
    void newRolPrivilegio() {
        when(rolPrivilegioRepository.save(any())).thenReturn(new RolPrivilegio());
        RolPrivilegio rolPrivilegio = new RolPrivilegio();
        rolPrivilegio.setPrivilegio(new Privilegio());
        rolPrivilegio.setRol(new Rol());
        ResponseEntity<Object> response = rolPrivilegioService.newRolPrivilegio(rolPrivilegio);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateRolPrivilegio() {
        when(rolPrivilegioRepository.findById(any())).thenReturn(Optional.of(new RolPrivilegio()));
        when(rolPrivilegioRepository.save(any())).thenReturn(new RolPrivilegio());
        RolPrivilegio rolPrivilegio = new RolPrivilegio();
        rolPrivilegio.setPrivilegio(new Privilegio());
        rolPrivilegio.setRol(new Rol());
        ResponseEntity<Object> response = rolPrivilegioService.updateRolPrivilegio(rolPrivilegio, 1L);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    void deleteRolPrivilegio() {
        when(rolPrivilegioRepository.existsById(any())).thenReturn(true);
        Long idToDelete = 1L;
        ResponseEntity<Object> response = rolPrivilegioService.deleteRolPrivilegio(idToDelete);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}