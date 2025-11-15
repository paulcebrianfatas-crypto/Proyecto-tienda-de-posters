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

public class Util {
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,mensaje,"Error",JOptionPane.ERROR_MESSAGE);

    }


    public static void mensajeExtra(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,"Mensaje",JOptionPane.PLAIN_MESSAGE);
    }
    public static void verImagen(Image imagen){

        JLabel lbl = new JLabel(new ImageIcon(imagen));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);


        JOptionPane.showMessageDialog(null, lbl, "Ver imagen", JOptionPane.PLAIN_MESSAGE);
    }

    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }



    public static JFileChooser crearSelectorFicheros(File rutaDefecto , String tipoArchivo, String extension){
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto!=null){
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if(extension!=null){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivo,extension );
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }
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
            if (unPoster instanceof MusicaPoster){
                texto.append("ESTILO:").append(((MusicaPoster) unPoster).getEstilo()).append("\n")
                        .append("GRUPO:").append(((MusicaPoster) unPoster).getGrupo()).append("\n")
                        .append("NACIONALIDAD:").append(((MusicaPoster) unPoster).getNacionalidad()).append("\n")
                        .append("-----------------------------\n");
            }else if (unPoster instanceof ObraPoster){
                texto.append("EDICION:").append(((ObraPoster) unPoster).getEdicion()).append("\n")
                        .append("TIPO DE ARTE:").append(((ObraPoster) unPoster).getTipoArte()).append("\n")
                        .append("PALETA DE COLORES:").append(((ObraPoster) unPoster).getPaletaColores()).append("\n")
                        .append("-----------------------------\n");
            }else {
                texto.append("GENERO:").append(((PeliculaPoster) unPoster).getGenero()).append("\n")
                        .append("DIRECTOR:").append(((PeliculaPoster) unPoster).getGenero()).append("\n")
                        .append("PUNTUACION:").append(((PeliculaPoster) unPoster).getGenero()).append("\n")
                        .append("-----------------------------\n");

            }

        }

        textPane.setText(texto.toString());

        JScrollPane scroll = new JScrollPane(textPane);
        ventana.add(scroll);

        ventana.setVisible(true);
    }
}
