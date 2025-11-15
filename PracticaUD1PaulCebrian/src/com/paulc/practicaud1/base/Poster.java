package com.paulc.practicaud1.base;

import java.time.LocalDate;

/**
 * Clase que representa un póster genérico. Contiene información común
 * para todas las variantes de pósters (música, obra de arte y película).
 *
 * Incluye datos básicos como título, autor, dimensiones, fecha de creación,
 * lenguaje, número de copias, si es público y la imagen asociada en formato
 * de bytes.
 */
public class Poster {

    /** Título del póster */
    private String titulo;

    /** Autor o creador del póster */
    private String autor;

    /** Dimensiones del póster en formato "ancho x alto" */
    private String dimensiones;

    /** Fecha en la que fue creado el póster */
    private LocalDate fechaCreado;

    /** Lenguaje o idioma utilizado en el contenido del póster */
    private String lenguaje;

    /** Número de copias producidas del póster */
    private int nCopias;

    /** Indica si el póster es público o privado */
    private boolean publico;

    /** Imagen del póster almacenada como un array de bytes */
    private byte[] imagen;

    /**
     * Constructor principal que inicializa todos los atributos del póster.
     *
     * @param titulo título del póster
     * @param autor creador o autor del póster
     * @param dimensiones dimensiones del póster en formato "ancho x alto"
     * @param fechaCreado fecha de creación del póster
     * @param lenguaje idioma del contenido
     * @param nCopias número de copias existentes
     * @param publico indica si es público
     * @param imagen datos de la imagen en bytes
     */
    public Poster(String titulo, String autor, String dimensiones, LocalDate fechaCreado,
                  String lenguaje, int nCopias, boolean publico, byte[] imagen) {
        this.titulo = titulo;
        this.autor = autor;
        this.dimensiones = dimensiones;
        this.fechaCreado = fechaCreado;
        this.lenguaje = lenguaje;
        this.nCopias = nCopias;
        this.publico = publico;
        this.imagen = imagen;
    }

    /**
     * Constructor vacío para permitir la creación de un póster sin definir valores iniciales.
     */
    public Poster() {
    }

    /** @return título del póster */
    public String getTitulo() {
        return titulo;
    }

    /** @param titulo nuevo título del póster */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return autor del póster */
    public String getAutor() {
        return autor;
    }

    /** @param autor nuevo autor del póster */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /** @return dimensiones del póster */
    public String getDimensiones() {
        return dimensiones;
    }

    /** @param dimensiones nuevas dimensiones del póster */
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    /** @return fecha de creación del póster */
    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    /** @param fechaCreado nueva fecha de creación */
    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    /** @return lenguaje o idioma del póster */
    public String getLenguaje() {
        return lenguaje;
    }

    /** @param lenguaje nuevo lenguaje del póster */
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    /** @return número de copias del póster */
    public int getnCopias() {
        return nCopias;
    }

    /** @param nCopias nuevo número de copias */
    public void setnCopias(int nCopias) {
        this.nCopias = nCopias;
    }

    /** @return true si el póster es público, false si es privado */
    public boolean isPublico() {
        return publico;
    }

    /** @param publico nuevo estado de visibilidad del póster */
    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    /** @return imagen del póster en formato de bytes */
    public byte[] getImagen() {
        return imagen;
    }

    /** @param imagen nueva imagen del póster en bytes */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
