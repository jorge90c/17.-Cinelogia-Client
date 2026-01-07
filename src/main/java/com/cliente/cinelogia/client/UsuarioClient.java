package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import com.cliente.cinelogia.model.User;


@HttpExchange("/usuarios")
public interface UsuarioClient {

    // Listar todos los usuarios
    @GetExchange
    List<User> listarUsuarios();

    @GetExchange("/{id}")
    User buscarUsuarioPorId(@PathVariable Integer id);

    @GetExchange("/username/{username}")
    List<User> buscarPorUsername(@PathVariable String username);

    @GetExchange("/buscar/{correo}")
    User buscarPorCorreo(@PathVariable String correo);

    @GetExchange("/login/{correo}/{clave}")
    User login(@PathVariable String correo, @PathVariable String clave);

    @PostExchange(
        contentType = "application/json;charset=UTF-8",
        accept = "application/json;charset=UTF-8"
    )
    User guardarUsuario(@RequestBody User usuario);

    @PutExchange(
        url = "/{id}",
        contentType = "application/json;charset=UTF-8",
        accept = "application/json;charset=UTF-8"
    )
    User actualizarUsuario(@PathVariable Integer id, @RequestBody User usuario);


    @DeleteExchange("/{id}")
    void eliminarUsuario(@PathVariable Integer id);

}
