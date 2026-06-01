package com.servitareas.servitareas.service;

import java.util.List;
import java.util.Optional;

import com.servitareas.servitareas.model.Proyecto;

public interface ProyectoService {

    List<Proyecto> listarProyectos();

    Optional<Proyecto> obtenerPorId(Long id);

    Proyecto guardarProyecto(Proyecto proyecto);

    Proyecto actualizarProyecto(Long id, Proyecto proyecto);

    void eliminarProyecto(Long id);
}
