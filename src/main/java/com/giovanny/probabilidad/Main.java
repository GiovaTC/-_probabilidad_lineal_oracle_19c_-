package com.giovanny.probabilidad;

import com.giovanny.probabilidad.dao.ProbabilidadDAO;
import com.giovanny.probabilidad.ui.GraficaLinealPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main extends JFrame {

    private final JTextField txtFavorables = new JTextField("3", 10);
    private final JTextField txtPosibles = new JTextField("10", 10);
    private final JTextField txtIntercepto = new JTextField("0", 10);

    private final JLabel lblResultado = new JLabel("ingresa los datos y pulsa el boton.");

    private final GraficaLinealPanel grafica = new GraficaLinealPanel();
    private final ProbabilidadDAO dao = new ProbabilidadDAO();

    public Main() {
        setTitle("Probabilidad y funcion lineal - Oracle 19c");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 580);
        setLocationRelativeTo(null);
        construirInterfaz();
    }

    private void construirInterfaz() {
    }   
}