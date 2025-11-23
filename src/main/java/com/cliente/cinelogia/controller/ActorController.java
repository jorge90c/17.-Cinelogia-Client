package com.cliente.cinelogia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cliente.cinelogia.model.Actor;
import com.cliente.cinelogia.service.IActorService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/actores")
public class ActorController {


    @Autowired 
    IActorService actorService;

    // Listar actores
    @GetMapping
        public String listarActores(Model model) {
        model.addAttribute("actores", actorService.listarActores());
        return "actores/actores";
    }

    //Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String nuevoActor(Model model) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("modo", "crear");
        return "actores/actor_form";
    }

     //Guardar actor
    @PostMapping("/guardar")
    public String guardarActor(@Valid @ModelAttribute Actor actor,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "actores/actor_form";
        }
        actorService.guardarActor(actor);
        return "redirect:/actores";
    }

    //Ver actor
    @GetMapping("/ver/{id}")
    public String verActor(@PathVariable Integer id, Model model) {
        Actor actor = actorService.buscarPorId(id);
        model.addAttribute("actor", actor);
        model.addAttribute("modo", "ver");
        return "actores/actor_form";
    }

    //Editar actor
    @GetMapping("/editar/{id}")
    public String editarActor(@PathVariable Integer id, Model model) {
        Actor actor = actorService.buscarPorId(id);
        model.addAttribute("actor", actor);
        model.addAttribute("modo", "editar");
        return "actores/actor_form";
    }

    // 🔹 Actualizar actor
    @PostMapping("/actualizar/{id}")
    public String actualizarActor(@PathVariable Long id,
                                  @Valid @ModelAttribute Actor actor,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "editar");
            return "actores/actor_form";
        }
        actorService.actualizarPorId(id, actor);
        return "redirect:/actores";
    }

    // 🔹 Eliminar actor
    @PostMapping("/eliminar/{id}")
    public String eliminarActor(@PathVariable Long id) {
        actorService.eliminarPorId(id);
        return "redirect:/actores";
    }

}
