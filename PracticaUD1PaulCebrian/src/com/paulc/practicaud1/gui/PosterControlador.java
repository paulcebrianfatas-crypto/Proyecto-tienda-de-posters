package com.paulc.practicaud1.gui;


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
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.fechaMatriculacionDPicker.setText(null);
        vista.plazasKmsTxt.setText(null);
        vista.matriculaTxt.requestFocus();
    }

    private void refrescar() {
        vista.dlmVehiculo.clear();
        for (Vehiculo vehiculo:modelo.obtenerVehiculos()) {
            vista.dlmVehiculo.addElement(vehiculo);
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
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada=new File (configuracion.getProperty("ultimaRutaExportada"));
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
            Vehiculo vehiculoSeleccionado = (Vehiculo) vista.list1.getSelectedValue();
            vista.matriculaTxt.setText(vehiculoSeleccionado.getMatricula());
            vista.marcaTxt.setText(vehiculoSeleccionado.getMarca());
            vista.modeloTxt.setText(vehiculoSeleccionado.getModelo());
            vista.fechaMatriculacionDPicker.setDate(vehiculoSeleccionado.getFechaMatriculacion());
            if (vehiculoSeleccionado instanceof Coche) {
                vista.cocheRadioButton.doClick();
                vista.plazasKmsTxt.setText(String.valueOf(((Coche) vehiculoSeleccionado).getNumPlazas()));
            } else {
                vista.motoRadioButton.doClick();
                vista.plazasKmsTxt.setText(String.valueOf(((Moto) vehiculoSeleccionado).getKms()));
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
                            +vista.plazasKmsLbl.getText());
                    break;
                }
                if (modelo.existeMatricula(vista.matriculaTxt.getText())) {
                    Util.mensajeError("Ya existe un coche con esa matricula\n"+vista.matriculaTxt.getText());
                    break;
                }
                if (vista.cocheRadioButton.isSelected()) {
                    modelo.altaCoche(vista.matriculaTxt.getText(),vista.marcaTxt.getText(),vista.modeloTxt.getText(),
                            vista.fechaMatriculacionDPicker.getDate(), Integer.parseInt(vista.plazasKmsTxt.getText()));
                } else {
                    modelo.altaMoto(vista.matriculaTxt.getText(),vista.marcaTxt.getText(),vista.modeloTxt.getText(),
                            vista.fechaMatriculacionDPicker.getDate(), Double.parseDouble(vista.plazasKmsTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML","xml");
                int opt=selectorFichero.showOpenDialog(null);
                if (opt==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML","xml");
                int opt2=selectorFichero2.showSaveDialog(null);
                if (opt2==JFileChooser.APPROVE_OPTION) {
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
            case "Moto":
                vista.plazasKmsLbl.setText("Kms");
                break;
            case "Coche":
                vista.plazasKmsLbl.setText("N Plazas");
                break;
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