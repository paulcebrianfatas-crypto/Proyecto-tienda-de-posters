package com.paulc.practicaud1;

import com.paulc.practicaud1.base.Poster;
import com.paulc.practicaud1.gui.PosterControlador;
import com.paulc.practicaud1.gui.PosterModelo;
import com.paulc.practicaud1.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        PosterModelo modelo = new PosterModelo();
        PosterControlador controlador = new PosterControlador(vista,modelo);
    }
}
