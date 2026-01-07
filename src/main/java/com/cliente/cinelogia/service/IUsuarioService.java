package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.User;

public interface IUsuarioService {

    List<User> listarUsuarios();
    User buscarPorId(Integer id);
    List<User> buscarPorUsername(String username);
    User buscarPorCorreo(String correo);
    User login(String correo, String clave);
    User crearUsuario(User usuario);
    User actualizarUsuario(Integer id, User usuario);
    void eliminarUsuario(Integer id);

}
