package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.service.annotation.*;
import com.cliente.cinelogia.model.Rol;

@HttpExchange("/roles")
public interface RolClient {
    
    @GetExchange 
    List<Rol> listarRoles(); 
    
    @GetExchange("/{id}") 
    Rol buscarPorId(@PathVariable Integer id);

    @PostExchange 
    Rol creaRol(@RequestBody Rol rol);   

    @DeleteExchange("/{id}") 
    Rol eliminaRol(@PathVariable Integer id);   

}
