package com.cliente.cinelogia.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cliente.cinelogia.client.ReviewClient;
import com.cliente.cinelogia.model.Review;

@Service
public class ReviewServiceImpl implements IReviewService {

    private final ReviewClient reviewClient;

    @Autowired
    public ReviewServiceImpl(ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Review> listarReviews() {
        return reviewClient.listarReviews();
    }

    @Override
    public Review buscarPorId(Long id) {
        return reviewClient.buscarPorId(id);
    }

    @Override
    public List<Review> buscarPorIdPelicula(Long idpelicula) {
        return reviewClient.buscarPorIdPelicula(idpelicula);
    }

    @Override
    public Review guardarReview(Review review) {
        return reviewClient.guardarReview(review);
    }

    @Override
    public Review eliminarPorId(Long id) {
        return reviewClient.eliminarPorId(id);
    }
}