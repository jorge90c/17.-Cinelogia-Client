package com.cliente.cinelogia.client;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.cliente.cinelogia.model.Actor;


@HttpExchange("/actores")
public interface ActorClient {

    @GetExchange 
    List<Actor> listarActores();   
    @GetExchange("/{id}") 
    Actor buscarPorId(@PathVariable Integer id);
    @PostExchange 
    Actor guardarActor(@RequestBody Actor actor);    
    @PutExchange("/{id}") 
    Actor actualizar(@PathVariable Long id ,@RequestBody Actor actorActualizado);
    @PatchExchange("/{id}")
    Actor actualizarPorId(@PathVariable Long id, @RequestBody Actor actorActualizado);
    @DeleteExchange("/{id}")
    void eliminarPorId(@PathVariable Long id);

}