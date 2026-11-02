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
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Jaime
 */
public class PantallaEditarCliente extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private ClienteDTO clienteDTO;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_CAMPO = new Color(225, 225, 225);
    private static final Color COLOR_ROJO = new Color(235, 20, 20);
    private static final Color COLOR_VERDE = new Color(0, 185, 30);

    public PantallaEditarCliente(ControlMercanciaTerrestre control, ClienteDTO clienteDTO) {
        this.control = control;
        this.clienteDTO = clienteDTO;
        initComponents();
        cargarDatosCliente();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("EditarCliente");
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
        panelBanner.setPreferredSize(new Dimension(0, 75));
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Editar Cliente");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);

        // Panel Central
        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);

        // Líneas laterales de acento
        JPanel lineaIzquierda = createVerticalLine();
        lineaIzquierda.setBounds(35, 40, 2, 330);
        panelCentro.add(lineaIzquierda);

        JPanel lineaDerecha = createVerticalLine();
        lineaDerecha.setBounds(640, 40, 2, 330);
        panelCentro.add(lineaDerecha);

        // Campo 1: Nombre
        txtNombre = createRoundedTextField();
        txtNombre.setBounds(180, 80, 320, 42);
        panelCentro.add(txtNombre);

        // Campo 2: Correo
        txtCorreo = createRoundedTextField();
        txtCorreo.setBounds(180, 145, 320, 42);
        panelCentro.add(txtCorreo);

        // Campo 3: Teléfono
        txtTelefono = createRoundedTextField();
        txtTelefono.setBounds(180, 210, 320, 42);
        panelCentro.add(txtTelefono);

        // Botón Cancelar (Rojo)
        btnCancelar = new JButton("Cancelar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_ROJO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnCancelar.setBounds(180, 300, 110, 36);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        panelCentro.add(btnCancelar);

        // Botón Guardar Cambios (Verde)
        btnGuardarCambios = new JButton("Guardar Cambios") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_VERDE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnGuardarCambios.setBounds(350, 300, 150, 36);
        btnGuardarCambios.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardarCambios.setForeground(Color.WHITE);
        btnGuardarCambios.setContentAreaFilled(false);
        btnGuardarCambios.setBorderPainted(false);
        btnGuardarCambios.setFocusPainted(false);
        btnGuardarCambios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardarCambios.addActionListener(this::btnGuardarCambiosActionPerformed);
        panelCentro.add(btnGuardarCambios);

        panelPrincipal.add(panelBanner, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private void cargarDatosCliente() {
        if (clienteDTO != null) {
            txtNombre.setText(clienteDTO.getNombre());
            txtCorreo.setText(clienteDTO.getCorreo());
            txtTelefono.setText(clienteDTO.getTelefono());
        }
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

    private JPanel createVerticalLine() {
        JPanel line = new JPanel();
        line.setBackground(COLOR_NARANJA);
        return line;
    }
    
    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.irAMenuClientes();
    }

    private void btnGuardarCambiosActionPerformed(ActionEvent evt) {
        control.guardarCambiosCliente(clienteDTO, txtNombre.getText(), txtCorreo.getText(), txtTelefono.getText());
    }
}
