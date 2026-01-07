package com.cliente.cinelogia.model;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Authority {

    private Integer idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(min = 5, max = 20, message = "El nombre debe tener entre 5 y 20 caracteres")
    private String authority;

    private List<User> usuarios;

    public Authority() {
    }

    public Authority(String authority, List<User> usuarios) {
        this.authority = authority;
        this.usuarios = usuarios;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }

}

