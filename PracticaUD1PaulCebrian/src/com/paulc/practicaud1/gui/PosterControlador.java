package com.paulc.practicaud1.gui;


import com.paulc.practicaud1.base.MusicaPoster;
import com.paulc.practicaud1.base.ObraPoster;
import com.paulc.practicaud1.base.PeliculaPoster;
import com.paulc.practicaud1.base.Poster;
import com.paulc.practicaud1.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class PosterControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private PosterModelo modelo;
    private File ultimaRutaExportada;

    public PosterControlador(Ventana vista, PosterModelo modelo) {
        this.vista=vista;
        this.modelo=modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion "+e.getMessage());
        }

        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    private void addActionListener(ActionListener listener) {
        vista.publicoCheckBox.addActionListener(listener);
        vista.insertarImagenButton.addActionListener(listener);
        vista.mostrarImagenButton.addActionListener(listener);
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.musicaRadioButton.addActionListener(listener);
        vista.obraRadioButton.addActionListener(listener);
        vista.peliculaRadioButton.addActionListener(listener);
    }

    private boolean hayCamposVacios() {
        if (vista.tituloTxt.getText().isEmpty() ||
                vista.autorTxt.getText().isEmpty() ||
                vista.dimensionesTxt.getText().isEmpty() ||
                vista.fechaDataPicker.getText().isEmpty() ||
                vista.lenguajeTxt.getText().isEmpty() ||
                vista.nCopiasTxt.getText().isEmpty() ||
                vista.genEstEdiTxt.getText().isEmpty() ||
                vista.dirGruArtTxt.getText().isEmpty() ||
                vista.punNacPalTxt.getText().isEmpty() ||
                vista.imagen.isEmpty()) {


            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.tituloTxt.setText(null);
        vista.autorTxt.setText(null);
        vista.dimensionesTxt.setText(null);
        vista.fechaDataPicker.setText(null);
        vista.nCopiasTxt.setText(null);
        vista.publicoCheckBox.setSelected(false);
        vista.imagen = null;
        vista.genEstEdiTxt.setText(null);
        vista.dirGruArtTxt.setText(null);
        vista.punNacPalTxt.setText(null);
        vista.tituloTxt.requestFocus();
    }

    private void refrescar() {
        vista.dlmPoster.clear();
        for (Poster poster:modelo.obtenerPosters()) {
            vista.dlmPoster.addElement(poster);
        }
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("posters.conf"));
        ultimaRutaExportada=new File (configuracion.getProperty("ultimaRutaExportado"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion=new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"),"Datos configuracion vehiculos");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp= Util.mensajeConfirmacion("¿Desea cerrar la ventana?","Salir");
        if (resp==JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Poster posterSeleccionado = (Poster) vista.list1.getSelectedValue();
            vista.tituloTxt.setText(posterSeleccionado.getTitulo());
            vista.autorTxt.setText(posterSeleccionado.getAutor());
            vista.dimensionesTxt.setText(posterSeleccionado.getDimensiones());
            vista.fechaDataPicker.setDate(posterSeleccionado.getFechaCreado());
            vista.lenguajeTxt.setText(posterSeleccionado.getLenguaje());
            vista.nCopiasTxt.setText(String.valueOf(posterSeleccionado.getnCopias()));
            vista.publicoCheckBox.setSelected(posterSeleccionado.isPublico());
            vista.imagen = posterSeleccionado.getImagen();
            if (posterSeleccionado instanceof MusicaPoster) {
                vista.musicaRadioButton.doClick();
                vista.genEstEdiTxt.setText(((MusicaPoster) posterSeleccionado).getEstilo());
                vista.dirGruArtTxt.setText(((MusicaPoster) posterSeleccionado).getGrupo());
                vista.punNacPalTxt.setText(((MusicaPoster) posterSeleccionado).getNacionalidad());

            } else if (posterSeleccionado instanceof ObraPoster){
                vista.obraRadioButton.doClick();
                vista.genEstEdiTxt.setText((String.valueOf(((ObraPoster) posterSeleccionado) . getEdicion())));
                vista.dirGruArtTxt.setText(((ObraPoster) posterSeleccionado).getTipoArte());
                vista.punNacPalTxt.setText(((ObraPoster) posterSeleccionado).getPaletaColores());
            } else {
                vista.peliculaRadioButton.doClick();
                vista.genEstEdiTxt.setText(((PeliculaPoster) posterSeleccionado).getDirector());
                vista.dirGruArtTxt.setText(((PeliculaPoster) posterSeleccionado).getDirector());
                vista.punNacPalTxt.setText(String.valueOf(((PeliculaPoster) posterSeleccionado).getPuntuacion()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios \n Matricula\nMarca\nModelo\nFechaMatriculacion\n"
                            +vista.genEstEdiLbl.getText() +"\n" + vista.dirGruArtLbl.getText() + "\n" + vista.punNacPalLbl.getText());
                    break;
                }
                if (modelo.existeTitulo(vista.tituloTxt.getText())) {
                    Util.mensajeError("Ya existe un poster con esa titulo\n"+vista.tituloTxt.getText());
                    break;
                }
                if (vista.musicaRadioButton.isSelected()) {
                    modelo.altaMusicaPoster(vista.tituloTxt.getText(),vista.autorTxt.getText(),vista.dimensionesTxt.getText(),
                            vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),vista.imagen,vista.genEstEdiTxt.getText(),vista.dirGruArtTxt.getText(),vista.punNacPalTxt.getText());
                } else if(vista.obraRadioButton.isSelected()){
                    modelo.altaObraPoster(vista.tituloTxt.getText(),vista.autorTxt.getText(),vista.dimensionesTxt.getText(),
                            vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),vista.imagen,Integer.parseInt(vista.genEstEdiTxt.getText()),vista.dirGruArtTxt.getText(),vista.punNacPalTxt.getText());
                } else {
                    modelo.altaPeliculaPoster(vista.tituloTxt.getText(),vista.autorTxt.getText(),vista.dimensionesTxt.getText(),
                            vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),vista.imagen,vista.genEstEdiTxt.getText(),vista.dirGruArtTxt.getText(),
                            Float.parseFloat(vista.punNacPalTxt.getText()) );
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar": {
                JFileChooser selectorFichero = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();

                        refrescar();
                    }
                }
                break;
            }
            case "Exportar": {
                JFileChooser selectorFichero2 = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }
            case "MusicaPoster": {
                vista.genEstEdiLbl.setText("Estilo");
                vista.dirGruArtTxt.setText("Grupo");
                vista.punNacPalTxt.setText("Nacionalidad");
                break;
            }
            case "ObraPoster": {
                vista.genEstEdiLbl.setText("Edicion");
                vista.dirGruArtTxt.setText("Tipo de arte");
                vista.punNacPalTxt.setText("Paleta de colores");
                break;
            }
            case "PeliculaPoster": {
                vista.genEstEdiLbl.setText("Genero");
                vista.dirGruArtTxt.setText("Director");
                vista.punNacPalTxt.setText("Puntuacion");
                break;
            }



        }
    }

    //no los uso

    @Override
    public void windowOpened(WindowEvent e) {

    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}