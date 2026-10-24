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

    private void agregarCampo(JPanel panel,
                              GridBagConstraints c,
                              int fila,
                              String etiqueta,
                              JTextField campo)
    {
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;

        c.gridx = 0;
        c.gridy = fila;
        panel.add(new JLabel(etiqueta), c);

        c.gridx = 1;
        panel.add(campo, c);
    }

    private void calcular() {
        try {
            long favorables = Long.parseLong(txtFavorables.getText().trim());

            long posibles = Long.parseLong(txtPosibles.getText().trim());

            double intercepto =
                    Double.parseDouble(txtIntercepto.getText().trim());

            String clasificacion;

            /*
            * tres niveles de condicionales:
            * 1. verificar que los casos posibles sean mayores que cero.
            * 2. verificar que los casos favorables esten en el rango.
            * 3. clasificar la probabilidad calculada.
            * */
            if (posibles > 0) {
                if (favorables >= 0 && favorables <= posibles) {
                    double probabilidad = (double) favorables / posibles;

                    if (probabilidad == 0.0) {
                        clasificacion = "evento imposible";
                    } else if (probabilidad < 0.5) {
                        clasificacion = "probabilidad baja";
                    } else {
                        clasificacion = "probabilidad media/alta";
                    }

                    grafica.actualizarFuncion(
                            probabilidad,
                            intercepto
                    );

                    lblResultado.setText(
                            "<html>"
                                    + "<b>Probabilidad:</b> "
                                    + String.format("%.4f", probabilidad)
                                    + "<br>"
                                    + "<b>Porcentaje:</b> "
                                    + String.format(
                                    "%.2f%%", probabilidad * 100
                            )
                                    + "<br>"
                                    + "<b>Clasificación:</b> "
                                    + clasificacion
                                    + "<br>"
                                    + "<b>Función:</b> f(x) = "
                                    + String.format("%.4f", probabilidad)
                                    + "x + "
                                    + String.format("%.4f", intercepto)
                                    + "<br><br>"
                                    + "Guardando resultado en Oracle 19c..."
                                    + "</html>"
                    );

                    try {
                        dao.guardar(
                                favorables,
                                posibles,
                                probabilidad,
                                clasificacion,
                                probabilidad,
                                intercepto
                        );

                        lblResultado.setText(
                                lblResultado.getText().replace(
                                        "Guardando resultado en Oracle 19c...",
                                        "Registro guardado en Oracle 19c."
                                )
                        );
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                "El cálculo se realizó, pero no se pudo "
                                        + "guardar en Oracle. Verifica la conexión, "
                                        + "el servicio y la tabla.\n\n"
                                        + ex.getMessage(),
                                "Error de base de datos",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    mostrarError(
                            "Los casos favorables deben estar entre "
                                    + "0 y los casos posibles."
                    );
                }
            } else {
                mostrarError(
                        "Los casos posibles deben ser mayores que cero."
                );
            }
        } catch (NumberFormatException ex) {
            mostrarError(
                    "Ingresa números enteros para los casos y un número "
                            + "válido para el intercepto."
            );
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Datos inválidos",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new Main().setVisible(true)
        );
    }
}   