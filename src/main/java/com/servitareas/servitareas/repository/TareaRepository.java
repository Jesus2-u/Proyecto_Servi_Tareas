package com.servitareas.servitareas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servitareas.servitareas.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByEstado(String estado);

}
