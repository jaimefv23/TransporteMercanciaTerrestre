/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Jaime
 */
public class PantallaRegistroSolicitudFechas extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private ClienteDTO clienteDTO;
    private SolicitudTransporteDTO solicitud;

    // Campos Origen
    private JTextField txtCalleOrigen, txtNumOrigen, txtColoniaOrigen, txtCpOrigen, txtCiudadOrigen, txtEstadoOrigen;
    private JTextField txtRefOrigen, txtContactoOrigen, txtTelOrigen, txtHorarioOrigen;

    // Campos Destino
    private JTextField txtCalleDestino, txtNumDestino, txtColoniaDestino, txtCpDestino, txtCiudadDestino, txtEstadoDestino;
    private JTextField txtRefDestino, txtContactoDestino, txtTelDestino, txtHorarioDestino;

    // Fechas y Observaciones
    private JDateChooser dateCarga;
    private JDateChooser dateEntrega;
    private JTextArea txtNotasGenerales;

    // Botones
    private JButton btnRegresar;
    private JButton btnCancelar;
    private JButton btnSiguiente;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_ROJO = new Color(230, 20, 20);
    private static final Color COLOR_CAMPO = new Color(225, 225, 225);
    private static final Color COLOR_TEXTO_GUIA = new Color(130, 130, 130);

    public PantallaRegistroSolicitudFechas(ControlMercanciaTerrestre control, ClienteDTO clienteDTO, SolicitudTransporteDTO solicitud) {
        this.control = control;
        this.clienteDTO = clienteDTO;
        this.solicitud = solicitud;
        initComponents();
        cargarDatosCliente();
        cargarDatosPrevios();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("RegistroSolicitudOrigenDestino");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(940, 780);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Separadores Horizontales
        JPanel lineaSuperior = new JPanel();
        lineaSuperior.setBackground(COLOR_NARANJA);
        lineaSuperior.setBounds(20, 90, 885, 2);
        panelPrincipal.add(lineaSuperior);

        JPanel lineaInferior = new JPanel();
        lineaInferior.setBackground(COLOR_NARANJA);
        lineaInferior.setBounds(20, 685, 885, 2);
        panelPrincipal.add(lineaInferior);

        // Banner Superior
        JPanel panelBanner = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        panelBanner.setOpaque(false);
        panelBanner.setBounds(270, 15, 400, 38);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Nueva Solicitud - Origen, Destino y Tiempos");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Badge Cliente
        JPanel panelBadgeCliente = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
            }
        };
        panelBadgeCliente.setOpaque(false);
        panelBadgeCliente.setBounds(35, 55, 460, 26);
        panelBadgeCliente.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 4));

        String nombreCliente = (clienteDTO != null && clienteDTO.getNombre() != null) ? clienteDTO.getNombre() : "Cliente";
        String rfcCliente = (clienteDTO != null && clienteDTO.getRFC() != null) ? clienteDTO.getRFC() : "N/A";

        JLabel lblInfoCliente = new JLabel("Cliente: " + nombreCliente + " ( " + rfcCliente + " )");
        lblInfoCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblInfoCliente.setForeground(Color.WHITE);
        panelBadgeCliente.add(lblInfoCliente);
        panelPrincipal.add(panelBadgeCliente);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN ORIGEN
        // ═════════════════════════════════════════════════════════════════
        JLabel lblOrigen = new JLabel("Origen");
        lblOrigen.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOrigen.setForeground(Color.WHITE);
        lblOrigen.setBounds(35, 100, 100, 25);
        panelPrincipal.add(lblOrigen);

        JPanel panelOrigen = createBorderedPanel();
        panelOrigen.setBounds(25, 125, 430, 360);
        panelOrigen.setLayout(null);

        txtCalleOrigen = createPlaceholderTextField("Calle");
        txtCalleOrigen.setBounds(40, 12, 350, 28);
        panelOrigen.add(txtCalleOrigen);

        txtNumOrigen = createPlaceholderTextField("Número");
        txtNumOrigen.setBounds(40, 46, 350, 28);
        panelOrigen.add(txtNumOrigen);

        txtColoniaOrigen = createPlaceholderTextField("Colonia");
        txtColoniaOrigen.setBounds(40, 80, 350, 28);
        panelOrigen.add(txtColoniaOrigen);

        txtCpOrigen = createPlaceholderTextField("Código Postal");
        txtCpOrigen.setBounds(40, 114, 350, 28);
        panelOrigen.add(txtCpOrigen);

        txtCiudadOrigen = createPlaceholderTextField("Ciudad");
        txtCiudadOrigen.setBounds(40, 148, 350, 28);
        panelOrigen.add(txtCiudadOrigen);

        txtEstadoOrigen = createPlaceholderTextField("Estado");
        txtEstadoOrigen.setBounds(40, 182, 350, 28);
        panelOrigen.add(txtEstadoOrigen);

        txtRefOrigen = createPlaceholderTextField("Referencias / Observaciones");
        txtRefOrigen.setBounds(40, 216, 350, 28);
        panelOrigen.add(txtRefOrigen);

        txtContactoOrigen = createPlaceholderTextField("Contacto Origen");
        txtContactoOrigen.setBounds(40, 250, 350, 28);
        panelOrigen.add(txtContactoOrigen);

        txtTelOrigen = createPlaceholderTextField("Teléfono de Contacto Origen");
        txtTelOrigen.setBounds(40, 284, 350, 28);
        panelOrigen.add(txtTelOrigen);

        txtHorarioOrigen = createPlaceholderTextField("Ventana / Horario Permitido de Carga");
        txtHorarioOrigen.setBounds(40, 318, 350, 28);
        panelOrigen.add(txtHorarioOrigen);

        panelPrincipal.add(panelOrigen);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN DESTINO
        // ═════════════════════════════════════════════════════════════════
        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblDestino.setForeground(Color.WHITE);
        lblDestino.setBounds(500, 100, 100, 25);
        panelPrincipal.add(lblDestino);

        JPanel panelDestino = createBorderedPanel();
        panelDestino.setBounds(475, 125, 430, 360);
        panelDestino.setLayout(null);

        txtCalleDestino = createPlaceholderTextField("Calle");
        txtCalleDestino.setBounds(40, 12, 350, 28);
        panelDestino.add(txtCalleDestino);

        txtNumDestino = createPlaceholderTextField("Número");
        txtNumDestino.setBounds(40, 46, 350, 28);
        panelDestino.add(txtNumDestino);

        txtColoniaDestino = createPlaceholderTextField("Colonia");
        txtColoniaDestino.setBounds(40, 80, 350, 28);
        panelDestino.add(txtColoniaDestino);

        txtCpDestino = createPlaceholderTextField("Código Postal");
        txtCpDestino.setBounds(40, 114, 350, 28);
        panelDestino.add(txtCpDestino);

        txtCiudadDestino = createPlaceholderTextField("Ciudad");
        txtCiudadDestino.setBounds(40, 148, 350, 28);
        panelDestino.add(txtCiudadDestino);

        txtEstadoDestino = createPlaceholderTextField("Estado");
        txtEstadoDestino.setBounds(40, 182, 350, 28);
        panelDestino.add(txtEstadoDestino);

        txtRefDestino = createPlaceholderTextField("Referencias / Observaciones");
        txtRefDestino.setBounds(40, 216, 350, 28);
        panelDestino.add(txtRefDestino);

        txtContactoDestino = createPlaceholderTextField("Contacto Destino");
        txtContactoDestino.setBounds(40, 250, 350, 28);
        panelDestino.add(txtContactoDestino);

        txtTelDestino = createPlaceholderTextField("Teléfono de Contacto Destino");
        txtTelDestino.setBounds(40, 284, 350, 28);
        panelDestino.add(txtTelDestino);

        txtHorarioDestino = createPlaceholderTextField("Ventana / Horario Permitido de Descarga");
        txtHorarioDestino.setBounds(40, 318, 350, 28);
        panelDestino.add(txtHorarioDestino);

        panelPrincipal.add(panelDestino);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN FECHAS Y OBSERVACIONES
        // ═════════════════════════════════════════════════════════════════
        JPanel panelFechas = createBorderedPanel();
        panelFechas.setBounds(25, 495, 880, 130);
        panelFechas.setLayout(null);

        JLabel lblFechasTitulo = new JLabel("Fechas Requeridas y Observaciones General");
        lblFechasTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFechasTitulo.setForeground(Color.WHITE);
        lblFechasTitulo.setBounds(15, 5, 350, 22);
        panelFechas.add(lblFechasTitulo);

        // Datepicker Fecha de Carga
        JLabel lblFechaCarga = new JLabel("Fecha de Carga:");
        lblFechaCarga.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblFechaCarga.setForeground(Color.WHITE);
        lblFechaCarga.setBounds(15, 32, 110, 20);
        panelFechas.add(lblFechaCarga);

        dateCarga = new JDateChooser();
        dateCarga.setDate(new Date());
        dateCarga.setDateFormatString("dd/MM/yyyy");
        dateCarga.setBounds(15, 55, 170, 60);
        panelFechas.add(dateCarga);

        // Datepicker Fecha de Entrega Estimada
        JLabel lblFechaEntrega = new JLabel("Fecha de Entrega Estimada:");
        lblFechaEntrega.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblFechaEntrega.setForeground(Color.WHITE);
        lblFechaEntrega.setBounds(205, 32, 170, 20);
        panelFechas.add(lblFechaEntrega);

        dateEntrega = new JDateChooser();
        dateEntrega.setDate(new Date());
        dateEntrega.setDateFormatString("dd/MM/yyyy");
        dateEntrega.setBounds(205, 55, 170, 60);
        panelFechas.add(dateEntrega);

        // Notas / Indicaciones Generales
        JLabel lblNotas = new JLabel("Notas / Indicaciones Generales del Cliente:");
        lblNotas.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNotas.setForeground(Color.WHITE);
        lblNotas.setBounds(400, 32, 280, 20);
        panelFechas.add(lblNotas);

        txtNotasGenerales = new JTextArea();
        txtNotasGenerales.setFont(new Font("SansSerif", Font.PLAIN, 11));
        txtNotasGenerales.setLineWrap(true);
        txtNotasGenerales.setWrapStyleWord(true);
        txtNotasGenerales.setBackground(COLOR_CAMPO);
        txtNotasGenerales.setForeground(Color.BLACK);

        JScrollPane scrollNotas = new JScrollPane(txtNotasGenerales);
        scrollNotas.setBounds(400, 55, 460, 60);
        panelFechas.add(scrollNotas);

        panelPrincipal.add(panelFechas);

        // ═════════════════════════════════════════════════════════════════
        // BOTONES DE ACCIÓN
        // ═════════════════════════════════════════════════════════════════
        btnRegresar = new JButton("Regresar") {
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
        btnRegresar.setBounds(25, 635, 110, 32);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

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
        btnCancelar.setBounds(150, 635, 110, 32);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        panelPrincipal.add(btnCancelar);

        btnSiguiente = new JButton("Siguiente: Capturar Mercancía") {
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
        btnSiguiente.setBounds(650, 635, 255, 32);
        btnSiguiente.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnSiguiente.setForeground(Color.WHITE);
        btnSiguiente.setContentAreaFilled(false);
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setFocusPainted(false);
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.addActionListener(this::btnSiguienteActionPerformed);
        panelPrincipal.add(btnSiguiente);

        setContentPane(panelPrincipal);
    }

    private JTextField createPlaceholderTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CAMPO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();

                super.paintComponent(g);

                // Dibujar el texto guía cuando el campo está vacío y no tiene el foco activo
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D gText = (Graphics2D) g.create();
                    gText.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    gText.setColor(COLOR_TEXTO_GUIA);
                    gText.setFont(getFont().deriveFont(Font.PLAIN, 12f));

                    FontMetrics fm = gText.getFontMetrics();
                    Insets insets = getInsets();
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

                    gText.drawString(placeholder, insets.left + 5, y);
                    gText.dispose();
                }
            }
        };

        field.setOpaque(false);
        field.setBorder(new EmptyBorder(0, 10, 0, 10));
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(Color.BLACK);

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

    private JPanel createBorderedPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.setColor(COLOR_NARANJA);
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, 14, 14));
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        return panel;
    }
    
    private void cargarDatosCliente() {
        txtContactoOrigen.setText(clienteDTO.getNombre());
        txtTelOrigen.setText(clienteDTO.getTelefono());
    }

    /** Si el usuario regresa a esta pantalla, recupera lo que ya había capturado. */
    private void cargarDatosPrevios() {
        PuntoTrasladoDTO o = solicitud.getOrigen();
        if (o != null) {
            txtCalleOrigen.setText(o.getCalle());
            txtNumOrigen.setText(o.getNumero());
            txtColoniaOrigen.setText(o.getColonia());
            txtCpOrigen.setText(o.getCodigoPostal());
            txtCiudadOrigen.setText(o.getCiudad());
            txtEstadoOrigen.setText(o.getEstado());
            txtRefOrigen.setText(o.getInfoAdicional());
            txtContactoOrigen.setText(o.getNomContacto());
            txtTelOrigen.setText(o.getTelContacto());
            txtHorarioOrigen.setText(o.getVentanaHoraria());
        }
        PuntoTrasladoDTO d = solicitud.getDestino();
        if (d != null) {
            txtCalleDestino.setText(d.getCalle());
            txtNumDestino.setText(d.getNumero());
            txtColoniaDestino.setText(d.getColonia());
            txtCpDestino.setText(d.getCodigoPostal());
            txtCiudadDestino.setText(d.getCiudad());
            txtEstadoDestino.setText(d.getEstado());
            txtRefDestino.setText(d.getInfoAdicional());
            txtContactoDestino.setText(d.getNomContacto());
            txtTelDestino.setText(d.getTelContacto());
            txtHorarioDestino.setText(d.getVentanaHoraria());
        }
        if (solicitud.getFechaCarga() != null) {
            dateCarga.setDate(Date.from(solicitud.getFechaCarga().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (solicitud.getFechaEntrega() != null) {
            dateEntrega.setDate(Date.from(solicitud.getFechaEntrega().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        if (solicitud.getObservaciones() != null) {
            txtNotasGenerales.setText(solicitud.getObservaciones());
        }
    }
   
    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irADetalleCliente(clienteDTO);
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.solicitarCancelacion();
    }

    private void btnSiguienteActionPerformed(ActionEvent evt) {
        PuntoTrasladoDTO origen = new PuntoTrasladoDTO();
        origen.setCalle(txtCalleOrigen.getText().trim());
        origen.setNumero(txtNumOrigen.getText().trim());
        origen.setColonia(txtColoniaOrigen.getText().trim());
        origen.setCodigoPostal(txtCpOrigen.getText().trim());
        origen.setCiudad(txtCiudadOrigen.getText().trim());
        origen.setEstado(txtEstadoOrigen.getText().trim());
        origen.setInfoAdicional(txtRefOrigen.getText().trim());
        origen.setNomContacto(txtContactoOrigen.getText().trim());
        origen.setTelContacto(txtTelOrigen.getText().trim());
        origen.setVentanaHoraria(txtHorarioOrigen.getText().trim());

        PuntoTrasladoDTO destino = new PuntoTrasladoDTO();
        destino.setCalle(txtCalleDestino.getText().trim());
        destino.setNumero(txtNumDestino.getText().trim());
        destino.setColonia(txtColoniaDestino.getText().trim());
        destino.setCodigoPostal(txtCpDestino.getText().trim());
        destino.setCiudad(txtCiudadDestino.getText().trim());
        destino.setEstado(txtEstadoDestino.getText().trim());
        destino.setInfoAdicional(txtRefDestino.getText().trim());
        destino.setNomContacto(txtContactoDestino.getText().trim());
        destino.setTelContacto(txtTelDestino.getText().trim());
        destino.setVentanaHoraria(txtHorarioDestino.getText().trim());

        control.guardarOrigenDestino(origen, destino, dateCarga.getDate(), dateEntrega.getDate(),
                txtNotasGenerales.getText());
    }
}
