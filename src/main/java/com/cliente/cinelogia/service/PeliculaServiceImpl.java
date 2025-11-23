package com.cliente.cinelogia.service;
import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.cliente.cinelogia.client.PeliculaClient;
import com.cliente.cinelogia.model.Pelicula;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    private final PeliculaClient peliculaClient; 
    
    //@Autowired
    public PeliculaServiceImpl(PeliculaClient peliculaClient) {
        this.peliculaClient = peliculaClient;
    }

    @Override 
    public List<Pelicula> listarPeliculas(){
        List<Pelicula> lista = peliculaClient.listarPeliculas(); 
        return lista;
    }

    @Override 
    public Pelicula buscarPorId(Integer idPelicula){
        return peliculaClient.buscarPorId(idPelicula);
    }

    @Override
    public List<Pelicula> buscarPorActor(String actor) {
        try {
        return peliculaClient.buscarPorActor(actor);
        } catch (HttpClientErrorException.NotFound e) {
        return List.of(); // Devuelve una lista vacía si no se encuentran películas
        }
    }

    @Override
    public List<Pelicula> buscarPorGeneroPelicula(String genero)
    {
        try{
        return peliculaClient.buscarPorGeneroPelicula(genero);
        } catch (HttpClientErrorException.NotFound e) {
        return List.of(); // Devuelve una lista vacía si no se encuentran películas
        }
    }

    @Override
    public List<Pelicula> buscarPorTituloPelicula(String titulo)
    {
        try {
        return peliculaClient.buscarPorTituloPelicula(titulo);
        } catch (HttpClientErrorException.NotFound e) {
        return List.of(); // Devuelve una lista vacía si no se encuentran películas
        }
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula){
        return peliculaClient.crearPelicula(pelicula);
    }
    
    @Override
    public Pelicula eliminarPelicula(Integer id){
        return peliculaClient.eliminarPelicula(id);
    }
    
    @Override
    public Pelicula editarPelicula(Integer id ,Pelicula pelicula){
        return peliculaClient.editarPelicula(id, pelicula);
    }
    
    @Override
    public Pelicula editarPeliculaParcialmente(Integer id ,Pelicula pelicula){
        return peliculaClient.editarPeliculaParcialmente(id, pelicula);
    }
}
