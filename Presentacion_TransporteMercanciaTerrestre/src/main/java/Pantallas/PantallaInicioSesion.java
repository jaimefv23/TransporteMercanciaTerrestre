/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.UsuarioDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Jaime
 */
public class PantallaInicioSesion extends JFrame {
    
    private ControlMercanciaTerrestre control;

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnEntrar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_CAMPO = new Color(225, 225, 225);

    public PantallaInicioSesion(ControlMercanciaTerrestre control) {
        this.control = control;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Inicio sesion");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 580);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 35, 25, 35));

        // Header Superior: Banner Naranja Redondeado
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
        panelBanner.setPreferredSize(new Dimension(0, 85));
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Transporte de Mercancía Terrestre");
        lblTituloGeneral.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 28));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);

        // Panel Central
        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);

        JLabel lblSubtitulo = new JLabel("Iniciar sesion");
        lblSubtitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(70, 30, 300, 30);
        panelCentro.add(lblSubtitulo);

        // Líneas laterales de acento
        JPanel lineaIzquierda = createVerticalLine();
        lineaIzquierda.setBounds(35, 75, 2, 330);
        panelCentro.add(lineaIzquierda);

        JPanel lineaDerecha = createVerticalLine();
        lineaDerecha.setBounds(640, 75, 2, 330);
        panelCentro.add(lineaDerecha);

        // Campo Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblUsuario.setForeground(new Color(200, 200, 200));
        lblUsuario.setBounds(160, 105, 100, 20);
        panelCentro.add(lblUsuario);

        txtUsuario = createRoundedTextField();
        txtUsuario.setBounds(160, 130, 320, 38);
        txtUsuario.setText("");
        panelCentro.add(txtUsuario);

        // Campo Contraseña
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblContrasena.setForeground(new Color(200, 200, 200));
        lblContrasena.setBounds(160, 185, 100, 20);
        panelCentro.add(lblContrasena);

        txtContrasena = createRoundedPasswordField();
        txtContrasena.setBounds(160, 210, 320, 38);
        txtContrasena.setText("");
        panelCentro.add(txtContrasena);

        // Botón Entrar
        btnEntrar = new JButton("Entrar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEntrar.setBounds(390, 280, 90, 32);
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setContentAreaFilled(false);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        panelCentro.add(btnEntrar);

        panelPrincipal.add(panelBanner, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private JTextField createRoundedTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CAMPO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(0, 12, 0, 12));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        return field;
    }

    private JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CAMPO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(0, 12, 0, 12));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        return field;
    }

    private JPanel createVerticalLine() {
        JPanel line = new JPanel();
        line.setBackground(COLOR_NARANJA);
        return line;
    }
    
    private void btnEntrarActionPerformed(ActionEvent evt) {
        String nombre = txtUsuario.getText().trim();
        String contrasenia = new String(txtContrasena.getPassword()).trim();

        control.iniciarSesion(nombre, contrasenia);
    }
    
}
