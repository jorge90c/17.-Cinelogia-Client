package com.cliente.cinelogia.model;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    private Integer idUsuario;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 100, message = "El nombre de usuario no puede superar los 100 caracteres")
    private String username;

    @Size(max = 50, message = "La contraseña no puede superar los 50 caracteres")
    private String password;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo válido")
    private String correo;

    private Boolean enable;

    private List<Authority> roles;

    public User() {}

    public User(String username, String password, String correo, boolean enable, List<Authority> roles) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.enable = enable;
        this.roles = roles;
    }

    // 🔹 Getters y Setters
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Boolean getEnable() { return enable; }
    public void setEnable(Boolean enable) { this.enable = enable; }

    public List<Authority> getRoles() { return roles; }
    public void setRoles(List<Authority> roles) { this.roles = roles; }
}
