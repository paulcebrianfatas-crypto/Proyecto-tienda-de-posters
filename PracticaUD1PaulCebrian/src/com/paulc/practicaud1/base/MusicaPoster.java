package com.paulc.practicaud1.base;

import java.time.LocalDate;

/**
 * Clase que representa un póster relacionado con la música. Extiende la clase {@link Poster}
 * añadiendo información específica como el estilo musical, el grupo o artista y su nacionalidad.
 */
public class MusicaPoster extends Poster {

    /** Estilo musical representado en el póster (rock, pop, jazz, etc.) */
    private String estilo;

    /** Nombre del grupo o artista musical */
    private String grupo;

    /** Nacionalidad del grupo o artista */
    private String nacionalidad;

    /**
     * Constructor completo que inicializa tanto los atributos heredados de {@link Poster}
     * como los propios de la clase {@code MusicaPoster}.
     *
     * @param titulo título del póster
     * @param autor autor o diseñador del póster
     * @param dimensiones dimensiones del póster
     * @param fechaCreado fecha de creación del póster
     * @param lenguaje idioma utilizado
     * @param nCopias número de copias existentes
     * @param publico indica si el póster es público
     * @param imagen imagen del póster en formato de bytes
     * @param estilo estilo musical representado
     * @param grupo nombre del grupo o artista
     * @param nacionalidad nacionalidad del grupo o artista
     */
    public MusicaPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado,
                        String lenguaje, int nCopias, boolean publico, byte[] imagen,
                        String estilo, String grupo, String nacionalidad) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico, imagen);
        this.estilo = estilo;
        this.grupo = grupo;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Constructor vacío que permite crear un {@code MusicaPoster} sin valores iniciales.
     */
    public MusicaPoster() {
    }

    /**
     * @return estilo musical del póster
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * @param estilo nuevo estilo musical
     */
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    /**
     * @return nombre del grupo o artista
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo nuevo nombre del grupo o artista
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return nacionalidad del grupo o artista
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad nueva nacionalidad del grupo o artista
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Devuelve una representación en texto del objeto, incluyendo
     * tanto los atributos heredados como los específicos de música.
     *
     * @return cadena con los valores del póster musical
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
                " grupo= " + getGrupo()  +
                ", estilo= " + getEstilo()  +
                ", nacionalidad= " + getNacionalidad() +
                '}';
    }
}
