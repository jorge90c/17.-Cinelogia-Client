package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.dto.LoginDTO;
import com.cliente.cinelogia.dto.UsuarioResponseDTO;
import com.cliente.cinelogia.dto.UsuarioRolUpdateDTO;
import com.cliente.cinelogia.model.Usuario;

public interface IUsuarioService {

    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO buscarPorId(Long id);
    UsuarioResponseDTO buscarPorUsername(String username);
    UsuarioResponseDTO crearUsuario(Usuario usuario);
    UsuarioResponseDTO actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
    String login(LoginDTO loginDTO);
    UsuarioResponseDTO actualizarRol(Long id,UsuarioRolUpdateDTO dto);
}
