package com.paulc.practicaud1.base;

import java.time.LocalDate;

/**
 * Clase que representa un póster de una obra de arte. Extiende la clase {@link Poster}
 * añadiendo información específica como la edición, el tipo de arte y la paleta de colores utilizada.
 */
public class ObraPoster extends Poster {

    /** Número de edición de la obra (ediciones limitadas, series, etc.) */
    private int edicion;

    /** Tipo de arte representado (pintura, escultura, mural, digital, etc.) */
    private String tipoArte;

    /** Paleta de colores predominante en la obra */
    private String paletaColores;

    /**
     * Constructor completo que inicializa tanto los atributos heredados como los propios
     * de la clase {@code ObraPoster}.
     *
     * @param titulo título del póster
     * @param autor autor de la obra o del diseño del póster
     * @param dimensiones dimensiones físicas del póster
     * @param fechaCreado fecha en que se creó la obra o el póster
     * @param lenguaje lenguaje utilizado en el contenido
     * @param nCopias número de copias disponibles
     * @param publico indica si el póster es público
     * @param imagen imagen asociada en formato de bytes
     * @param edicion número de edición de la obra
     * @param tipoArte tipo de arte representado
     * @param paletaColores paleta de colores predominante
     */
    public ObraPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado,
                      String lenguaje, int nCopias, boolean publico, byte[] imagen,
                      int edicion, String tipoArte, String paletaColores) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico, imagen);
        this.edicion = edicion;
        this.tipoArte = tipoArte;
        this.paletaColores = paletaColores;
    }

    /**
     * Constructor vacío que permite crear un objeto {@code ObraPoster} sin inicializar atributos.
     */
    public ObraPoster() {
    }

    /**
     * @return número de edición de la obra
     */
    public int getEdicion() {
        return edicion;
    }

    /**
     * @param edicion nuevo número de edición
     */
    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    /**
     * @return tipo de arte representado en el póster
     */
    public String getTipoArte() {
        return tipoArte;
    }

    /**
     * @param tipoArte nuevo tipo de arte representado
     */
    public void setTipoArte(String tipoArte) {
        this.tipoArte = tipoArte;
    }

    /**
     * @return paleta de colores predominante en la obra
     */
    public String getPaletaColores() {
        return paletaColores;
    }

    /**
     * @param paletaColores nueva paleta de colores predominante
     */
    public void setPaletaColores(String paletaColores) {
        this.paletaColores = paletaColores;
    }

    /**
     * Devuelve una representación en forma de cadena del póster de obra de arte,
     * incluyendo tanto los atributos heredados como los específicos de esta clase.
     *
     * @return representación textual del objeto
     */
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
