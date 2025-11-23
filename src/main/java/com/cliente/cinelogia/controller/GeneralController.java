package com.cliente.cinelogia.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cliente.cinelogia.model.Pelicula;
import com.cliente.cinelogia.service.IPeliculaService;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class GeneralController {
    
    private final IPeliculaService peliculaService;

    // Inyección del servicio
    public GeneralController(IPeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Obtener todas las películas
        List<Pelicula> peliculas = peliculaService.listarPeliculas();

        // Pasarlas al modelo
        model.addAttribute("peliculas", peliculas);

        // Renderizar la vista home.html
        return "home";
    }
    
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("titulo", "Iniciar sesión");
        return "login";
    } 

    @PostMapping("/logout")
    public String generarLogout(Model model) {
        return "home"; 

    }
}
