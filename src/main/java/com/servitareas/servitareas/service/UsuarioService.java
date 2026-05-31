package com.servitareas.servitareas.service;

import com.servitareas.servitareas.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();

    Optional<Usuario> obtenerPorId(Long id);

    Usuario guardarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);
}