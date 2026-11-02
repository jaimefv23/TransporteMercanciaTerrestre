/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import java.util.List;
import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jaime
 */
public class PantallaDetalleCliente extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private List<SolicitudTransporteDTO> solicitudes;
    private ClienteDTO clienteDTO;

    private JLabel lblNombreCliente;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JTable tblOrdenes;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar;
    private JButton btnCrearSolicitud;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_FILA_TABLA = new Color(28, 28, 28);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);

    public PantallaDetalleCliente(ControlMercanciaTerrestre control, ClienteDTO clienteDTO, List<SolicitudTransporteDTO> solicitudes) {
        this.control = control;
        this.solicitudes = solicitudes;
        this.clienteDTO = clienteDTO;
        initComponents();
        cargarDatosCliente();
        cargarOrdenesCliente();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 620);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Separadores Horizontales
        JPanel lineaSuperior = new JPanel();
        lineaSuperior.setBackground(COLOR_NARANJA);
        lineaSuperior.setBounds(20, 95, 795, 2);
        panelPrincipal.add(lineaSuperior);

        JPanel lineaInferior = new JPanel();
        lineaInferior.setBackground(COLOR_NARANJA);
        lineaInferior.setBounds(20, 530, 795, 2);
        panelPrincipal.add(lineaInferior);

        // Badge Nombre Cliente (Esquina superior izquierda)
        JPanel panelBadgeNombre = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        panelBadgeNombre.setOpaque(false);
        panelBadgeNombre.setBounds(35, 35, 360, 42);
        panelBadgeNombre.setLayout(new GridBagLayout());

        lblNombreCliente = new JLabel("Nombre Cliente");
        lblNombreCliente.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblNombreCliente.setForeground(Color.WHITE);
        panelBadgeNombre.add(lblNombreCliente);
        panelPrincipal.add(panelBadgeNombre);

        // Sección Información
        JLabel lblTituloInfo = new JLabel("Información:");
        lblTituloInfo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloInfo.setForeground(Color.WHITE);
        lblTituloInfo.setBounds(50, 200, 200, 30);
        panelPrincipal.add(lblTituloInfo);

        lblTelefono = new JLabel("Telefono: ");
        lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setBounds(50, 250, 280, 25);
        panelPrincipal.add(lblTelefono);

        lblCorreo = new JLabel("Correo: ");
        lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lblCorreo.setForeground(Color.WHITE);
        lblCorreo.setBounds(50, 290, 280, 25);
        panelPrincipal.add(lblCorreo);

        // Badge "Órdenes Activas"
        JPanel panelBadgeOrdenes = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        panelBadgeOrdenes.setOpaque(false);
        panelBadgeOrdenes.setBounds(460, 140, 180, 36);
        panelBadgeOrdenes.setLayout(new GridBagLayout());

        JLabel lblTituloOrdenes = new JLabel("Ordenes Activas");
        lblTituloOrdenes.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTituloOrdenes.setForeground(Color.WHITE);
        panelBadgeOrdenes.add(lblTituloOrdenes);
        panelPrincipal.add(panelBadgeOrdenes);

        // Panel Tabla Órdenes Activas
        JPanel panelTabla = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        panelTabla.setOpaque(false);
        panelTabla.setBounds(330, 195, 475, 230);
        panelTabla.setBorder(new EmptyBorder(12, 12, 12, 12));

        String[] columnas = {"Folio", "Fecha Registro", "Origen - Destino", "Estado", "Mercancía"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblOrdenes = new JTable(modeloTabla);
        tblOrdenes.setBackground(COLOR_PANEL_INTERNO);
        tblOrdenes.setForeground(Color.WHITE);
        tblOrdenes.setRowHeight(32);
        tblOrdenes.setShowGrid(false);
        tblOrdenes.setIntercellSpacing(new Dimension(0, 4));
        tblOrdenes.setFont(new Font("SansSerif", Font.PLAIN, 11));
        tblOrdenes.getTableHeader().setBackground(COLOR_PANEL_INTERNO);
        tblOrdenes.getTableHeader().setForeground(Color.WHITE);
        tblOrdenes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11));

        tblOrdenes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

        JScrollPane scrollTabla = new JScrollPane(tblOrdenes);
        scrollTabla.getViewport().setBackground(COLOR_PANEL_INTERNO);
        scrollTabla.setBorder(null);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        // Botón Regresar
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
        btnRegresar.setBounds(35, 465, 120, 36);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        // Botón Crear Solicitud
        btnCrearSolicitud = new JButton("Crear Solicitud") {
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
        btnCrearSolicitud.setBounds(620, 465, 185, 36);
        btnCrearSolicitud.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCrearSolicitud.setForeground(Color.WHITE);
        btnCrearSolicitud.setContentAreaFilled(false);
        btnCrearSolicitud.setBorderPainted(false);
        btnCrearSolicitud.setFocusPainted(false);
        btnCrearSolicitud.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrearSolicitud.addActionListener(this::btnCrearSolicitudActionPerformed);
        panelPrincipal.add(btnCrearSolicitud);

        setContentPane(panelPrincipal);
    }

    private void cargarDatosCliente() { 
        lblNombreCliente.setText(clienteDTO.getNombre());
        lblTelefono.setText("Telefono: " + clienteDTO.getTelefono());
        lblCorreo.setText("Correo: " + clienteDTO.getCorreo());
    }
    
    private void cargarOrdenesCliente() {
        modeloTabla.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (SolicitudTransporteDTO sol : solicitudes) {
            String fecha = sol.getFechaCarga() != null ? sol.getFechaCarga().format(formatter) : "-";
            String trayecto = (sol.getOrigen() != null && sol.getDestino() != null)
                    ? sol.getOrigen().getCiudad() + " - " + sol.getDestino().getCiudad() : "-";
            String mercancia = sol.getMercancias().isEmpty() ? "-" : sol.getMercancias().get(0).getNombre();
            Object[] tablita = {
                sol.getFolio(),
                fecha,
                trayecto,
                sol.getEstado(),
                mercancia
            };
            modeloTabla.addRow(tablita);
        }
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irAMenuClientes();
    }

    private void btnCrearSolicitudActionPerformed(ActionEvent evt) {
        control.iniciarSolicitud();
    }
}
