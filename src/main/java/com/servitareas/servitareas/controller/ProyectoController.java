package com.servitareas.servitareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.servitareas.servitareas.model.Proyecto;
import com.servitareas.servitareas.service.ProyectoService;
import com.servitareas.servitareas.service.UsuarioService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final UsuarioService usuarioService;

    public ProyectoController(
            ProyectoService proyectoService,
            UsuarioService usuarioService) {

        this.proyectoService = proyectoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.listarProyectos());
        return "html/proyectos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());

        return "html/proyecto-formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Proyecto proyecto = proyectoService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());

        return "html/proyecto-formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {

        proyectoService.guardarProyecto(proyecto);

        return "redirect:/proyectos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        proyectoService.eliminarProyecto(id);

        return "redirect:/proyectos";
    }
}