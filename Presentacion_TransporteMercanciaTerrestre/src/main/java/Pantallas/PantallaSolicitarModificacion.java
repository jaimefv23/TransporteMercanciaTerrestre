/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import javax.swing.JDialog;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author Jaime
 */
public class PantallaSolicitarModificacion extends JDialog {
    
    private ControlMercanciaTerrestre control;
    private JFrame parentFrame;

    private JCheckBox chkCambioMercancia;
    private JCheckBox chkCambioRuta;

    private JButton btnCancelar;
    private JButton btnAceptar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_CENTRO = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_ROJO = new Color(220, 40, 40);
    private static final Color COLOR_VERDE = new Color(0, 185, 25);

    public PantallaSolicitarModificacion(JFrame parentFrame, ControlMercanciaTerrestre control) {
        super(parentFrame, "SolicitarModificacion", true);
        this.parentFrame = parentFrame;
        this.control = control;
        initComponents();
        this.setLocationRelativeTo(parentFrame);
    }

    private void initComponents() {
        setSize(580, 480);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo general
                g2.setColor(COLOR_FONDO);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Líneas laterales de acento en naranja
                g2.setColor(COLOR_NARANJA);
                g2.fillRect(20, 25, 2, getHeight() - 50);
                g2.fillRect(getWidth() - 22, 25, 2, getHeight() - 50);

                g2.dispose();
            }
        };

        // Banner Superior "Solicitud Modificación"
        JPanel panelBanner = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 22, 22));
                g2.dispose();
            }
        };
        panelBanner.setOpaque(false);
        panelBanner.setBounds(120, 25, 325, 55);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Solicitud Modificación");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Panel Central Oscuro
        JPanel panelCentro = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_CENTRO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));

                // Líneas divisorias internas superior e inferior
                g2.setColor(new Color(200, 110, 20));
                g2.fillRect(0, 35, getWidth(), 1);
                g2.fillRect(0, getHeight() - 35, getWidth(), 1);

                g2.dispose();
            }
        };
        panelCentro.setOpaque(false);
        panelCentro.setBounds(115, 110, 335, 225);

        // Checkbox: Cambio en la Mercancía
        chkCambioMercancia = new JCheckBox("Cambio en la Mercancía", true);
        chkCambioMercancia.setOpaque(false);
        chkCambioMercancia.setFont(new Font("SansSerif", Font.BOLD, 15));
        chkCambioMercancia.setForeground(Color.WHITE);
        chkCambioMercancia.setBounds(30, 65, 260, 30);
        chkCambioMercancia.addActionListener(e -> {
            if (chkCambioMercancia.isSelected()) {
                chkCambioRuta.setSelected(false);
            }
        });
        panelCentro.add(chkCambioMercancia);

        // Checkbox: Cambio de Ruta
        chkCambioRuta = new JCheckBox("Cambio de Ruta", false);
        chkCambioRuta.setOpaque(false);
        chkCambioRuta.setFont(new Font("SansSerif", Font.BOLD, 15));
        chkCambioRuta.setForeground(Color.WHITE);
        chkCambioRuta.setBounds(30, 125, 260, 30);
        chkCambioRuta.addActionListener(e -> {
            if (chkCambioRuta.isSelected()) {
                chkCambioMercancia.setSelected(false);
            }
        });
        panelCentro.add(chkCambioRuta);

        panelPrincipal.add(panelCentro);

        // Botón Cancelar (Rojo)
        btnCancelar = new JButton("Cancelar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_ROJO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnCancelar.setBounds(65, 375, 110, 36);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        panelPrincipal.add(btnCancelar);

        // Botón Aceptar (Verde)
        btnAceptar = new JButton("Aceptar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_VERDE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnAceptar.setBounds(395, 375, 110, 36);
        btnAceptar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setContentAreaFilled(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);
        panelPrincipal.add(btnAceptar);

        setContentPane(panelPrincipal);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.cerrarDialogo();
    }

    private void btnAceptarActionPerformed(ActionEvent evt) {
        control.confirmarSolicitudModificacion(chkCambioMercancia.isSelected(), chkCambioRuta.isSelected());
    }
}
