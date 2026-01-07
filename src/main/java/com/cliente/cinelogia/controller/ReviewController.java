package com.cliente.cinelogia.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.cliente.cinelogia.model.Review;
import com.cliente.cinelogia.service.IReviewService;
import com.cliente.cinelogia.service.IUsuarioService;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    @Autowired
    IReviewService reviewService;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("Reviews")
    public String listaReview(Model model) {
        model.addAttribute("reviews", reviewService.listarReviews());
        return "/Reviews/reviews";
    }

    @PostMapping("/reviews/eliminar/{id}")
    public String eliminarReview(@PathVariable Long id, Principal principal) {
        Review review = reviewService.buscarPorId(id);
        if (review == null) {
            return "redirect:/?error=notfound";
        }
        if (!review.getUsuario().getCorreo().equals(principal.getName())) {
            return "redirect:/?error=forbidden";
        }
        reviewService.eliminarPorId(id);
        return "redirect:/mis-reviews";
    }


    @GetMapping("/reviews/nuevo/{idPelicula}")
    public String nuevoReview(@PathVariable Integer idPelicula, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Review review = new Review();
        review.setIdPelicula(idPelicula);
        model.addAttribute("modo", "crear");
        model.addAttribute("review", review);
        model.addAttribute("usuario", principal.getName());
        model.addAttribute("fechaActual", LocalDate.now());

        return "reviews/review_form";
    }

    @PostMapping("/reviews/guardar")
    public String guardarReview(
            @RequestParam Integer idPelicula,
            @RequestParam String valoracion,
            @RequestParam Integer nota,
            Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Review review = new Review();
        review.setIdPelicula(idPelicula);
        review.setValoracion(valoracion);
        review.setNota(nota);
        review.setFecha(LocalDate.now());
        review.setUsuario(usuarioService.buscarPorCorreo(principal.getName()));
        reviewService.guardarReview(review);
        return "redirect:/mis-reviews";
    }

    @GetMapping("/mis-reviews")
    public String misReviews(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String correo = principal.getName();
        List<Review> reviews = reviewService.listarReviews()
                .stream()
                .filter(r -> r.getUsuario().getCorreo().equals(correo))
                .toList();
        model.addAttribute("reviews", reviews);
        model.addAttribute("usuario", correo);
        return "reviews/reviews";
    }


    @GetMapping("/reviews/editar/{id}")
    public String editarReview(@PathVariable Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Review review = reviewService.buscarPorId(id);
        if (!review.getUsuario().getCorreo().equals(principal.getName())) {
            return "redirect:/?error=forbidden";
        }
        model.addAttribute("modo", "editar");
        model.addAttribute("review", review);
        model.addAttribute("usuario", principal.getName());
        model.addAttribute("fechaActual", LocalDate.now());

        return "reviews/review_form";
    }

    @PostMapping("/reviews/actualizar")
    public String actualizarReview(
            @RequestParam Long idReviews,
            @RequestParam Long idPelicula,
            @RequestParam String valoracion,
            @RequestParam Integer nota,
            Principal principal) {
        Review review = reviewService.buscarPorId(idReviews);
        if (!review.getUsuario().getCorreo().equals(principal.getName())) {
            return "redirect:/?error=forbidden";
        }
        review.setValoracion(valoracion);
        review.setNota(nota);
        review.setFecha(LocalDate.now());
        reviewService.guardarReview(review);
        return "redirect:/mis-reviews";
    }
}
