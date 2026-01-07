package com.cliente.cinelogia.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cliente.cinelogia.model.Actor;
import com.cliente.cinelogia.model.Pelicula;
import com.cliente.cinelogia.service.IActorService;
import com.cliente.cinelogia.service.IPeliculaService;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PeliculaController {

    @Autowired 
    IPeliculaService peliculaService;

    @Autowired
    IActorService actorService;

    
    /*Metodo de listado y busqueda*/
    @GetMapping("/ListaPeliculas")
    public String ListaPeliculas( @RequestParam(required = false) String titulo,
                                  @RequestParam(required = false) String actor,
                                  @RequestParam(required = false) String genero,
                                  Model model) {
        List<Pelicula> peliculas;
        if (titulo != null && !titulo.isBlank()) {
            peliculas = peliculaService.buscarPorTituloPelicula(titulo);
        }
        else if (actor != null && !actor.isBlank()) {
            peliculas = peliculaService.buscarPorActor(actor);
        }
        else if (genero != null && !genero.isBlank()) {
            peliculas = peliculaService.buscarPorGeneroPelicula(genero);
        }
        else {
            peliculas = peliculaService.listarPeliculas();
        }
        model.addAttribute("peliculas", peliculas);
        return "peliculas/ListaPeliculas";
    }

    @GetMapping("/peliculas/{id}")
    public String verPelicula(Model model,@PathVariable("id") Integer id) {
        Pelicula pelicula = peliculaService.buscarPorId(id); 
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("modo", "ver"); 
        return "peliculas/pelicula";
    }

    @GetMapping("/NuevaPelicula")
    public String nuevaPelicula(Model model) 
    {
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("modo", "crear");

        List<Actor> actores = actorService.listarActores();
        model.addAttribute("actoresDisponibles", actores);
        return "peliculas/pelicula";
    }

    @PostMapping("/eliminarPelicula/{id}")
    public String eliminarPelicula(@PathVariable("id") Integer id) {
        peliculaService.eliminarPelicula(id);
        return "redirect:/ListaPeliculas";
    }

    
    @GetMapping("/editarPelicula/{id}")
    public String preEditarPelicula(Model model,@PathVariable("id") Integer id) {
        Pelicula pelicula = peliculaService.buscarPorId(id); 
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("modo", "editar"); 

        List<Actor> actores = actorService.listarActores();
        model.addAttribute("actoresDisponibles", actores);
        return "peliculas/pelicula";
    }

   @PostMapping("/actualizarPelicula/{id}")
    public String editarPelicula(@PathVariable("id") Integer id,
                                @ModelAttribute Pelicula pelicula,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "actoresIds", required = false) List<Long> actoresIds,
                                Model model) {
        Pelicula existente = peliculaService.buscarPorId(id);

        if (file != null && !file.isEmpty()) {
            try {
                String carpeta = "uploads/";
                Path ruta = Paths.get(carpeta + file.getOriginalFilename());
                Files.createDirectories(ruta.getParent());
                Files.write(ruta, file.getBytes());
                pelicula.setImagenPortada("/uploads/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error al guardar la imagen");
                model.addAttribute("modo", "editar");
                model.addAttribute("actoresDisponibles", actorService.listarActores());
                return "peliculas/pelicula";
            }
        } else {
            pelicula.setImagenPortada(existente.getImagenPortada());
        }

        if (actoresIds != null && !actoresIds.isEmpty()) {
            List<Actor> actoresSeleccionados = new ArrayList<>();
            for (Long actorId : actoresIds) {
                Actor actor = actorService.buscarPorId(actorId.intValue());
                if (actor != null) {
                    actoresSeleccionados.add(actor);
                }
            }
            pelicula.setActores(actoresSeleccionados);
        } else {
            pelicula.setActores(existente.getActores());
        }

        peliculaService.editarPelicula(id, pelicula);
        return "redirect:/ListaPeliculas";
    }


    /*guardar*/
    @PostMapping("/guardarPelicula")
    public String guardarPelicula(@Valid @ModelAttribute("pelicula") Pelicula pelicula,
                                BindingResult result,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "actoresIds", required = false) List<Long> actoresIds,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            model.addAttribute("actoresDisponibles", actorService.listarActores());
            return "peliculas/pelicula";
        }
        if (file != null && !file.isEmpty()) {
            try {
                String carpeta = "uploads/";
                Path ruta = Paths.get(carpeta + file.getOriginalFilename());
                Files.createDirectories(ruta.getParent());
                Files.write(ruta, file.getBytes());
                pelicula.setImagenPortada("/uploads/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Error al guardar la imagen");
                model.addAttribute("modo", "crear");
                model.addAttribute("actoresDisponibles", actorService.listarActores());
                return "peliculas/pelicula";
            }
        } else {
            model.addAttribute("error", "Debes añadir una imagen de portada");
            model.addAttribute("modo", "crear");
            model.addAttribute("actoresDisponibles", actorService.listarActores());
            return "peliculas/pelicula";
        }

        if (actoresIds != null && !actoresIds.isEmpty()) {
            List<Actor> actoresSeleccionados = new ArrayList<>();
            for (Long id : actoresIds) {
                Actor actor = actorService.buscarPorId(id.intValue());
                if (actor != null) {
                    actoresSeleccionados.add(actor);
                }
            }
            pelicula.setActores(actoresSeleccionados);
        }

        peliculaService.crearPelicula(pelicula);
        return "redirect:/ListaPeliculas";
    }

}
