package com.cliente.cinelogia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cliente.cinelogia.dto.UsuarioResponseDTO;
import com.cliente.cinelogia.model.Rol;
import com.cliente.cinelogia.model.Usuario;
import com.cliente.cinelogia.service.IRolService;
import com.cliente.cinelogia.service.IUsuarioService;

import jakarta.validation.Valid;


@Controller
public class UsuarioController {
    @Autowired
    IUsuarioService usuarioService;
    
    @Autowired
    IRolService rolService;
    

    //Nuevo usuario (formulario)
    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modo", "crear");
        return "/usuarios/usuario_form";
    }

    // Guardar Nuevo usuario , le asigna un rol USER por defecto
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "/usuarios/usuario_form";
        }

        // Asignar rol por defecto (id=2) al crear
        if (usuario.getRol() == null || usuario.getRol().getId() == null) {
            Rol rolPorDefecto = new Rol();
            rolPorDefecto= rolService.buscarPorId(2); // ID del rol USER por defecto        
            usuario.setRol(rolPorDefecto);
        }
        usuarioService.crearUsuario(usuario);
        return "redirect:/usuarios";
    }

    // Listar usuarios
    @GetMapping("/usuarios")
    public String listaUsuarios(Model model) {
        model.addAttribute("usuarios",usuarioService.listarUsuarios());
        return "usuarios/usuarios"; 
    }

    // Ver usuario (solo lectura por ID)
    @GetMapping("/usuarios/ver/{id}")
    public String verUsuario(@PathVariable Long id, Model model) {
        var dto = usuarioService.buscarPorId(id);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setCorreo(dto.getCorreo());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());

        // Crear objeto Rol a partir del rolNombre del DTO
        Rol rol = new Rol();
        rol.setNombre(dto.getRolNombre());
        usuario.setRol(rol);

        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "verUsuario"); //Modo para esconder el password y modo visualizacion
        return "usuarios/usuario_form"; 
    }

    // Editar usuario (formulario)
    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        var dto = usuarioService.buscarPorId(id);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setCorreo(dto.getCorreo());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());

        List<Rol> roles=rolService.listarRoles();
            Rol rolActual = new Rol();
            for (Rol r : roles) {
                if (r.getNombre().equals(dto.getRolNombre())) {
                    rolActual.setId(r.getId());
                    rolActual.setNombre(r.getNombre());
                    rolActual.setDescripcion(r.getDescripcion());
                    break;
                }
            }

        usuario.setRol(rolActual);

        // 🔹 Traer todos los roles disponibles
        model.addAttribute("roles", rolService.listarRoles());
        model.addAttribute("usuario", usuario);
        model.addAttribute("modo", "editar");
        return "usuarios/usuario_form";
    }

    //Actualizar usuario
    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                    @Valid @ModelAttribute Usuario usuario,
                                    BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            // Recargar lista de roles para el formulario
            model.addAttribute("roles", rolService.listarRoles());
            model.addAttribute("modo", "editar");
            return "usuarios/usuario_form";
        }

        // Si el rol no viene informado en el formulario, mantener el rol actual
        if (usuario.getRol() == null || usuario.getRol().getId() == null) {
            UsuarioResponseDTO usuarioActual = usuarioService.buscarPorId(id);
            List<Rol> roles=rolService.listarRoles();
            Rol rolActual = new Rol();
            for (Rol r : roles) {
                if (r.getNombre().equals(usuarioActual.getRolNombre())) {
                    rolActual.setId(r.getId());
                    rolActual.setNombre(r.getNombre());
                    rolActual.setDescripcion(r.getDescripcion());
                    break;
                }
            }

            usuario.setRol(rolActual);
        }

        usuarioService.actualizarUsuario(id, usuario);
        return "redirect:/usuarios";
    }


    //Eliminar usuario
    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }


}
