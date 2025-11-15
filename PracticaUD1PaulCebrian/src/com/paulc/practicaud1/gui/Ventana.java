package com.paulc.practicaud1.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.paulc.practicaud1.base.Poster;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Clase que representa la ventana principal de la aplicación PosterMVC.
 * Se encarga de gestionar y organizar los componentes gráficos (Swing),
 * estructurados en distintos paneles.
 */
public class Ventana {
    /** Panel principal que contiene toda la estructura visual */
    private JPanel panel1;

    /** Botones de selección del tipo de póster */
    public JRadioButton musicaRadioButton;
    public JRadioButton obraRadioButton;
    public JRadioButton peliculaRadioButton;

    /** Checkbox que indica si el póster es para público general */
    public JCheckBox publicoCheckBox;

    /** Botones de acciones principales */
    public JButton insertarImagenButton;
    public JButton mostrarImagenButton;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JButton actualizarButton;

    /** Lista que mostrará los pósters registrados */
    public JList list1;

    /** Campos de texto para introducir datos del póster */
    public JTextField tituloTxt;
    public JTextField autorTxt;
    public JTextField lenguajeTxt;
    public JTextField nCopiasTxt;
    public JTextField dirGruArtTxt;
    public JTextField punNacPalTxt;
    public JTextField genEstEdiTxt;

    /** Selector de fecha */
    public DatePicker fechaDataPicker;

    /** Etiquetas */
    public JLabel genEstEdiLbl;
    public JLabel dirGruArtLbl;
    public JLabel punNacPalLbl;
    private JLabel tituloLbl;
    private JLabel autorLbl;
    private JLabel dimensionesLbl;
    private JLabel fechaCreadoLbl;
    private JLabel lenguajeLbl;
    private JLabel numeroCopiasLbl;

    /** ComboBox para seleccionar dimensiones del póster */
    public JComboBox dimensionesComboBox;

    /** Contenedor de la imagen en bytes */
    public byte[] imagen;

    /** Opciones adicionales del menú */
    public JMenuItem itemLimpiar;
    public JMenuItem itemMostrar;

    /** Marco principal de la ventana */
    public JFrame frame;

    /** Modelo de lista que almacena objetos Poster */
    public DefaultListModel<Poster> dlmPoster;

    /**
     * Constructor que inicializa la ventana y sus componentes.
     */
    public Ventana() {
        frame = new JFrame("PosterMVC");

        panel1 = new JPanel(new BorderLayout(10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel1.setBackground(Color.CYAN);

        // Creación y organización de los paneles principales
        crearPanelTipoPoster();
        crearPanelDatos();
        crearPanelInferior();

        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        crearBarraMenu();
        initComponents();
        anyadirDimensionesComboBox();
    }

    /**
     * Añade las dimensiones disponibles al comboBox correspondiente.
     */
    private void anyadirDimensionesComboBox() {
        dimensionesComboBox.addItem("400x400");
        dimensionesComboBox.addItem("600x200");
        dimensionesComboBox.addItem("200x600");
        dimensionesComboBox.addItem("200x200");
    }

    /**
     * Inicializa el modelo de la lista que mostrará los pósters.
     */
    private void initComponents() {
        dlmPoster = new DefaultListModel<>();
        list1.setModel(dlmPoster);
    }

    /**
     * Aplica un estilo uniforme a los botones.
     * @param boton botón a modificar
     * @return el botón con el nuevo estilo aplicado
     */
    private JButton modificarBoton(JButton boton){
        boton.setBackground(new Color(0, 100, 0));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(150, 30));
        return boton;
    }

    /**
     * Crea la barra de menú superior con opciones adicionales.
     */
    public void crearBarraMenu() {
        JMenuBar barra= new JMenuBar();
        JMenu menu = new JMenu("Más opciones");
        itemLimpiar = new JMenuItem("Limpiar");
        itemMostrar = new JMenuItem("Mostrar Datos");

        itemLimpiar.setActionCommand("limpiar");
        itemMostrar.setActionCommand("mostrarDatos");

        menu.add(itemLimpiar);
        menu.add(itemMostrar);

        barra.add(menu);
        frame.setJMenuBar(barra);
    }

    /**
     * Crea el panel inferior donde se ubican los botones y la lista.
     */
    public void crearPanelInferior(){
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(null);
        panelInferior.setBackground(Color.CYAN);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(Color.CYAN);
        panelBotones.add(modificarBoton(insertarImagenButton));
        panelBotones.add(modificarBoton(mostrarImagenButton));
        panelBotones.add(modificarBoton(nuevoButton));
        panelBotones.add(modificarBoton(actualizarButton));
        panelBotones.add(modificarBoton(exportarButton));
        panelBotones.add(modificarBoton(importarButton));

        panelInferior.add(panelBotones);

        JScrollPane scroll = new JScrollPane(list1);
        scroll.setPreferredSize(new Dimension(500, 150));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInferior.add(scroll);

        panel1.add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Crea el panel central donde se muestran los campos del formulario.
     */
    public void crearPanelDatos(){
        JPanel datosPoster = new JPanel(new GridLayout(0, 2, 5, 5));
        datosPoster.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Datos del Póster",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)
        ));

        JLabel publicoLbl = new JLabel("Público:");
        publicoLbl.setFont(new Font("Calibri", Font.BOLD,18));

        datosPoster.add(tituloLbl);
        datosPoster.add(tituloTxt);
        datosPoster.add(autorLbl);
        datosPoster.add(autorTxt);
        datosPoster.add(dimensionesLbl);
        datosPoster.add(dimensionesComboBox);
        datosPoster.add(fechaCreadoLbl);
        datosPoster.add(fechaDataPicker);
        datosPoster.add(lenguajeLbl);
        datosPoster.add(lenguajeTxt);
        datosPoster.add(numeroCopiasLbl);
        datosPoster.add(nCopiasTxt);
        datosPoster.add(genEstEdiLbl);
        datosPoster.add(genEstEdiTxt);
        datosPoster.add(dirGruArtLbl);
        datosPoster.add(dirGruArtTxt);
        datosPoster.add(punNacPalLbl);
        datosPoster.add(punNacPalTxt);
        datosPoster.add(publicoLbl);
        datosPoster.add(publicoCheckBox);

        panel1.add(datosPoster, BorderLayout.CENTER);
    }

    /**
     * Crea el panel superior donde se selecciona el tipo de póster.
     */
    public void crearPanelTipoPoster(){
        JPanel tipoPoster = new JPanel(new FlowLayout());
        tipoPoster.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Tipo de Póster",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)
        ));

        tipoPoster.add(musicaRadioButton);
        tipoPoster.add(obraRadioButton);
        tipoPoster.add(peliculaRadioButton);
        panel1.add(tipoPoster, BorderLayout.NORTH);
    }
}
