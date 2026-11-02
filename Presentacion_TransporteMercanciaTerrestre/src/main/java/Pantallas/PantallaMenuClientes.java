/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import DTOS.UsuarioDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class PantallaMenuClientes extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private UsuarioDTO usuario;
    private static final String PLACEHOLDER_BUSQUEDA = "Barra de busqueda";

    private JLabel lblUsuario;
    private JTextField txtBusqueda;
    private JButton btnLimpiarFiltros;
    private JButton btnRegistrarCliente;
    private JTable tblClientes;
    private DefaultTableModel modeloTabla;
    private List<ClienteDTO> clientesActuales;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_FILA_TABLA = new Color(28, 28, 28);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);

    public PantallaMenuClientes(ControlMercanciaTerrestre control, UsuarioDTO usuario, List<ClienteDTO> clientes) {
        this.control = control;
        this.usuario = usuario;
        initComponents();
        cargarDatosUsuario();
        mostrarClientes(clientes);
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("MenuClientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 620);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Separadores Horizontales
        JPanel lineaSuperior = new JPanel();
        lineaSuperior.setBackground(COLOR_NARANJA);
        lineaSuperior.setBounds(20, 50, 795, 2);
        panelPrincipal.add(lineaSuperior);

        JPanel lineaInferior = new JPanel();
        lineaInferior.setBackground(COLOR_NARANJA);
        lineaInferior.setBounds(20, 530, 795, 2);
        panelPrincipal.add(lineaInferior);

        // Icono de Usuario y Nombre
        JLabel lblIconoUsuario = new JLabel("👤");
        lblIconoUsuario.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        lblIconoUsuario.setForeground(Color.WHITE);
        lblIconoUsuario.setBounds(35, 15, 30, 30);
        panelPrincipal.add(lblIconoUsuario);

        lblUsuario = new JLabel("NombreUsuario");
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(70, 15, 250, 30);
        panelPrincipal.add(lblUsuario);

        // Barra de Búsqueda y Filtros
        JPanel panelBusqueda = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
                g2.dispose();
            }
        };
        panelBusqueda.setOpaque(false);
        panelBusqueda.setBounds(180, 68, 480, 36);

        txtBusqueda = new JTextField("Barra de busqueda");
        txtBusqueda.setBounds(15, 4, 310, 28);
        txtBusqueda.setOpaque(false);
        txtBusqueda.setBorder(null);
        txtBusqueda.setForeground(Color.WHITE);
        txtBusqueda.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtBusqueda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (PLACEHOLDER_BUSQUEDA.equals(txtBusqueda.getText())) {
                    txtBusqueda.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBusqueda.getText().trim().isEmpty()) {
                    txtBusqueda.setText(PLACEHOLDER_BUSQUEDA);
                }
            }
        });
        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarClientes();
            }
        });
        panelBusqueda.add(txtBusqueda);

        JSeparator separadorFiltro = new JSeparator(SwingConstants.VERTICAL);
        separadorFiltro.setBounds(335, 6, 2, 24);
        separadorFiltro.setForeground(COLOR_FONDO);
        panelBusqueda.add(separadorFiltro);

        btnLimpiarFiltros = new JButton("Limpieza Filtros");
        btnLimpiarFiltros.setBounds(345, 4, 125, 28);
        btnLimpiarFiltros.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnLimpiarFiltros.setForeground(Color.WHITE);
        btnLimpiarFiltros.setContentAreaFilled(false);
        btnLimpiarFiltros.setBorderPainted(false);
        btnLimpiarFiltros.setFocusPainted(false);
        btnLimpiarFiltros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiarFiltros.addActionListener(this::btnLimpiarFiltrosActionPerformed);
        panelBusqueda.add(btnLimpiarFiltros);

        panelPrincipal.add(panelBusqueda);

        // Badge "Clientes"
        JPanel panelBadgeClientes = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_NARANJA);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
            }
        };
        panelBadgeClientes.setOpaque(false);
        panelBadgeClientes.setBounds(70, 130, 125, 32);
        panelBadgeClientes.setLayout(new GridBagLayout());

        JLabel lblTituloClientes = new JLabel("Clientes");
        lblTituloClientes.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTituloClientes.setForeground(Color.WHITE);
        panelBadgeClientes.add(lblTituloClientes);
        panelPrincipal.add(panelBadgeClientes);

        // Panel Contenedor de la Tabla
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
        panelTabla.setBounds(120, 180, 600, 230);
        panelTabla.setBorder(new EmptyBorder(15, 15, 15, 15));

        String[] columnas = {"Nombre", "Telefono", "Correo", ""};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblClientes = new JTable(modeloTabla);
        tblClientes.setBackground(COLOR_PANEL_INTERNO);
        tblClientes.setForeground(Color.WHITE);
        tblClientes.setRowHeight(36);
        tblClientes.setShowGrid(false);
        tblClientes.setIntercellSpacing(new Dimension(0, 6));
        tblClientes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblClientes.getTableHeader().setBackground(COLOR_PANEL_INTERNO);
        tblClientes.getTableHeader().setForeground(Color.WHITE);
        tblClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        // Dimensiones de columnas (Espacio más grande para Correo)
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(120); // Nombre
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(100); // Telefono
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(230); // Correo (más amplio)
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(30);  // Opción (⋮)

        tblClientes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setOpaque(true);
                
                if (isSelected) {
                    lbl.setBackground(COLOR_NARANJA);
                    lbl.setForeground(Color.WHITE);
                } else {
                    lbl.setBackground(COLOR_FILA_TABLA);
                    lbl.setForeground(Color.WHITE);
                }
                return lbl;
            }
        });

        // Evento de clic corrigiendo la detección de fila y el cierre de ventana
        tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblClientes.rowAtPoint(e.getPoint());
                int col = tblClientes.columnAtPoint(e.getPoint());

                if (row >= 0 && clientesActuales != null && row < clientesActuales.size()) {
                    ClienteDTO cliente = clientesActuales.get(row);
                    if (col == 3) {
                        // Click en los 3 puntos
                        control.irAEditarCliente(cliente);
                    } else {
                        // Clic en cualquier otro lado de la fila
                        control.irADetalleCliente(cliente);
                    }
                }
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tblClientes);
        scrollTabla.getViewport().setBackground(COLOR_PANEL_INTERNO);
        scrollTabla.setBorder(null);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        // Botón "Registrar Cliente"
        btnRegistrarCliente = new JButton("Registrar Cliente") {
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
        btnRegistrarCliente.setBounds(570, 445, 150, 35);
        btnRegistrarCliente.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegistrarCliente.setForeground(Color.WHITE);
        btnRegistrarCliente.setContentAreaFilled(false);
        btnRegistrarCliente.setBorderPainted(false);
        btnRegistrarCliente.setFocusPainted(false);
        btnRegistrarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrarCliente.addActionListener(this::btnRegistrarClienteActionPerformed);
        panelPrincipal.add(btnRegistrarCliente);

        setContentPane(panelPrincipal);
    }

    private void cargarDatosUsuario() {
        if (usuario != null) {
            lblUsuario.setText(usuario.getNombre());
        }
    }

    // la vista solo pinta la lista que le entrega el coordinador
    public void mostrarClientes(List<ClienteDTO> clientes) {
        modeloTabla.setRowCount(0);
        clientesActuales = clientes;
        if (clientes != null) {
            for (ClienteDTO cliente : clientes) {
                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    "  ⋮  "
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private void filtrarClientes() {
        String texto = txtBusqueda.getText();
        if (PLACEHOLDER_BUSQUEDA.equals(texto)) {
            texto = "";
        }
        control.buscarClientes(texto);
    }

    private void btnLimpiarFiltrosActionPerformed(ActionEvent evt) {
        txtBusqueda.setText("");
        control.limpiarFiltrosClientes();
    }

    private void btnRegistrarClienteActionPerformed(ActionEvent evt) {
        control.irARegistrarCliente();
    }
}
