package com.servitareas.servitareas.Unit;

import com.servitareas.servitareas.model.Usuario;
import com.servitareas.servitareas.repository.UsuarioRepository;
import com.servitareas.servitareas.service.impl.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    void listarUsuariosDebeRetornarLista() {

        Usuario u1 = new Usuario();
        u1.setNombre("Juan");

        Usuario u2 = new Usuario();
        u2.setNombre("Pedro");

        when(usuarioRepository.findAll())
                .thenReturn(Arrays.asList(u1, u2));

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerUsuarioPorIdDebeRetornarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado =
                usuarioService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    void guardarUsuarioDebeGuardarCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        Usuario guardado =
                usuarioService.guardarUsuario(usuario);

        assertEquals("Carlos", guardado.getNombre());

        verify(usuarioRepository, times(1))
                .save(usuario);
    }

    @Test
    void eliminarUsuarioDebeLlamarDeleteById() {

        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository, times(1))
                .deleteById(1L);
    }
}