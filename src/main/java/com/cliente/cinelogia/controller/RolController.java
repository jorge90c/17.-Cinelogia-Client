package com.cliente.cinelogia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.PostExchange;
import com.cliente.cinelogia.model.Authority;
import com.cliente.cinelogia.service.IRolService;

import jakarta.validation.Valid;

@Controller
public class RolController {
    @Autowired
    IRolService rolService;

    @GetMapping("/roles")
    public String listaRoles(Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "/roles/roles";
    }
    @GetMapping("/roles/nuevo")
    public String nuevoRol(Model model) {
        model.addAttribute("rol", new com.cliente.cinelogia.model.Authority());
        model.addAttribute("modo", "crear");
        return "/roles/rol_form";
    }

    @PostMapping("/roles/guardar")
    public String guardarRol(@Valid @ModelAttribute("rol") Authority rol, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("rol", rol); 
            model.addAttribute("modo", "crear"); 
            return "/roles/rol_form";
        }

        rolService.crearRol(rol);
        return "redirect:/roles";
    }


    @PostMapping("/roles/eliminar/{id}")
    public String eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return "redirect:/roles";
    }

}