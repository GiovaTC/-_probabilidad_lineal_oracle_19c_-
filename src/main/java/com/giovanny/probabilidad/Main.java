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
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createTitledBorder("datos de entrada"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.anchor = GridBagConstraints.WEST;

        agregarCampo(
                formulario, c, 0, "casos favorables:", txtFavorables);
        
        agregarCampo(
                formulario,c, 1, "casos posibles:", txtPosibles);
        
        agregarCampo(
                formulario, c, 2, "intercepto b:", txtIntercepto);
        
        JButton btnCalcular = new JButton("calcular, graficar y guardar");
        btnCalcular.addActionListener(e -> calcular());

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        formulario.add(btnCalcular, c);

        JPanel panelResultado = new JPanel(new BorderLayout(8, 8));
        panelResultado.setBorder(BorderFactory.createTitledBorder("resultado"));

        lblResultado.setVerticalAlignment(SwingConstants.TOP);
        lblResultado.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panelResultado.add(lblResultado, BorderLayout.CENTER);

        JPanel izquierda = new JPanel(new BorderLayout(8, 8));
        izquierda.setPreferredSize(new Dimension(285,  500));
        izquierda.add(formulario, BorderLayout.NORTH);
        izquierda.add(panelResultado, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(izquierda,  BorderLayout.WEST);
        add(grafica, BorderLayout.CENTER);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }   

    private void agregarCampo(JPanel formulario, GridBagConstraints c, int i, String s, JTextField txtFavorables) {
    }

    private void calcular() {
        
    }
}