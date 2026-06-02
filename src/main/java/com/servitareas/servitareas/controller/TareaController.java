package com.servitareas.servitareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.servitareas.servitareas.model.Tarea;
import com.servitareas.servitareas.service.ProyectoService;
import com.servitareas.servitareas.service.TareaService;
import com.servitareas.servitareas.service.UsuarioService;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;
    private final ProyectoService proyectoService;
    private final UsuarioService usuarioService;

    public TareaController(
            TareaService tareaService,
            ProyectoService proyectoService,
            UsuarioService usuarioService) {

        this.tareaService = tareaService;
        this.proyectoService = proyectoService;
        this.usuarioService = usuarioService;
    }

    // =========================
    // LISTA
    // =========================
    @GetMapping
    public String listar(Model model) {

        model.addAttribute("tareas", tareaService.listarTareas());

        return "html/tareas";
    }

    // =========================
    // FORMULARIO NUEVO
    // =========================
    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("tarea", new Tarea());

        model.addAttribute("proyectos",
                proyectoService.listarProyectos());

        model.addAttribute("usuarios",
                usuarioService.listarUsuarios());

        return "html/tarea-formulario";
    }

    // =========================
    // FORMULARIO EDITAR
    // =========================
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Tarea tarea = tareaService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        model.addAttribute("tarea", tarea);

        model.addAttribute("proyectos",
                proyectoService.listarProyectos());

        model.addAttribute("usuarios",
                usuarioService.listarUsuarios());

        return "html/tarea-formulario";
    }

    // =========================
    // GUARDAR / ACTUALIZAR
    // =========================
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tarea tarea) {

        tareaService.guardarTarea(tarea);

        return "redirect:/tareas";
    }

    // =========================
    // ELIMINAR
    // =========================
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        tareaService.eliminarTarea(id);

        return "redirect:/tareas";
    }
}