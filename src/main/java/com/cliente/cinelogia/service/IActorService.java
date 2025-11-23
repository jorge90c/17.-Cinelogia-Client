package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.Actor;

public interface IActorService {

    List<Actor> listarActores();   
    Actor buscarPorId(Integer id);
    Actor guardarActor(Actor actor);   
    Actor actualizar(Long id ,Actor actorActualizado);
    Actor actualizarPorId(Long id,Actor actorActualizado);
    void eliminarPorId(Long id);
}
