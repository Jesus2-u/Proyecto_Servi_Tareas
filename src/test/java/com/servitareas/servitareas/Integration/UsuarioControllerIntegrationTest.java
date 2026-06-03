package com.servitareas.servitareas.Integration;

import com.servitareas.servitareas.controller.UsuarioController;
import com.servitareas.servitareas.model.Usuario;
import com.servitareas.servitareas.service.UsuarioService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    private final String USER_SESSION = "userEmail";

    @Test
    void listarUsuariosDebeRetornarVistaUsuarios() throws Exception {

        when(usuarioService.listarUsuarios())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/usuarios")
                .sessionAttr(USER_SESSION, "test@test.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("html/usuarios"))
                .andExpect(model().attributeExists("usuarios"));
    }

    @Test
    void nuevoUsuarioDebeRetornarFormulario() throws Exception {

        mockMvc.perform(get("/usuarios/nuevo")
                .sessionAttr(USER_SESSION, "test@test.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("html/registro"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    void editarUsuarioDebeRetornarFormularioConDatos() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");

        when(usuarioService.obtenerPorId(1L))
                .thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/editar/1")
                .sessionAttr(USER_SESSION, "test@test.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("html/registro"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    void guardarUsuarioDebeRedireccionarALista() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");

        when(usuarioService.guardarUsuario(any(Usuario.class)))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios/guardar")
                .sessionAttr(USER_SESSION, "test@test.com")
                .param("nombre", "Juan")
                .param("apellido", "Perez")
                .param("correo", "juan@test.com")
                .param("password", "123456")
                .param("estado", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void eliminarUsuarioDebeRedireccionarALista() throws Exception {

        doNothing().when(usuarioService).eliminarUsuario(1L);

        mockMvc.perform(get("/usuarios/eliminar/1")
                .sessionAttr(USER_SESSION, "test@test.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/usuarios"));

        verify(usuarioService, times(1))
                .eliminarUsuario(1L);
    }
}