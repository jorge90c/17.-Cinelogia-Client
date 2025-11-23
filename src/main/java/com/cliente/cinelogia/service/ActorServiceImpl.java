package com.cliente.cinelogia.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.ActorClient;
import com.cliente.cinelogia.model.Actor;

@Service
public class ActorServiceImpl implements IActorService {
    ActorClient actorClient;

    public ActorServiceImpl(ActorClient actorClient) {
        this.actorClient = actorClient;
    }

    @Override
    public List<Actor> listarActores() {
        return actorClient.listarActores();
    }

    @Override
    public Actor buscarPorId(Integer id) {
        return actorClient.buscarPorId(id);
    }

    @Override
    public Actor guardarActor(Actor actor) {
        return actorClient.guardarActor(actor);
    }


    @Override
     public Actor actualizar(Long id ,Actor actorActualizado){
        return actorClient.actualizar(id, actorActualizado);
    }

    @Override
    public Actor actualizarPorId(Long id,Actor actorActualizado)
    {
        return actorClient.actualizarPorId(id, actorActualizado);
    }

    @Override
    public void eliminarPorId(Long id) {
        actorClient.eliminarPorId(id);
    }

}
