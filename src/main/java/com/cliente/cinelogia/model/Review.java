package com.cliente.cinelogia.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Review {

    private Integer idReviews;

    @NotNull(message = "La película es obligatoria")
    private Integer idPelicula;

    @NotBlank(message = "La valoración no puede estar vacía")
    @Size(max = 5000, message = "La valoración no puede superar los 5000 caracteres")
    private String valoracion;

    @NotNull(message = "La nota es obligatoria")
    @Min(value = 1, message = "La nota mínima es 1")
    @Max(value = 10, message = "La nota máxima es 10")
    private Integer nota;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El usuario es obligatorio")
    private User usuario;

    public Integer getIdReviews() {
        return idReviews;
    }

    public void setIdReviews(Integer idReviews) {
        this.idReviews = idReviews;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
