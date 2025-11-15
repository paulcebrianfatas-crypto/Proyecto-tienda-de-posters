package com.paulc.practicaud1;


import com.paulc.practicaud1.gui.PosterControlador;
import com.paulc.practicaud1.gui.PosterModelo;
import com.paulc.practicaud1.gui.Ventana;

/**
 * Clase principal de la aplicación de gestión de pósters.
 *
 * Esta clase se encarga de iniciar la aplicación, creando la ventana
 * principal, el modelo de datos y el controlador que conecta la vista con
 * el modelo siguiendo el patrón MVC.
 */
public class Principal {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Crear la vista principal de la aplicación
        Ventana vista = new Ventana();

        // Crear el modelo que almacena los datos de los pósters
        PosterModelo modelo = new PosterModelo();

        // Crear el controlador que conecta la vista con el modelo
        PosterControlador controlador = new PosterControlador(vista, modelo);
    }
}
