package com.giovanny.probabilidad.ui;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class GraficaLinealPanel extends JPanel {

    private double pendiente = 0.3;
    private double intercepto = 0.0;

    public GraficaLinealPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new java.awt.Dimension(600, 360));
    }

    public void actualizarFuncion(double m, double b) {
        pendiente = m;
        intercepto = b;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)  g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int left = 58;
        int right = 25;
        int top = 35;
        int bottom = 48;

        int w = getWidth() - left - right;
        int h = getHeight() - top - bottom;

        if (w <= 0 || h <= 0) {
            g2.dispose();
            return;
        }

        // Dominio horizontal: x desde 0 hasta 10.
        double xMin = 0;
        double xMax = 10;

        double y1 = pendiente * xMin + intercepto;
        double y2 = pendiente * xMax + intercepto;

        double yMin = Math.min(0, Math.min(y1, y2));
        double yMax = Math.max(1, Math.max(y1, y2));

        if (Math.abs(yMax - yMin) < 1e-9) {
            yMax = yMin + 1;
        }

        double pad = (yMax - yMin) * 0.12;
        yMin -= pad;
        yMax += pad;

        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
        g2.setColor(new Color(225, 225, 225));

        // Cuadrícula y etiquetas de los ejes.
        for (int i = 0; i <= 10; i++) {
            int x = left + i * w / 10;
            int y = top + h - i * h / 10;

            g2.drawLine(x, top, x, top + h);
            g2.drawLine(left, y, left + w, y);

            double yVal = yMin + (yMax - yMin) * i / 10.0;

            g2.setColor(Color.DARK_GRAY);
            g2.drawString(String.format("%.2f", yVal), 5, y + 4);
            g2.drawString(Integer.toString(i), x - 3, top + h + 18);

            g2.setColor(new Color(225, 225, 225));
        }

        // Ejes.
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(left, top + h, left + w, top + h);
        g2.drawLine(left, top, left, top + h);

        // Títulos.
        g2.setFont(new Font("SansSerif", Font.BOLD, 13));
        g2.drawString(
                String.format("f(x) = %.4f x + %.4f", pendiente, intercepto),
                left,
                20
        );
        g2.drawString("x", left + w / 2, getHeight() - 10);
        g2.drawString("f(x)", 10, top - 8);

        // Recta que representa f(x) = mx + b.
        int px1 = left;
        int py1 = mapY(pendiente * xMin + intercepto, yMin, yMax, top, h);
        int px2 = left + w;
        int py2 = mapY(pendiente * xMax + intercepto, yMin, yMax, top, h);

        g2.setColor(new Color(30, 110, 210));
        g2.setStroke(new BasicStroke(3f));
        g2.drawLine(px1, py1, px2, py2);

        g2.dispose();
    }

    private int mapY(
            double value,
            double yMin,
            double yMax,
            int top,
            int h
    ) {
        return top + h
                - (int) Math.round(
                (value - yMin) / (yMax - yMin) * h
        );
    }
}
