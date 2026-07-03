package com.servitareas.servitareas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servitareas.servitareas.model.Tarea;
import com.servitareas.servitareas.repository.TareaRepository;
import com.servitareas.servitareas.service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }
    

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> obtenerPorId(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea actualizarTarea(Long id, Tarea tarea) {
        Tarea existente = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        // Actualizamos solo los campos permitidos
        existente.setTitulo(tarea.getTitulo());
        existente.setDescripcion(tarea.getDescripcion());
        existente.setFechaLimite(tarea.getFechaLimite());
        existente.setPrioridad(tarea.getPrioridad());
        existente.setEstado(tarea.getEstado());

        // Actualizamos las relaciones con otras tablas
        existente.setProyecto(tarea.getProyecto());
        existente.setUsuarioAsignado(tarea.getUsuarioAsignado());

        return tareaRepository.save(existente);
    }

    @Override
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public List<Tarea> listarPorEstado(String estado) {
        return tareaRepository.findByEstado(estado);
    }
}
