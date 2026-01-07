package com.cliente.cinelogia.client;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.service.annotation.*;
import com.cliente.cinelogia.model.Review;

@HttpExchange("/Reviews")
public interface ReviewClient {
    @GetExchange 
    List<Review> listarReviews(); 
    @GetExchange("/{id}") 
    Review buscarPorId(@PathVariable Long id);
    @GetExchange("/buscar/{idpelicula}") 
    List<Review> buscarPorIdPelicula(@PathVariable Long idpelicula);
    
    @PostExchange( 
        contentType = "application/json;charset=UTF-8", 
        accept = "application/json;charset=UTF-8" )
    Review guardarReview(@RequestBody Review review);
    
    @DeleteExchange("/{id}") 
    Review eliminarPorId(@PathVariable Long id);
}
