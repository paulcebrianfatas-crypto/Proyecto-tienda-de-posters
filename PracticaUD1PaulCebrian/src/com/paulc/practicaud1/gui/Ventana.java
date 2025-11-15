package com.paulc.practicaud1.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.paulc.practicaud1.base.Poster;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton musicaRadioButton;
    public JRadioButton obraRadioButton;
    public JRadioButton peliculaRadioButton;
    public JCheckBox publicoCheckBox;
    public JButton insertarImagenButton;
    public JButton mostrarImagenButton;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public JTextField tituloTxt;
    public JTextField autorTxt;
    public JTextField lenguajeTxt;
    public JTextField nCopiasTxt;
    public JTextField dirGruArtTxt;
    public JTextField punNacPalTxt;
    public DatePicker fechaDataPicker;
    public JLabel genEstEdiLbl;
    public JLabel dirGruArtLbl;
    public JTextField genEstEdiTxt;
    public JLabel punNacPalLbl;
    public JComboBox dimensionesComboBox;
    private JLabel tituloLbl;
    private JLabel autorLbl;
    private JLabel dimensionesLbl;
    private JLabel fechaCreadoLbl;
    private JLabel lenguajeLbl;
    private JLabel numeroCopiasLbl;
    public JButton actualizarButton;
    public byte[] imagen;
    public JMenuItem itemLimpiar;
    public JMenuItem itemMostrar;

    public JFrame frame;
    public DefaultListModel<Poster> dlmPoster;

    public Ventana() {
        frame = new JFrame("PosterMVC");


        panel1 = new JPanel(new BorderLayout(10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel1.setBackground(Color.CYAN);


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

    private void anyadirDimensionesComboBox() {
        dimensionesComboBox.addItem("400x400");
        dimensionesComboBox.addItem("600x200");
        dimensionesComboBox.addItem("200x600");
        dimensionesComboBox.addItem("200x200");
    }

    private void initComponents() {
        dlmPoster = new DefaultListModel<>();
        list1.setModel(dlmPoster);
    }
    private JButton modificarBoton(JButton boton){
        boton.setBackground(new Color(0, 100, 0));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(150, 30));
        return boton;
    }
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
    public void crearPanelInferior(){
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(null);
        panelInferior.setBackground(Color.CYAN);



        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(Color.CYAN);
        panelBotones.add(modificarBoton(insertarImagenButton));
        panelBotones.add(modificarBoton(mostrarImagenButton));
        panelBotones.add(modificarBoton(nuevoButton) );
        panelBotones.add(modificarBoton(actualizarButton));
        panelBotones.add(modificarBoton(exportarButton) );
        panelBotones.add(modificarBoton(importarButton) );


        panelInferior.add(panelBotones);
        JScrollPane scroll = new JScrollPane(list1);
        scroll.setPreferredSize(new Dimension(500, 150));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInferior.add(scroll);

        panel1.add(panelInferior, BorderLayout.SOUTH);
    }
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
