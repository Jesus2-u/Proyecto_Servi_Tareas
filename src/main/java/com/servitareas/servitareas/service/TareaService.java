package com.servitareas.servitareas.service;

import java.util.List;
import java.util.Optional;

import com.servitareas.servitareas.model.Tarea;

public interface TareaService {

    List<Tarea> listarTareas();

    Optional<Tarea> obtenerPorId(Long id);

    Tarea guardarTarea(Tarea tarea);

    Tarea actualizarTarea(Long id, Tarea tarea);

    void eliminarTarea(Long id);

    List<Tarea> listarPorEstado(String estado);

}