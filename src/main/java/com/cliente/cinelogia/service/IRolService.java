package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.Authority;

public interface IRolService {
    List<Authority> listarRoles(); 
    Authority buscarPorId(Integer id);
    Authority crearRol(Authority rol);   
    Authority eliminarRol(Integer id);
}
