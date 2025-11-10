package com.paulc.practicaud1.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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
}
