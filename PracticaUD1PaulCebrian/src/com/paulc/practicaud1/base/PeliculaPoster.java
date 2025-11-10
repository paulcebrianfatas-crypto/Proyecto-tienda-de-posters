package com.paulc.practicaud1.base;

import java.time.LocalDate;

public class PeliculaPoster extends Poster {
    private String genero;
    private String director;
    private float puntuacion;

    public PeliculaPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico, byte[] imagen,
                          String genero, String director, float puntuacion) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico,imagen);
        this.genero = genero;
        this.director = director;
        this.puntuacion = puntuacion;
    }

    public PeliculaPoster() {
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "PeliculaPoster{" +
                " titulo= " + getTitulo() +
                " autor= " + getAutor() +
                " dimensiones= " + getDimensiones() +
                " fechaCreado= " + getFechaCreado() +
                " lenguaje= " + getLenguaje() +
                " nCopias= " + getnCopias() +
                " publico= " + isPublico() +
                " genero= " + getGenero()  +
                ", director= " + getDirector()  +
                ", puntuacion= " + getDirector() +
                '}';
    }
}
