package com.cliente.cinelogia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cliente.cinelogia.model.Authority;
import com.cliente.cinelogia.model.User;
import com.cliente.cinelogia.service.IRolService;
import com.cliente.cinelogia.service.IUsuarioService;

import jakarta.validation.Valid;


@Controller
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    
    @Autowired
    IRolService rolService;
    
    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new User());
        model.addAttribute("modo", "crear");
        return "/usuarios/usuario_form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@Valid @ModelAttribute User usuario,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "/usuarios/usuario_form";
        }

        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Authority rolPorDefecto = new Authority();
            rolPorDefecto= rolService.buscarPorId(2); // ID del rol USER por defecto        
            usuario.setRoles(List.of(rolPorDefecto));
        }
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios")
    public String listaUsuarios(@RequestParam(required = false) String username,Model model) {
        List<User> usuarios; 
        if (username != null && !username.isBlank()) 
            { 
                usuarios = usuarioService.buscarPorUsername(username); 
            } 
        else { 
            usuarios = usuarioService.listarUsuarios(); 
        }
        
        model.addAttribute("usuarios", usuarios); 
        model.addAttribute("username", username);
        return "usuarios/usuarios"; 
    }

    @GetMapping("/usuarios/ver/{id}")
    public String verUsuario(@PathVariable Integer id, Model model) {
        var dto = usuarioService.buscarPorId(id);
        User usuario = new User();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setUsername(dto.getUsername());
        usuario.setCorreo(dto.getCorreo());
        usuario.setEnable(dto.getEnable());
        usuario.setRoles(dto.getRoles());
        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "verUsuario"); //Modo para esconder el password y modo visualizacion
        return "usuarios/usuario_form"; 
    }

    
    @GetMapping("/usuarios/editar/{id}") 
    public String editarUsuario(@PathVariable Integer id, Model model) 
    { 
        var dto = usuarioService.buscarPorId(id); 
        User usuario = new User(); 
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setUsername(dto.getUsername()); 
        usuario.setCorreo(dto.getCorreo()); 
        usuario.setEnable(dto.getEnable()); 
        if (dto.getRoles() == null) 
            { 
                usuario.setRoles(new ArrayList<>()); 
            } 
        else { 
            usuario.setRoles(dto.getRoles()); 
        }
        model.addAttribute("roles", rolService.listarRoles()); 
        model.addAttribute("usuario", usuario); 
        model.addAttribute("modo", "editar"); 
        return "usuarios/usuario_form"; 
    }

    
    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(@Valid @ModelAttribute User usuario,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("roles", rolService.listarRoles());
            model.addAttribute("modo", "editar");
            return "/usuarios/usuario_form";
        }

        User usuarioActual = usuarioService.buscarPorId(usuario.getIdUsuario());
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            usuario.setPassword(usuarioActual.getPassword());
        }

        List<Authority> rolesCompletos = new ArrayList<>();
        if (usuario.getRoles() == null) {
            usuario.setRoles(usuarioActual.getRoles());
        }

        for (Authority r : usuario.getRoles()) 
            {
                if (r.getIdRol() != null && r.getIdRol() > 0){
                    Authority rolCompleto = rolService.buscarPorId(r.getIdRol()); 
                    rolesCompletos.add(rolCompleto);
                }     
            }
        usuario.setRoles(rolesCompletos);
        usuario.setEnable(usuario.getEnable().booleanValue());
        usuarioService.actualizarUsuario(usuario.getIdUsuario(), usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }


}
