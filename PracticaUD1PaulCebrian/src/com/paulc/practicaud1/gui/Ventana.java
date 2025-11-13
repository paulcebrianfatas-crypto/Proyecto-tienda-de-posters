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
    public byte[] imagen;

    public JFrame frame;
    public DefaultListModel<Poster> dlmPoster;

    public Ventana() {
        frame = new JFrame("PosterMVC");

        // Panel principal con BorderLayout
        panel1 = new JPanel(new BorderLayout(10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ================================
        // PANEL 1: Tipo de Póster
        // ================================
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

        // ================================
        // PANEL 2: Datos del Póster (reordenado)
        // ================================
        JPanel datosPoster = new JPanel(new GridLayout(0, 2, 5, 5));
        datosPoster.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Datos del Póster",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)
        ));

        // Orden indicado
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
        datosPoster.add(new JLabel("Público:"));
        datosPoster.add(publicoCheckBox);

        panel1.add(datosPoster, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(null);


        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(insertarImagenButton);
        panelBotones.add(mostrarImagenButton);
        panelBotones.add(nuevoButton);
        panelBotones.add(exportarButton);
        panelBotones.add(importarButton);

        panelInferior.add(panelBotones);
        JScrollPane scroll = new JScrollPane(list1);
        scroll.setPreferredSize(new Dimension(500, 150));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInferior.add(scroll);

        panel1.add(panelInferior, BorderLayout.SOUTH);


        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
}
