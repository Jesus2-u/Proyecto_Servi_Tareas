
package com.servitareas.servitareas.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        // Rutas públicas
        if (uri.equals("/") || uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/Imagenes/")
                || uri.startsWith("/usuarios/nuevo") || uri.startsWith("/usuarios/guardar") || uri.startsWith("/login")
                || uri.startsWith("/logout") || uri.startsWith("/error")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userEmail") != null) {
            return true;
        }

        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
