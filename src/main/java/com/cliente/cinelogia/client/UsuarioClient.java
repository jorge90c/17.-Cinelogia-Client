package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import com.cliente.cinelogia.model.Usuario;
import com.cliente.cinelogia.dto.LoginDTO;
import com.cliente.cinelogia.dto.UsuarioResponseDTO;
import com.cliente.cinelogia.dto.UsuarioRolUpdateDTO;

@HttpExchange("/usuarios")
public interface UsuarioClient {

    // Listar todos los usuarios
    @GetExchange
    List<UsuarioResponseDTO> listarUsuarios();

    // Buscar usuario por ID
    @GetExchange("/{id}")
    UsuarioResponseDTO buscarPorId(@PathVariable Long id);

    // Buscar usuario por username
    @GetExchange("/username/{username}")
    UsuarioResponseDTO buscarPorUsername(@PathVariable String username);

    // Crear usuario
    @PostExchange
    UsuarioResponseDTO crearUsuario(@RequestBody Usuario usuario);

    // Actualizar usuario completo
    @PutExchange("/{id}")
    UsuarioResponseDTO actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario);

    // Eliminar usuario
    @DeleteExchange("/{id}")
    void eliminarUsuario(@PathVariable Long id);

    // Login
    @PostExchange("/login")
    String login(@RequestBody LoginDTO loginDTO);

    // Actualizar solo el rol del usuario
    @PutExchange("/rol/{id}")
    UsuarioResponseDTO actualizarRol(@PathVariable Long id, @RequestBody UsuarioRolUpdateDTO dto);
}
