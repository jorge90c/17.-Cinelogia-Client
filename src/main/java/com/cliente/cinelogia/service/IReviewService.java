package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.Review;
public interface IReviewService {
    List<Review> listarReviews(); 
    Review buscarPorId(Long id);
    List<Review> buscarPorIdPelicula(Long idpelicula);
    Review guardarReview(Review review);
    Review eliminarPorId(Long id);
}
