package com.servitareas.servitareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servitareas.servitareas.model.Proyecto;
import com.servitareas.servitareas.service.ProyectoService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.listarProyectos());
        return "proyecto/lista"; // Ruta de la vista HTML que crearán después
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyecto/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        return proyectoService.obtenerPorId(id)
                .map(proyecto -> {
                    model.addAttribute("proyecto", proyecto);
                    return "proyecto/formulario";
                })
                .orElse("redirect:/proyectos");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }
}