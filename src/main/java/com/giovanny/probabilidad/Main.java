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
        
        
    }

    private void agregarCampo(JPanel formulario, GridBagConstraints c, int i, String s, JTextField txtFavorables) {
    }

    private void calcular() {
        
    }
}