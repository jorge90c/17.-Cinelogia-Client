package com.cliente.cinelogia.dto;
import jakarta.validation.constraints.NotNull;

public class UsuarioRolUpdateDTO {

    @NotNull(message = "El ID del rol es obligatorio")
    private Long rolId;

    // 🔹 Constructores
    public UsuarioRolUpdateDTO() {}

    public UsuarioRolUpdateDTO(Long rolId) {
        this.rolId = rolId;
    }

    // 🔹 Getter y Setter
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
}

