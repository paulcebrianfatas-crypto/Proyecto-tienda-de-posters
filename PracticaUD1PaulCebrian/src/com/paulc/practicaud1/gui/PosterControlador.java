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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

/**
 * Controlador principal que gestiona la interacción entre la vista {@link Ventana}
 * y el modelo {@link PosterModelo}. Se encarga de manejar eventos de botones,
 * listas y la ventana, así como operaciones de creación, importación, exportación
 * y actualización de Posters.
 *
 * Implementa:
 *  - ActionListener → para botones y acciones de menú
 *  - ListSelectionListener → para seleccionar elementos en la lista
 *  - WindowListener → para eventos de la ventana
 */
public class PosterControlador implements ActionListener, ListSelectionListener, WindowListener {

    /**
     * Vista principal del programa
     */
    private Ventana vista;

    /**
     * Modelo con la lógica y almacenamiento de posters
     */
    private PosterModelo modelo;

    /**
     * Última ruta utilizada para exportar e importar archivos
     */
    private File ultimaRutaExportada;

    /**
     * Constructor del controlador. Asocia vista y modelo,
     * carga configuración previa y registra los listeners.
     *
     * @param vista  interfaz gráfica
     * @param modelo lógica y datos
     */
    public PosterControlador(Ventana vista, PosterModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion " + e.getMessage());
        }

        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    /**
     * Registra el ActionListener para todos los botones y opciones de menú de la vista.
     *
     * @param listener controlador de eventos
     */
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
        vista.actualizarButton.addActionListener(listener);
        vista.itemLimpiar.addActionListener(listener);
        vista.itemMostrar.addActionListener(listener);
    }

    /**
     * Comprueba si existen campos obligatorios vacíos en el formulario.
     *
     * @return true si falta algún dato, false en caso contrario
     */
    private boolean hayCamposVacios() {
        if (vista.tituloTxt.getText().isEmpty() ||
                vista.autorTxt.getText().isEmpty() ||
                vista.dimensionesComboBox.getSelectedItem() == null ||
                vista.fechaDataPicker.getText().isEmpty() ||
                vista.lenguajeTxt.getText().isEmpty() ||
                vista.nCopiasTxt.getText().isEmpty() ||
                vista.genEstEdiTxt.getText().isEmpty() ||
                vista.dirGruArtTxt.getText().isEmpty() ||
                vista.punNacPalTxt.getText().isEmpty() ||
                vista.imagen == null) {

            return true;
        }
        return false;
    }

    /**
     * Limpia todos los campos de la vista y reinicia el foco.
     */
    private void limpiarCampos() {
        vista.tituloTxt.setText(null);
        vista.autorTxt.setText(null);
        vista.dimensionesComboBox.setSelectedIndex(0);
        vista.fechaDataPicker.setText(null);
        vista.lenguajeTxt.setText(null);
        vista.nCopiasTxt.setText(null);
        vista.publicoCheckBox.setSelected(false);
        vista.imagen = null;
        vista.genEstEdiTxt.setText(null);
        vista.dirGruArtTxt.setText(null);
        vista.punNacPalTxt.setText(null);
        vista.tituloTxt.requestFocus();
    }

    /**
     * Recarga la lista de posters ordenados desde el modelo hacia la vista.
     */
    private void refrescar() {
        vista.dlmPoster.clear();
        modelo.ordenar();
        for (Poster poster : modelo.getListaPosters()) {
            vista.dlmPoster.addElement(poster);
        }
    }

    /**
     * Registra el WindowListener en la ventana.
     */
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    /**
     * Añade listener de selección a la lista de posters.
     */
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    /**
     * Carga parámetros previos de configuración desde archivo.
     *
     * @throws IOException si el archivo no existe o no se puede leer
     */
    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("posters.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportado"));
    }

    /**
     * Actualiza internamente la última ruta utilizada.
     */
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }



    /**
     * Guarda parámetros de configuración en un archivo.
     *
     * @throws IOException si ocurre algún error al guardar
     */
    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"), "Datos configuracion vehiculos");
    }

    /**
     * Evento al intentar cerrar la ventana.
     * Solicita confirmación y guarda configuración si procede.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    /**
     * Evento cuando se selecciona un elemento de la lista.
     * Carga sus datos en el formulario y ajusta los campos según tipo de poster.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Poster posterSeleccionado = (Poster) vista.list1.getSelectedValue();

            // Cargar datos comunes
            vista.tituloTxt.setText(posterSeleccionado.getTitulo());
            vista.autorTxt.setText(posterSeleccionado.getAutor());
            vista.dimensionesComboBox.setSelectedItem(posterSeleccionado.getDimensiones());
            vista.fechaDataPicker.setDate(posterSeleccionado.getFechaCreado());
            vista.lenguajeTxt.setText(posterSeleccionado.getLenguaje());
            vista.nCopiasTxt.setText(String.valueOf(posterSeleccionado.getnCopias()));
            vista.publicoCheckBox.setSelected(posterSeleccionado.isPublico());
            vista.imagen = posterSeleccionado.getImagen();

            // Cargar datos específicos según tipo de poster
            if (posterSeleccionado instanceof MusicaPoster) {
                vista.musicaRadioButton.doClick();
                vista.genEstEdiTxt.setText(((MusicaPoster) posterSeleccionado).getEstilo());
                vista.dirGruArtTxt.setText(((MusicaPoster) posterSeleccionado).getGrupo());
                vista.punNacPalTxt.setText(((MusicaPoster) posterSeleccionado).getNacionalidad());

            } else if (posterSeleccionado instanceof ObraPoster) {
                vista.obraRadioButton.doClick();
                vista.genEstEdiTxt.setText(String.valueOf(((ObraPoster) posterSeleccionado).getEdicion()));
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

    /**
     * Maneja todas las acciones de los botones y menú de la interfaz.
     *
     * @param e evento generado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {

            // --- CREAR UN NUEVO POSTER ---
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios \n Matricula\nMarca\nModelo\nFechaMatriculacion\n"
                            +vista.genEstEdiLbl.getText() +"\n" + vista.dirGruArtLbl.getText() + "\n" + vista.punNacPalLbl.getText());
                    break;
                }
                if (modelo.existeTitulo(vista.tituloTxt.getText())) {
                    Util.mensajeError("Ya existe un poster con ese titulo\n" + vista.tituloTxt.getText());
                    break;
                }

                // Lógica según tipo de poster
                if (vista.musicaRadioButton.isSelected()) {
                    modelo.altaMusicaPoster(
                            vista.tituloTxt.getText(),
                            vista.autorTxt.getText(),
                            vista.dimensionesComboBox.getSelectedItem().toString(),
                            vista.fechaDataPicker.getDate(),
                            vista.lenguajeTxt.getText(),
                            Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),
                            vista.imagen,
                            vista.genEstEdiTxt.getText(),
                            vista.dirGruArtTxt.getText(),
                            vista.punNacPalTxt.getText()
                    );
                } else if (vista.obraRadioButton.isSelected()) {
                    modelo.altaObraPoster(
                            vista.tituloTxt.getText(),
                            vista.autorTxt.getText(),
                            vista.dimensionesComboBox.getSelectedItem().toString(),
                            vista.fechaDataPicker.getDate(),
                            vista.lenguajeTxt.getText(),
                            Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),
                            vista.imagen,
                            Integer.parseInt(vista.genEstEdiTxt.getText()),
                            vista.dirGruArtTxt.getText(),
                            vista.punNacPalTxt.getText()
                    );
                } else {
                    modelo.altaPeliculaPoster(
                            vista.tituloTxt.getText(),
                            vista.autorTxt.getText(),
                            vista.dimensionesComboBox.getSelectedItem().toString(),
                            vista.fechaDataPicker.getDate(),
                            vista.lenguajeTxt.getText(),
                            Integer.parseInt(vista.nCopiasTxt.getText()),
                            vista.publicoCheckBox.isSelected(),
                            vista.imagen,
                            vista.genEstEdiTxt.getText(),
                            vista.dirGruArtTxt.getText(),
                            Float.parseFloat(vista.punNacPalTxt.getText())
                    );
                }

                limpiarCampos();
                refrescar();
                break;

            // --- IMPORTAR XML ---
            case "Importar": {
                JFileChooser selectorFichero = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);

                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            }

            // --- EXPORTAR XML ---
            case "Exportar": {
                JFileChooser selectorFichero2 = Util.crearSelectorFicheros(ultimaRutaExportada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);

                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }

            // --- INSERTAR IMAGEN ---
            case "Insertar Imagen": {
                JFileChooser selectorFichero3 = Util.crearSelectorFicheros(ultimaRutaExportada, "Imagen PNG", "png");
                int opc = selectorFichero3.showOpenDialog(null);

                if (opc == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = selectorFichero3.getSelectedFile();

                    try {
                        byte[] bytesImagen = Files.readAllBytes(archivoSeleccionado.toPath());
                        vista.imagen = bytesImagen;
                        Util.mensajeExtra("Imagen importada correctamente:\n" + archivoSeleccionado.getName());
                    } catch (IOException ex) {
                        Util.mensajeError("Error al leer la imagen: " + ex.getMessage());
                    }
                }
                break;
            }

            // --- MOSTRAR IMAGEN ---
            case "Mostrar Imagen": {
                if (vista.imagen != null) {
                    try {
                        ImageIcon icon = new ImageIcon(vista.imagen);
                        String[] dimensions = vista.dimensionesComboBox.getSelectedItem().toString().split("x");

                        int wight = Integer.parseInt(dimensions[0]);
                        int height = Integer.parseInt(dimensions[1]);

                        Image imagenEscalada = icon.getImage().getScaledInstance(wight, height, Image.SCALE_SMOOTH);
                        Util.verImagen(imagenEscalada);

                    } catch (Exception e1) {
                        Util.mensajeError("Dimensiones tiene mal formato: wight x height");
                    }
                } else {
                    Util.mensajeError("Error al mostrar la imagen: No hay imagen seleccionada");
                }
                break;
            }

            // --- ACTUALIZAR POSTER ---
            case "Actualizar": {
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios \n Matricula\nMarca\nModelo\nFechaMatriculacion\n"
                            +vista.genEstEdiLbl.getText() +"\n" + vista.dirGruArtLbl.getText() + "\n" + vista.punNacPalLbl.getText());
                    break;
                }

                Poster poster = new Poster(
                        vista.tituloTxt.getText(),
                        vista.autorTxt.getText(),
                        vista.dimensionesComboBox.getSelectedItem().toString(),
                        vista.fechaDataPicker.getDate(),
                        vista.lenguajeTxt.getText(),
                        Integer.parseInt(vista.nCopiasTxt.getText()),
                        vista.publicoCheckBox.isSelected(),
                        vista.imagen
                );

                if (modelo.actualizar(poster)) {

                    // Se vuelve a dar de alta según tipo (modifica datos específicos)
                    if (vista.musicaRadioButton.isSelected()) {
                        modelo.altaMusicaPoster(vista.tituloTxt.getText(), vista.autorTxt.getText(), vista.dimensionesComboBox.getSelectedItem().toString(),
                                vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()), vista.publicoCheckBox.isSelected(),
                                vista.imagen, vista.genEstEdiTxt.getText(), vista.dirGruArtTxt.getText(), vista.punNacPalTxt.getText());
                        limpiarCampos();
                        refrescar();
                    } else if (vista.obraRadioButton.isSelected()) {
                        modelo.altaObraPoster(vista.tituloTxt.getText(), vista.autorTxt.getText(), vista.dimensionesComboBox.getSelectedItem().toString(),
                                vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()), vista.publicoCheckBox.isSelected(),
                                vista.imagen, Integer.parseInt(vista.genEstEdiTxt.getText()), vista.dirGruArtTxt.getText(), vista.punNacPalTxt.getText());
                        limpiarCampos();
                        refrescar();
                    } else {
                        modelo.altaPeliculaPoster(vista.tituloTxt.getText(), vista.autorTxt.getText(), vista.dimensionesComboBox.getSelectedItem().toString(),
                                vista.fechaDataPicker.getDate(), vista.lenguajeTxt.getText(), Integer.parseInt(vista.nCopiasTxt.getText()), vista.publicoCheckBox.isSelected(),
                                vista.imagen, vista.genEstEdiTxt.getText(), vista.dirGruArtTxt.getText(), Float.parseFloat(vista.punNacPalTxt.getText())); limpiarCampos(); refrescar();
                    }

                    limpiarCampos();
                    refrescar();

                } else {
                    Util.mensajeError("Error al modificar el mensaje, no se encontró ese título de poster");
                }
                break;
            }

            // --- CAMBIO DE RADIOBUTTONS ---
            case "Musica":
                vista.genEstEdiLbl.setText("Estilo");
                vista.dirGruArtLbl.setText("Grupo");
                vista.punNacPalLbl.setText("Nacionalidad");
                break;

            case "Obra de Arte":
                vista.genEstEdiLbl.setText("Edición");
                vista.dirGruArtLbl.setText("Tipo de arte");
                vista.punNacPalLbl.setText("Paleta de colores");
                break;

            case "Pelicula":
                vista.genEstEdiLbl.setText("Género");
                vista.dirGruArtLbl.setText("Director");
                vista.punNacPalLbl.setText("Puntuación");
                break;

            // --- OPCIÓN LIMPIAR MODELO ---
            case "limpiar":
                if (!modelo.getListaPosters().isEmpty()) {
                    modelo.limpiar();
                    limpiarCampos();
                    refrescar();
                } else {
                    Util.mensajeError("No hay datos para borrar");
                }
                break;

            // --- MOSTRAR DATOS EN CONSOLA ---
            case "mostrarDatos":
                if (!modelo.getListaPosters().isEmpty()) {
                    modelo.mostrarDatos();
                } else {
                    Util.mensajeError("No hay datos creados");
                }
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