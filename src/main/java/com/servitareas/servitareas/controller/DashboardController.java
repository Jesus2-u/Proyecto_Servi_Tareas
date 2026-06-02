package com.servitareas.servitareas.controller;

import com.servitareas.servitareas.service.ProyectoService;
import com.servitareas.servitareas.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProyectoService proyectoService;
    private final TareaService tareaService;

    public DashboardController(ProyectoService proyectoService, TareaService tareaService) {
        this.proyectoService = proyectoService;
        this.tareaService = tareaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalProyectos", proyectoService.listarProyectos().size());
        model.addAttribute("totalTareas", tareaService.listarTareas().size());

        model.addAttribute("tareasPendientes",
                tareaService.listarTareas().stream()
                        .filter(t -> "PENDIENTE".equalsIgnoreCase(t.getEstado()))
                        .count()
        );

        model.addAttribute("tareasCompletadas",
                tareaService.listarTareas().stream()
                        .filter(t -> "COMPLETADA".equalsIgnoreCase(t.getEstado()))
                        .count()
        );

        model.addAttribute("ultimasTareas",
                tareaService.listarTareas()
                        .stream()
                        .limit(5)
                        .toList()
        );

        return "html/dashboard";
    }
}