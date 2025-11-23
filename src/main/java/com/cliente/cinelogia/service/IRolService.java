package com.cliente.cinelogia.service;
import java.util.List;
import com.cliente.cinelogia.model.Rol;

public interface IRolService {
    List<Rol> listarRoles(); 
    Rol buscarPorId(Integer id);
    Rol crearRol(Rol rol);   
    Rol eliminarRol(Integer id);
}
