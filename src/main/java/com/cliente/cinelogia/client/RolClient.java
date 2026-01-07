package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.service.annotation.*;
import com.cliente.cinelogia.model.Authority;

@HttpExchange("/roles")
public interface RolClient {
    
    @GetExchange 
    List<Authority> listarRoles(); 
    
    @GetExchange("/{id}") 
    Authority buscarPorId(@PathVariable Integer id);

    @PostExchange 
    Authority guardarRol(@RequestBody Authority rol);   

    @DeleteExchange("/{id}") 
    Authority eliminarPorId(@PathVariable Integer id);   

}
