package com.paulc.practicaud1.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.paulc.practicaud1.base.Poster;

import javax.swing.*;

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
    public JTextField dimensionesTxt;
    public JTextField lenguajeTxt;
    public JTextField nCopiasTxt;
    public JTextField dirGruArtTxt;
    public JTextField punNacPalTxt;
    public DatePicker fechaDataPicker;
    public JLabel genEstEdiLbl;
    public JLabel dirGruArtLbl;
    public JTextField genEstEdiTxt;
    public JLabel punNacPalLbl;
    public byte[] imagen;

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
