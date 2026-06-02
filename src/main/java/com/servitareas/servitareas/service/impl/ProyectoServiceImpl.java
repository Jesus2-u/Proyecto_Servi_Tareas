package com.servitareas.servitareas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servitareas.servitareas.model.Proyecto;
import com.servitareas.servitareas.repository.ProyectoRepository;
import com.servitareas.servitareas.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<Proyecto> listarProyectos() {
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override
    public Proyecto guardarProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto actualizarProyecto(Long id, Proyecto proyecto) {
        Proyecto existente = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Actualizamos solo los datos editables
        existente.setNombre(proyecto.getNombre());
        existente.setDescripcion(proyecto.getDescripcion());
        existente.setFechaLimite(proyecto.getFechaLimite());
        existente.setEstado(proyecto.getEstado());
        
        // Actualizamos la relación con el usuario creador
        existente.setUsuarioCreador(proyecto.getUsuarioCreador());

        return proyectoRepository.save(existente);
    }

    @Override
    public void eliminarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }
}
