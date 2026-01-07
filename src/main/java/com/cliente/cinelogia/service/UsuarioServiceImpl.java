package com.cliente.cinelogia.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.UsuarioClient;
import com.cliente.cinelogia.model.User;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    
    private final UsuarioClient usuarioClient;

    public UsuarioServiceImpl(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public List<User> listarUsuarios(){
        return usuarioClient.listarUsuarios();
    }

    @Override
    public User buscarPorId(Integer id){
        return usuarioClient.buscarUsuarioPorId(id);
    }

    @Override
    public List<User> buscarPorUsername(String username){
        return usuarioClient.buscarPorUsername(username);
    }

    @Override
    public User buscarPorCorreo(String correo){
        return usuarioClient.buscarPorCorreo(correo);
    }
    
    @Override public User login(String correo, String clave) 
    { 
        return usuarioClient.login(correo, clave); 
    }

    @Override
    public User crearUsuario(User usuario){
        return usuarioClient.guardarUsuario(usuario);
    }

    @Override
    public User actualizarUsuario(Integer id, User usuario){
        return usuarioClient.actualizarUsuario(id, usuario);
    }

    @Override
    public void eliminarUsuario(Integer id){
        usuarioClient.eliminarUsuario(id);
    }

}
