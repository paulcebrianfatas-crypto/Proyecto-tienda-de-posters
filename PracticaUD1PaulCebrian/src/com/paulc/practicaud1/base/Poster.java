package com.paulc.practicaud1.base;

import java.time.LocalDate;

public class Poster {
    private String titulo;
    private String autor;
    private String dimensiones;
    private LocalDate fechaCreado;
    private String lenguaje;
    private int nCopias;
    private boolean publico;
    private String imagen;

    public Poster(String titulo, String autor, String dimensiones, LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico,String imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.dimensiones = dimensiones;
        this.fechaCreado = fechaCreado;
        this.lenguaje = lenguaje;
        this.nCopias = nCopias;
        this.publico = publico;
        this.imagen = imagen;
    }

    public Poster() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getnCopias() {
        return nCopias;
    }

    public void setnCopias(int nCopias) {
        this.nCopias = nCopias;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
