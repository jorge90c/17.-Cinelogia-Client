package com.cliente.cinelogia.dto;

//import com.cliente.cinelogia.model.Rol;

public class UsuarioResponseDTO {

    private Long id;
    private String username;
    private String correo;
    private String nombre;
    private String apellido;
    //private Rol rol;
    private String rolNombre;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long id, String username, String correo, String nombre, String apellido, String rolNombre) {
        this.id = id;
        this.username = username;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        //this.rol = rol;
        this.rolNombre = rolNombre;
    }

    // 🔹 Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    //public Rol getRol() { return rol; }
    //public void setRol(Rol rol) { this.rol = rol; }

    public String getRolNombre() { return rolNombre; }
    public void setRolNombre(String rolNombre) { this.rolNombre = rolNombre; }
}

