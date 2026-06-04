package com.servitareas.servitareas.Unit;

import com.servitareas.servitareas.model.Tarea;
import com.servitareas.servitareas.repository.TareaRepository;
import com.servitareas.servitareas.service.impl.TareaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    private TareaServiceImpl tareaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tareaService = new TareaServiceImpl(tareaRepository);
    }

    @Test
    void listarTareasDebeRetornarLista() {

        Tarea t1 = new Tarea();
        t1.setTitulo("Diseñar interfaz");

        Tarea t2 = new Tarea();
        t2.setTitulo("Implementar backend");

        when(tareaRepository.findAll())
                .thenReturn(Arrays.asList(t1, t2));

        List<Tarea> tareas = tareaService.listarTareas();

        assertEquals(2, tareas.size());

        verify(tareaRepository, times(1))
                .findAll();
    }

    @Test
    void obtenerTareaPorIdDebeRetornarTarea() {

        Tarea tarea = new Tarea();
        tarea.setIdTarea(1L);
        tarea.setTitulo("ServiTareas");

        when(tareaRepository.findById(1L))
                .thenReturn(Optional.of(tarea));

        Optional<Tarea> resultado =
                tareaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("ServiTareas",
                resultado.get().getTitulo());
    }

    @Test
    void guardarTareaDebeGuardarCorrectamente() {

        Tarea tarea = new Tarea();
        tarea.setTitulo("Tarea Test");

        when(tareaRepository.save(tarea))
                .thenReturn(tarea);

        Tarea guardada =
                tareaService.guardarTarea(tarea);

        assertEquals("Tarea Test",
                guardada.getTitulo());

        verify(tareaRepository, times(1))
                .save(tarea);
    }

    @Test
    void eliminarTareaDebeLlamarDeleteById() {

        tareaService.eliminarTarea(1L);

        verify(tareaRepository, times(1))
                .deleteById(1L);
    }
}