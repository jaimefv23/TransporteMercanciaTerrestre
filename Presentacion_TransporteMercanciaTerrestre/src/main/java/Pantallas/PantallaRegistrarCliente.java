/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Jaime
 */
public class PantallaRegistrarCliente extends JFrame {
    
    private ControlMercanciaTerrestre control;

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtRfc;
    private JTextField txtCorreo;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_CENTRO = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_CAMPO_FONDO = new Color(225, 225, 225);
    private static final Color COLOR_TEXTO_GUIA = new Color(130, 130, 130);
    private static final Color COLOR_ROJO = new Color(220, 40, 40);
    private static final Color COLOR_VERDE = new Color(0, 185, 25);

    public PantallaRegistrarCliente(ControlMercanciaTerrestre control) {
        this.control = control;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Registrar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Banner Superior "Registrar Cliente"
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
        panelBanner.setBounds(150, 25, 400, 55);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Registrar Cliente");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Panel Central
        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelCentro.setBounds(60, 110, 580, 360);

        // Líneas laterales de acento
        JPanel lineaIzquierda = new JPanel();
        lineaIzquierda.setBackground(COLOR_NARANJA);
        lineaIzquierda.setBounds(10, 10, 2, 330);
        panelCentro.add(lineaIzquierda);

        JPanel lineaDerecha = new JPanel();
        lineaDerecha.setBackground(COLOR_NARANJA);
        lineaDerecha.setBounds(568, 10, 2, 330);
        panelCentro.add(lineaDerecha);

        // 1. Nombre Completo
        txtNombre = createPlaceholderTextField("Nombre Completo");
        txtNombre.setBounds(35, 20, 510, 48);
        panelCentro.add(txtNombre);

        // 2. Correo Electrónico
        txtCorreo = createPlaceholderTextField("Correo Electronico");
        txtCorreo.setBounds(35, 90, 510, 48);
        panelCentro.add(txtCorreo);

        // 3. RFC (Nuevo campo agregado debajo de Correo Electrónico)
        txtRfc = createPlaceholderTextField("RFC");
        txtRfc.setBounds(35, 160, 510, 48);
        panelCentro.add(txtRfc);

        // 4. Teléfono Principal / Móvil
        txtTelefono = createPlaceholderTextField("Teléfono Principal / Móvil");
        txtTelefono.setBounds(35, 230, 510, 48);
        panelCentro.add(txtTelefono);

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
        btnCancelar.setBounds(95, 490, 130, 38);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 14));
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
        btnGuardar.setBounds(475, 490, 130, 38);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panelPrincipal.add(btnGuardar);

        setContentPane(panelPrincipal);
    }

    /**
     * Crea un JTextField redondeado con soporte nativo de texto guía (Placeholder)
     * que desaparece cuando el usuario interactúa para escribir.
     */
    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo redondeado del campo
                g2.setColor(COLOR_CAMPO_FONDO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 22, 22));
                g2.dispose();

                super.paintComponent(g);

                // Dibujar el texto guía si el campo está vacío y no tiene el foco
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D gText = (Graphics2D) g.create();
                    gText.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    gText.setColor(COLOR_TEXTO_GUIA);
                    gText.setFont(getFont().deriveFont(Font.PLAIN, 14f));

                    FontMetrics fm = gText.getFontMetrics();
                    Insets insets = getInsets();
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

                    gText.drawString(placeholder, insets.left + 5, y);
                    gText.dispose();
                }
            }
        };

        field.setOpaque(false);
        field.setBorder(new EmptyBorder(0, 18, 0, 18));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);

        // Repintar al ganar/perder el foco para ocultar o mostrar el texto guía
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.repaint();
            }
        });

        return field;
    }
    private void btnGuardarActionPerformed(ActionEvent evt) {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String rfc = txtRfc.getText().trim();

        control.registrarCliente(nombre, telefono, correo, rfc);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.irAMenuClientes();
    }
    
}
