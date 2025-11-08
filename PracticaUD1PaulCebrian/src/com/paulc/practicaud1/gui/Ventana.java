package com.paulc.practicaud1.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.paulc.practicaud1.base.Poster;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton musicaRadioButton;
    private JRadioButton obraDeArteRadioButton;
    private JRadioButton peliculaRadioButton;
    private JCheckBox publicoCheckBox;
    private JButton insertarImagenButton;
    private JButton mostrarImagenButton;
    private JButton nuevoButton;
    private JButton exportarButton;
    private JButton importatButton;
    private JList list1;
    private JTextField tituloTxt;
    private JTextField AutorTxt;
    private JTextField dimensionesTxt;
    private JTextField lenguajeTxt;
    private JTextField nCopiasTxt;
    private JTextField dirGruArtTxt;
    private JTextField punNacPalTxt;
    private DatePicker fechaDataPicker;
    private JLabel genEstEdiLbl;
    private JLabel dirGruArtLbl;
    private JTextField genEstEdiTxt;
    private JLabel punNacPalLbl;

    public JFrame frame;
    public DefaultListModel<Poster> dlmPoster;

    public Ventana() {
        frame = new JFrame("PosterMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        dlmPoster=new DefaultListModel<Poster>();
        list1.setModel(dlmPoster);
    }
}
