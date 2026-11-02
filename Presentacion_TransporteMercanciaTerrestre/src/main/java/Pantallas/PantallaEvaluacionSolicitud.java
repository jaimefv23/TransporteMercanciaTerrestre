/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.MercanciaDTO;
import DTOS.PuntoTrasladoDTO;
import javax.swing.JFrame;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Jaime
 */
public class PantallaEvaluacionSolicitud extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private SolicitudTransporteDTO solicitud;
    
    private JTextArea txtOrigenDatos;
    private JTextArea txtDestinoDatos;
    private JTable tblMercancia;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar;
    private JButton btnFinalizarEvaluacion;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_FILA_TABLA = new Color(28, 28, 28);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);

    public PantallaEvaluacionSolicitud(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud) {
        this.control = control;
        this.solicitud = solicitud;
        initComponents();
        cargarDatosReales();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Evaluacion de Solicitud");
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
        panelBanner.setBounds(340, 15, 260, 38);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Evaluacion de Solicitud");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Badge Folio | Cliente
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

        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        String nombreCliente = (solicitud != null && solicitud.getCliente() != null) ? solicitud.getCliente().getNombre() : "Cliente";

        JLabel lblInfoCliente = new JLabel("Folio Solicitud: " + folioStr + " | Cliente: " + nombreCliente);
        lblInfoCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblInfoCliente.setForeground(Color.WHITE);
        panelBadgeCliente.add(lblInfoCliente);
        panelPrincipal.add(panelBadgeCliente);

        // Origen
        JLabel lblOrigen = new JLabel("Origen");
        lblOrigen.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblOrigen.setForeground(Color.WHITE);
        lblOrigen.setBounds(75, 110, 100, 25);
        panelPrincipal.add(lblOrigen);

        JSeparator sepOrigen = new JSeparator();
        sepOrigen.setBounds(75, 138, 380, 2);
        sepOrigen.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepOrigen);

        txtOrigenDatos = new JTextArea();
        txtOrigenDatos.setFont(new Font("Monospaced", Font.BOLD, 13));
        txtOrigenDatos.setForeground(Color.WHITE);
        txtOrigenDatos.setOpaque(false);
        txtOrigenDatos.setEditable(false);
        txtOrigenDatos.setBounds(95, 145, 360, 160);
        panelPrincipal.add(txtOrigenDatos);

        // Destino
        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblDestino.setForeground(Color.WHITE);
        lblDestino.setBounds(505, 110, 100, 25);
        panelPrincipal.add(lblDestino);

        JSeparator sepDestino = new JSeparator();
        sepDestino.setBounds(505, 138, 380, 2);
        sepDestino.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepDestino);

        txtDestinoDatos = new JTextArea();
        txtDestinoDatos.setFont(new Font("Monospaced", Font.BOLD, 13));
        txtDestinoDatos.setForeground(Color.WHITE);
        txtDestinoDatos.setOpaque(false);
        txtDestinoDatos.setEditable(false);
        txtDestinoDatos.setBounds(525, 145, 360, 160);
        panelPrincipal.add(txtDestinoDatos);

        // Tabla Mercancía
        JLabel lblMercancia = new JLabel("Mercancia");
        lblMercancia.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblMercancia.setForeground(Color.WHITE);
        lblMercancia.setBounds(75, 320, 120, 25);
        panelPrincipal.add(lblMercancia);

        JPanel panelTablaContenedor = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
            }
        };
        panelTablaContenedor.setOpaque(false);
        panelTablaContenedor.setBounds(60, 350, 830, 250);

        String[] columnas = {"Descripción", "Cantidad", "Empaque", "Peso Individual", "Peso Total", "Dimensiones", "Condiciones Es.", "Observaciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblMercancia = new JTable(modeloTabla);
        tblMercancia.setBackground(COLOR_PANEL_INTERNO);
        tblMercancia.setForeground(Color.WHITE);
        tblMercancia.setRowHeight(42);
        tblMercancia.setShowGrid(false);
        tblMercancia.setIntercellSpacing(new Dimension(0, 4));
        tblMercancia.setFont(new Font("SansSerif", Font.PLAIN, 11));
        tblMercancia.getTableHeader().setBackground(COLOR_PANEL_INTERNO);
        tblMercancia.getTableHeader().setForeground(Color.WHITE);
        tblMercancia.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11));

        tblMercancia.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setOpaque(true);
                lbl.setBackground(COLOR_FILA_TABLA);
                lbl.setForeground(Color.WHITE);
                return lbl;
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tblMercancia);
        scrollTabla.getViewport().setBackground(COLOR_PANEL_INTERNO);
        scrollTabla.setBorder(null);
        scrollTabla.setBounds(15, 15, 800, 180);
        panelTablaContenedor.add(scrollTabla);

        JSeparator sepTablaBot = new JSeparator();
        sepTablaBot.setBounds(15, 205, 800, 2);
        sepTablaBot.setForeground(COLOR_NARANJA);
        panelTablaContenedor.add(sepTablaBot);

        JButton btnEditarMercancia = new JButton("Editar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEditarMercancia.setBounds(735, 215, 80, 26);
        btnEditarMercancia.setFont(new Font("SansSerif", Font.BOLD, 11));
        btnEditarMercancia.setForeground(Color.WHITE);
        btnEditarMercancia.setContentAreaFilled(false);
        btnEditarMercancia.setBorderPainted(false);
        btnEditarMercancia.setFocusPainted(false);
        btnEditarMercancia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditarMercancia.addActionListener(this::btnEditarMercanciaActionPerformed);
        panelTablaContenedor.add(btnEditarMercancia);

        panelPrincipal.add(panelTablaContenedor);

        // Botones Inferiores
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
        btnRegresar.setBounds(60, 635, 110, 32);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        btnFinalizarEvaluacion = new JButton("Finalizar Evaluación") {
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
        btnFinalizarEvaluacion.setBounds(715, 635, 175, 32);
        btnFinalizarEvaluacion.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnFinalizarEvaluacion.setForeground(Color.WHITE);
        btnFinalizarEvaluacion.setContentAreaFilled(false);
        btnFinalizarEvaluacion.setBorderPainted(false);
        btnFinalizarEvaluacion.setFocusPainted(false);
        btnFinalizarEvaluacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizarEvaluacion.addActionListener(this::btnFinalizarEvaluacionActionPerformed);
        panelPrincipal.add(btnFinalizarEvaluacion);

        setContentPane(panelPrincipal);
    }

    private void cargarDatosReales() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.forLanguageTag("es-ES"));

        // Origen
        PuntoTrasladoDTO orig = solicitud.getOrigen();
        String fechaCargaStr = solicitud.getFechaCarga() != null ? solicitud.getFechaCarga().format(formatter) : "-";
        txtOrigenDatos.setText(orig.getCalle() + " " + orig.getNumero() + "\n"
                + orig.getColonia() + "\n"
                + orig.getCodigoPostal() + "\n"
                + orig.getCiudad() + "\n"
                + orig.getEstado() + "\n"
                + fechaCargaStr + "\n"
                + orig.getVentanaHoraria());

        // Destino
        PuntoTrasladoDTO dest = solicitud.getDestino();
        String fechaEntregaStr = solicitud.getFechaEntrega() != null ? solicitud.getFechaEntrega().format(formatter) : "-";
        txtDestinoDatos.setText(dest.getCalle() + " " + dest.getNumero() + "\n"
                + dest.getColonia() + "\n"
                + dest.getCodigoPostal() + "\n"
                + dest.getCiudad() + "\n"
                + dest.getEstado() + "\n"
                + fechaEntregaStr + "\n"
                + dest.getVentanaHoraria());

        // Mercancías
        modeloTabla.setRowCount(0);
        for (MercanciaDTO m : solicitud.getMercancias()) {
            double pesoTotal = m.getCantidad() * m.getPeso();
            String dimens = m.getLargo() + "×" + m.getAncho() + "×" + m.getAlto() + " m";

            Object[] fila = {
                m.getNombre(),
                m.getCantidad(),
                m.getTipoEmpaque(),
                m.getPeso() + " kg",
                pesoTotal + " kg",
                dimens,
                m.getCondicionesEsp(),
                m.getInfoAdicional()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void btnEditarMercanciaActionPerformed(ActionEvent evt) {
        control.editarMercancia(tblMercancia.getSelectedRow());
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.regresarACaptura();
    }

    private void btnFinalizarEvaluacionActionPerformed(ActionEvent evt) {
        control.finalizarEvaluacion();
    }
}
