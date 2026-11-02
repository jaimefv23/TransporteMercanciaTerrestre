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
public class PantallaMotivoCancelacion extends JDialog {
    
    private ControlMercanciaTerrestre control;
    private JFrame parentFrame;
    private boolean esRechazo;

    private JTextArea txtMotivo;
    private JButton btnCancelar;
    private JButton btnGuardar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_CAMPO_TEXTO = new Color(225, 225, 225);
    private static final Color COLOR_ROJO = new Color(220, 40, 40);
    private static final Color COLOR_VERDE = new Color(0, 185, 25);

    public PantallaMotivoCancelacion(JFrame parentFrame, ControlMercanciaTerrestre control, boolean esRechazo) {
        super(parentFrame, "MotivoCancelacion", true);
        this.parentFrame = parentFrame;
        this.control = control;
        this.esRechazo = esRechazo;
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

        // Banner Superior "Motivo Cancelación"
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

        JLabel lblTituloGeneral = new JLabel("Motivo Cancelación");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Área de texto del motivo
        txtMotivo = new JTextArea("El Cliente No Estuvo De Acuerdo Con Las Condiciones");
        txtMotivo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtMotivo.setForeground(Color.BLACK);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        txtMotivo.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Contenedor redondeado para el JTextArea
        JScrollPane scrollMotivo = new JScrollPane(txtMotivo) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CAMPO_TEXTO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        scrollMotivo.setOpaque(false);
        scrollMotivo.getViewport().setOpaque(false);
        scrollMotivo.setBorder(BorderFactory.createEmptyBorder());
        scrollMotivo.setBounds(65, 110, 435, 230);
        panelPrincipal.add(scrollMotivo);

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

        // Botón Guardar (Verde)
        btnGuardar = new JButton("Guardar") {
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
        btnGuardar.setBounds(390, 375, 110, 36);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panelPrincipal.add(btnGuardar);

        setContentPane(panelPrincipal);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.cerrarDialogo();
    }

    private void btnGuardarActionPerformed(ActionEvent evt) {
        control.confirmarCancelacion(txtMotivo.getText(), esRechazo);
    }
}
