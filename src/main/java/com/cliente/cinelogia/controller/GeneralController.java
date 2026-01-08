package com.cliente.cinelogia.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cliente.cinelogia.model.Pelicula;
import com.cliente.cinelogia.model.Review;
import com.cliente.cinelogia.service.IPeliculaService;
import com.cliente.cinelogia.service.IReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.core.Authentication;

@Controller
public class GeneralController {
    
    private final IPeliculaService peliculaService;
    private final IReviewService reviewService;

    public GeneralController(IPeliculaService peliculaService, IReviewService reviewService) {
        this.peliculaService = peliculaService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Pelicula> peliculas = peliculaService.listarPeliculas();
        model.addAttribute("peliculas", peliculas);
        Map<Long, List<Review>> reviewsPorPelicula = new HashMap<>();
        Map<Long, Double> promedioPorPelicula = new HashMap<>();
        for (Pelicula pelicula : peliculas) {
            Long id = pelicula.getId(); 
            List<Review> reviews = reviewService.buscarPorIdPelicula(id); 
            reviewsPorPelicula.put(id, reviews); 

            if (reviews != null && !reviews.isEmpty()) 
                { 
                    double promedio = reviews.stream() 
                    .mapToInt(Review::getNota) 
                    .average() 
                    .orElse(0); 
                    promedioPorPelicula.put(id, promedio); 
                } 
            else 
                { 
                    promedioPorPelicula.put(id, null); // sin comentarios 
                }
        }
        model.addAttribute("reviewsPorPelicula", reviewsPorPelicula);
        model.addAttribute("promedioPorPelicula", promedioPorPelicula);
        return "home";
    }
    
    @GetMapping("/login") 
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) 
    { 
        if (principal != null) 
            { 
                return "redirect:/"; 
            } 
        if (error != null) 
            { 
                model.addAttribute("msg", "Error al iniciar sesión: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!"); 
            } 
                return "login"; 
    }  
    
    
    @GetMapping("/logout") 
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) 
    { 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        if (auth != null)
            { 
                new SecurityContextLogoutHandler().logout(request, response, auth); 
            } 
        return "redirect:/login"; }
}
