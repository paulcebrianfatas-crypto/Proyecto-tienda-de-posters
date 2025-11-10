package com.paulc.practicaud1.base;

import java.time.LocalDate;

public class MusicaPoster extends Poster{
    private String estilo;
    private String grupo;
    private String nacionalidad;

    public MusicaPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico, byte[] imagen, String estilo, String grupo, String nacionalidad) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico, imagen);
        this.estilo = estilo;
        this.grupo = grupo;
        this.nacionalidad = nacionalidad;
    }

    public MusicaPoster() {

    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
                " grupo= " + getGrupo()  +
                ", estilo= " + getEstilo()  +
                ", nacionalidad= " + getNacionalidad() +
                '}';
    }
}
