package com.servitareas.servitareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servitareas.servitareas.model.Tarea;
import com.servitareas.servitareas.service.TareaService;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tareas", tareaService.listarTareas());
        return "tarea/lista"; // Asegúrate de crear luego las vistas en esta ruta
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tarea", new Tarea());
        /* * NOTA PARA EL FUTURO: Como la Tarea necesita un Proyecto y un Usuario, 
         * más adelante tendrás que inyectar el UsuarioService y ProyectoService aquí 
         * para mandar la lista de ambos a la vista y poder seleccionarlos en un <select>.
         */
        return "tarea/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tarea tarea) {
        tareaService.guardarTarea(tarea);
        return "redirect:/tareas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        return tareaService.obtenerPorId(id)
                .map(tarea -> {
                    model.addAttribute("tarea", tarea);
                    return "tarea/formulario";
                })
                .orElse("redirect:/tareas");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }
}
