package com.cliente.cinelogia.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.UsuarioClient;
import com.cliente.cinelogia.dto.LoginDTO;
import com.cliente.cinelogia.dto.UsuarioResponseDTO;
import com.cliente.cinelogia.dto.UsuarioRolUpdateDTO;
import com.cliente.cinelogia.model.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    
    private final UsuarioClient usuarioClient;

    public UsuarioServiceImpl(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioClient.listarUsuarios();
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id){
        return usuarioClient.buscarPorId(id);
    }

    @Override
    public UsuarioResponseDTO buscarPorUsername(String username){
        return usuarioClient.buscarPorUsername(username);
    }

    @Override
    public UsuarioResponseDTO crearUsuario(Usuario usuario){
        return usuarioClient.crearUsuario(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, Usuario usuario){
        return usuarioClient.actualizarUsuario(id, usuario);
    }

    @Override
    public void eliminarUsuario(Long id){
        usuarioClient.eliminarUsuario(id);
    }

    @Override
    public String login(LoginDTO loginDTO){
        return usuarioClient.login(loginDTO);
    }

    @Override
    public UsuarioResponseDTO actualizarRol(Long id,UsuarioRolUpdateDTO dto){
        return usuarioClient.actualizarRol(id, dto);
    }

}
