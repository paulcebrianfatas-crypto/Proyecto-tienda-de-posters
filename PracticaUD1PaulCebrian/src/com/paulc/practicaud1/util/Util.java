package com.paulc.practicaud1.util;

import com.paulc.practicaud1.base.MusicaPoster;
import com.paulc.practicaud1.base.ObraPoster;
import com.paulc.practicaud1.base.PeliculaPoster;
import com.paulc.practicaud1.base.Poster;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase de utilidades generales para la aplicación. Proporciona métodos comunes
 * como mensajes, selectores de ficheros, visualización de imágenes o impresión
 * de datos.
 */
public class Util {

    /**
     * Muestra una ventana emergente con un mensaje de error.
     * @param mensaje Texto que se mostrará en la ventana.
     */
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje informativo simple.
     * @param mensaje Texto a mostrar.
     */
    public static void mensajeExtra(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra una imagen dentro de un cuadro de diálogo.
     * @param imagen Imagen a visualizar.
     */
    public static void verImagen(Image imagen){
        JLabel lbl = new JLabel(new ImageIcon(imagen));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, lbl, "Ver imagen", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra un cuadro de diálogo pidiendo confirmación al usuario.
     * @param mensaje Texto principal.
     * @param titulo Título de la ventana.
     * @return 0 si el usuario elige "Sí", 1 si elige "No".
     */
    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }

    /**
     * Crea un selector de ficheros personalizado.
     * @param rutaDefecto Carpeta inicial del selector. Puede ser null.
     * @param tipoArchivo Texto descriptivo del tipo de archivo.
     * @param extension Extensión permitida (sin punto). Puede ser null.
     * @return JFileChooser configurado.
     */
    public static JFileChooser crearSelectorFicheros(File rutaDefecto, String tipoArchivo, String extension){
        JFileChooser selectorFichero = new JFileChooser();

        if (rutaDefecto != null){
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if(extension != null){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivo, extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }

    /**
     * Muestra los datos de una lista de pósters en una nueva ventana.
     * @param listaPosters Lista de objetos Poster.
     */
    public static void mostrarDatos(ArrayList<Poster> listaPosters){
        JFrame ventana = new JFrame("Mostrar Datos");
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        StringBuilder texto = new StringBuilder();

        for (Poster unPoster : listaPosters) {
            texto.append("TITULO: ").append(unPoster.getTitulo()).append("\n")
                    .append("AUTOR: ").append(unPoster.getAutor()).append("\n")
                    .append("DIMENSIONES: ").append(unPoster.getDimensiones()).append("\n")
                    .append("FECHA CREADO: ").append(unPoster.getFechaCreado()).append("\n")
                    .append("LENGUAJE: ").append(unPoster.getLenguaje()).append("\n")
                    .append("NUMERO DE COPIAS: ").append(unPoster.getnCopias()).append("\n")
                    .append("ES PUBLICO: ").append(unPoster.isPublico()).append("\n");

            // Información específica según el tipo de póster
            if (unPoster instanceof MusicaPoster){
                texto.append("ESTILO: ").append(((MusicaPoster) unPoster).getEstilo()).append("\n")
                        .append("GRUPO: ").append(((MusicaPoster) unPoster).getGrupo()).append("\n")
                        .append("NACIONALIDAD: ").append(((MusicaPoster) unPoster).getNacionalidad()).append("\n")
                        .append("-----------------------------\n");

            } else if (unPoster instanceof ObraPoster){
                texto.append("EDICION: ").append(((ObraPoster) unPoster).getEdicion()).append("\n")
                        .append("TIPO DE ARTE: ").append(((ObraPoster) unPoster).getTipoArte()).append("\n")
                        .append("PALETA DE COLORES: ").append(((ObraPoster) unPoster).getPaletaColores()).append("\n")
                        .append("-----------------------------\n");

            } else if (unPoster instanceof PeliculaPoster){
                texto.append("GENERO: ").append(((PeliculaPoster) unPoster).getGenero()).append("\n")
                        .append("DIRECTOR: ").append(((PeliculaPoster) unPoster).getDirector()).append("\n")
                        .append("PUNTUACION: ").append(((PeliculaPoster) unPoster).getPuntuacion()).append("\n")
                        .append("-----------------------------\n");
            }
        }

        textPane.setText(texto.toString());

        JScrollPane scroll = new JScrollPane(textPane);
        ventana.add(scroll);

        ventana.setVisible(true);
    }
}
