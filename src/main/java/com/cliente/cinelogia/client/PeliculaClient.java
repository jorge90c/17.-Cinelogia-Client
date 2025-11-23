package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.service.annotation.*;
import com.cliente.cinelogia.model.Pelicula;

@HttpExchange("/peliculas")
public interface PeliculaClient {
    @GetExchange 
    List<Pelicula> listarPeliculas(); 
    
    @GetExchange("/{id}") 
    Pelicula buscarPorId(@PathVariable Integer id);

    @GetExchange("/buscar/a/{actor}") 
    List<Pelicula> buscarPorActor(@PathVariable String actor);

    @GetExchange("/buscar/g/{genero}") 
    List<Pelicula> buscarPorGeneroPelicula(@PathVariable String genero);

    @GetExchange("/buscar/t/{titulo}") 
    List<Pelicula> buscarPorTituloPelicula(@PathVariable String titulo);

    @PostExchange 
    Pelicula crearPelicula(@RequestBody Pelicula pelicula);   

    @DeleteExchange("/{id}") 
    Pelicula eliminarPelicula(@PathVariable Integer id);   

    @PutExchange("/{id}") 
    Pelicula editarPelicula(@PathVariable Integer id ,@RequestBody Pelicula pelicula);
    
    @PatchExchange("/{id}") 
    Pelicula editarPeliculaParcialmente(@PathVariable Integer id , @RequestBody Pelicula pelicula);

}
