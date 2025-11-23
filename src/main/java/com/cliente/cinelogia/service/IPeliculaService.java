package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.Pelicula;


public interface IPeliculaService {
    List<Pelicula> listarPeliculas(); 
    Pelicula buscarPorId(Integer idCurso);
    List<Pelicula> buscarPorActor(String actor);
    List<Pelicula> buscarPorGeneroPelicula(String genero);
    List<Pelicula> buscarPorTituloPelicula(String titulo);
    Pelicula crearPelicula(Pelicula pelicula);   
    Pelicula eliminarPelicula(Integer id);   
    Pelicula editarPelicula(Integer id ,Pelicula pelicula);    
    Pelicula editarPeliculaParcialmente(Integer id ,Pelicula pelicula);
}