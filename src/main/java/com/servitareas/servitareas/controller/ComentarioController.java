package com.servitareas.servitareas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComentarioController {

    @GetMapping("/comentarios")
    public String comentarios(Model model) {
        return "html/comentarios";
    }
}
