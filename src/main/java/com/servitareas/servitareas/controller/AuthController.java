
package com.servitareas.servitareas.controller;

// import com.servitareas.servitareas.model.Usuario;
import com.servitareas.servitareas.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String inicio(HttpSession session) {
        if (session != null && session.getAttribute("userEmail") != null) {
            return "redirect:/dashboard";
        }
        return "logi";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String password,
                        HttpSession session) {

        return usuarioRepository.findByCorreo(correo)
                .map(u -> {
                    if (u.getPassword() != null && u.getPassword().equals(password)) {
                        session.setAttribute("userEmail", u.getCorreo());
                        return "redirect:/dashboard";
                    }
                    return "redirect:/";
                })
                .orElse("redirect:/");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
