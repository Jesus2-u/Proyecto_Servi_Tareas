package com.servitareas.servitareas.Unit;

import com.servitareas.servitareas.model.Proyecto;
import com.servitareas.servitareas.repository.ProyectoRepository;
import com.servitareas.servitareas.service.impl.ProyectoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    private ProyectoServiceImpl proyectoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proyectoService = new ProyectoServiceImpl(proyectoRepository);
    }

    @Test
    void listarProyectosDebeRetornarLista() {

        Proyecto p1 = new Proyecto();
        p1.setNombre("Sistema Ventas");

        Proyecto p2 = new Proyecto();
        p2.setNombre("ServiTareas");

        when(proyectoRepository.findAll())
                .thenReturn(Arrays.asList(p1, p2));

        List<Proyecto> proyectos = proyectoService.listarProyectos();

        assertEquals(2, proyectos.size());

        verify(proyectoRepository, times(1))
                .findAll();
    }

    @Test
    void obtenerProyectoPorIdDebeRetornarProyecto() {

        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1L);
        proyecto.setNombre("ServiTareas");

        when(proyectoRepository.findById(1L))
                .thenReturn(Optional.of(proyecto));

        Optional<Proyecto> resultado =
                proyectoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("ServiTareas",
                resultado.get().getNombre());
    }

    @Test
    void guardarProyectoDebeGuardarCorrectamente() {

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto Test");

        when(proyectoRepository.save(proyecto))
                .thenReturn(proyecto);

        Proyecto guardado =
                proyectoService.guardarProyecto(proyecto);

        assertEquals("Proyecto Test",
                guardado.getNombre());

        verify(proyectoRepository, times(1))
                .save(proyecto);
    }

    @Test
    void eliminarProyectoDebeLlamarDeleteById() {

        proyectoService.eliminarProyecto(1L);

        verify(proyectoRepository, times(1))
                .deleteById(1L);
    }
}