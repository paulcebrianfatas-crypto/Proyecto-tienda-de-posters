package com.paulc.practicaud1.base;

import java.time.LocalDate;

public class ObraPoster extends Poster{
    private int edicion;
    private String tipoArte;
    private String paletaColores;

    public ObraPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado, String lenguaje, int nCopias, boolean publico, String imagen, int edicion, String tipoArte, String paletaColores) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico, imagen);
        this.edicion = edicion;
        this.tipoArte = tipoArte;
        this.paletaColores = paletaColores;
    }

    public ObraPoster() {
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getTipoArte() {
        return tipoArte;
    }

    public void setTipoArte(String tipoArte) {
        this.tipoArte = tipoArte;
    }

    public String getPaletaColores() {
        return paletaColores;
    }

    public void setPaletaColores(String paletaColores) {
        this.paletaColores = paletaColores;
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
                " nEdicion= " + getEdicion()  +
                ", tipoArte= " + getTipoArte()  +
                ", paletaColores= " + getPaletaColores() +
                '}';
    }
}
