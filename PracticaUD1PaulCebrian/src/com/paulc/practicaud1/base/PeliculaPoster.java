package com.paulc.practicaud1.base;

import java.time.LocalDate;

/**
 * Clase que representa un póster de película. Extiende la clase {@link Poster}
 * e incluye información específica como el género, el director y la puntuación.
 */
public class PeliculaPoster extends Poster {

    /** Género de la película (acción, drama, comedia, terror, etc.) */
    private String genero;

    /** Director de la película */
    private String director;

    /** Puntuación de la película (por ejemplo en IMDB, FilmAffinity, etc.) */
    private float puntuacion;

    /**
     * Constructor completo que inicializa tanto los atributos heredados como
     * los específicos de un póster de película.
     *
     * @param titulo título del póster
     * @param autor creador o diseñador del póster
     * @param dimensiones dimensiones físicas del póster
     * @param fechaCreado fecha de creación del póster
     * @param lenguaje lenguaje utilizado en el contenido
     * @param nCopias número de copias disponibles
     * @param publico indica si el póster es público
     * @param imagen imagen en formato de bytes
     * @param genero género de la película
     * @param director director de la película
     * @param puntuacion puntuación asignada a la película
     */
    public PeliculaPoster(String titulo, String autor, String dimensiones, LocalDate fechaCreado, String lenguaje,
                          int nCopias, boolean publico, byte[] imagen,
                          String genero, String director, float puntuacion) {
        super(titulo, autor, dimensiones, fechaCreado, lenguaje, nCopias, publico, imagen);
        this.genero = genero;
        this.director = director;
        this.puntuacion = puntuacion;
    }

    /**
     * Constructor vacío que permite crear un póster de película sin asignar valores iniciales.
     */
    public PeliculaPoster() {
    }

    /**
     * @return género de la película
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero nuevo género de la película
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return director de la película
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director nuevo director de la película
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return puntuación de la película
     */
    public float getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion nueva puntuación asignada
     */
    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Devuelve una representación en texto del póster de película,
     * incluyendo tanto los atributos heredados como los específicos.
     *
     * @return cadena con los valores del póster de película
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
                " genero= " + getGenero()  +
                ", director= " + getDirector()  +
                ", puntuacion= " + getDirector() +
                '}';
    }
}
