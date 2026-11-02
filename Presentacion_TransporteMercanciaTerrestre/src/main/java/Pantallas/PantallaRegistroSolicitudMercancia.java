/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import DTOS.MercanciaDTO;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Locale;

/**
 *
 * @author Jaime
 */
public class PantallaRegistroSolicitudMercancia extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private ClienteDTO clienteDTO;
    private SolicitudTransporteDTO solicitud;

    // Campos de Texto con Prefijo Bloqueado
    private JTextField txtConcepto;
    private JTextField txtCantidad;
    private JComboBox<String> cbTipoEmpaque;
    private JTextField txtPeso;
    private JTextField txtLargo;
    private JTextField txtAncho;
    private JTextField txtAlto;
    private JTextField txtObservaciones;

    // Checkboxes Condiciones Especiales
    private JCheckBox chkFragil, chkTemperatura, chkManiobras, chkPeligrosa, chkNinguna;

    // Tabla y Resumen
    private JTable tblConceptos;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotalConceptos;
    private JLabel lblPesoTotalEstimado;
    private JLabel lblVolumenTotalEstimado;

    // Acumuladores de Totales
    private int totalConceptosCount = 0;
    private double acumuladoPesoKg = 0.0;
    private double acumuladoVolumenM3 = 0.0;

    // Botones
    private JButton btnAgregarMercancia;
    private JButton btnRegresar;
    private JButton btnCancelar;
    private JButton btnFinalizar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_FILA_TABLA = new Color(28, 28, 28);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_ROJO = new Color(230, 20, 20);
    private static final Color COLOR_CAMPO = new Color(225, 225, 225);

    public PantallaRegistroSolicitudMercancia(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud) {
        this.control = control;
        this.solicitud = solicitud;
        this.clienteDTO = solicitud.getCliente();
        initComponents();
        cargarMercanciasPrevias();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("RegistroSolicitudMercancia");
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
        panelBanner.setBounds(310, 15, 320, 38);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Nueva Solicitud - Captura de Mercancía");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 15));
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

        String nombreCliente = (clienteDTO != null) ? clienteDTO.getNombre() : "Alejandro Efraín Ibarra Armenta";
        JLabel lblInfoCliente = new JLabel("Folio Solicitud: SOL-205 | Cliente: " + nombreCliente);
        lblInfoCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblInfoCliente.setForeground(Color.WHITE);
        panelBadgeCliente.add(lblInfoCliente);
        panelPrincipal.add(panelBadgeCliente);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN FORMULARIO CONCEPTO DE MERCANCÍA
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloCaptura = new JLabel("Agregar Concepto de Mercancía");
        lblTituloCaptura.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloCaptura.setForeground(Color.WHITE);
        lblTituloCaptura.setBounds(35, 100, 300, 25);
        panelPrincipal.add(lblTituloCaptura);

        JPanel panelCaptura = createBorderedPanel();
        panelCaptura.setBounds(25, 125, 880, 260);
        panelCaptura.setLayout(null);

        // Campos de entrada con Prefijo Bloqueado
        txtConcepto = createPrefixedTextField("Concepto: ", "Pastel");
        txtConcepto.setBounds(25, 15, 330, 28);
        panelCaptura.add(txtConcepto);

        txtCantidad = createPrefixedTextField("Cantidad: ", "20");
        txtCantidad.setBounds(25, 48, 175, 28);
        panelCaptura.add(txtCantidad);

        // Selector de Empaque
        cbTipoEmpaque = new JComboBox<>(new String[]{"Tipo de Empaque: Tarimas", "Tipo de Empaque: Cajas", "Tipo de Empaque: Bultos", "Tipo de Empaque: Contenedor"});
        cbTipoEmpaque.setBounds(25, 81, 330, 28);
        cbTipoEmpaque.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbTipoEmpaque.setBackground(COLOR_CAMPO);
        cbTipoEmpaque.setForeground(Color.BLACK);
        panelCaptura.add(cbTipoEmpaque);

        txtPeso = createPrefixedTextField("Peso: ", "1.25 kg");
        txtPeso.setBounds(25, 114, 215, 28);
        panelCaptura.add(txtPeso);

        txtLargo = createPrefixedTextField("Largo: ", "0.35 m");
        txtLargo.setBounds(25, 147, 175, 28);
        panelCaptura.add(txtLargo);

        txtAncho = createPrefixedTextField("Ancho: ", "0.35 m");
        txtAncho.setBounds(25, 180, 175, 28);
        panelCaptura.add(txtAncho);

        txtAlto = createPrefixedTextField("Alto: ", "0.20 m");
        txtAlto.setBounds(25, 213, 175, 28);
        panelCaptura.add(txtAlto);

        txtObservaciones = createPrefixedTextField("Observaciones del Concepto: ", "No apilar más de 3 de alto");
        txtObservaciones.setBounds(210, 213, 330, 28);
        panelCaptura.add(txtObservaciones);

        // Checkboxes Condiciones Especiales
        JLabel lblCondiciones = new JLabel("Condiciones Especiales de Manejo");
        lblCondiciones.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblCondiciones.setForeground(Color.WHITE);
        lblCondiciones.setBounds(560, 20, 280, 20);
        panelCaptura.add(lblCondiciones);

        chkFragil = createCustomCheckBox("Frágil", false);
        chkFragil.setBounds(525, 55, 300, 22);
        panelCaptura.add(chkFragil);

        chkTemperatura = createCustomCheckBox("Control de Temperatura (Refrigerado / Congelado)", true);
        chkTemperatura.setBounds(525, 85, 340, 22);
        panelCaptura.add(chkTemperatura);

        chkManiobras = createCustomCheckBox("Requiere Maniobras Especiales de Descarga", false);
        chkManiobras.setBounds(525, 115, 340, 22);
        panelCaptura.add(chkManiobras);

        chkPeligrosa = createCustomCheckBox("Peligrosa / Manejo Especial", false);
        chkPeligrosa.setBounds(525, 145, 300, 22);
        panelCaptura.add(chkPeligrosa);

        chkNinguna = createCustomCheckBox("Ninguna", false);
        chkNinguna.setBounds(525, 175, 200, 22);
        panelCaptura.add(chkNinguna);

        // Botón Agregar Mercancía
        btnAgregarMercancia = new JButton("Agregar Mercancía") {
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
        btnAgregarMercancia.setBounds(700, 215, 155, 32);
        btnAgregarMercancia.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAgregarMercancia.setForeground(Color.WHITE);
        btnAgregarMercancia.setContentAreaFilled(false);
        btnAgregarMercancia.setBorderPainted(false);
        btnAgregarMercancia.setFocusPainted(false);
        btnAgregarMercancia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarMercancia.addActionListener(this::btnAgregarMercanciaActionPerformed);
        panelCaptura.add(btnAgregarMercancia);

        panelPrincipal.add(panelCaptura);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN CONCEPTOS AGREGADOS Y RESUMEN TOTAL
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloTabla = new JLabel("Conceptos Agregados a la Solicitud");
        lblTituloTabla.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloTabla.setForeground(Color.WHITE);
        lblTituloTabla.setBounds(35, 395, 350, 25);
        panelPrincipal.add(lblTituloTabla);

        // Tabla Conceptos
        JPanel panelTabla = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
            }
        };
        panelTabla.setOpaque(false);
        panelTabla.setBounds(25, 425, 520, 195);
        panelTabla.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Descripción", "Cantidad", "Empaque", "Peso Total"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblConceptos = new JTable(modeloTabla);
        tblConceptos.setBackground(COLOR_PANEL_INTERNO);
        tblConceptos.setForeground(Color.WHITE);
        tblConceptos.setRowHeight(32);
        tblConceptos.setShowGrid(false);
        tblConceptos.setIntercellSpacing(new Dimension(0, 4));
        tblConceptos.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tblConceptos.getTableHeader().setBackground(COLOR_PANEL_INTERNO);
        tblConceptos.getTableHeader().setForeground(Color.WHITE);
        tblConceptos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        tblConceptos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

        JScrollPane scrollTabla = new JScrollPane(tblConceptos);
        scrollTabla.getViewport().setBackground(COLOR_PANEL_INTERNO);
        scrollTabla.setBorder(null);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        // Panel Resumen Total
        JPanel panelResumen = createBorderedPanel();
        panelResumen.setBounds(565, 425, 340, 195);
        panelResumen.setLayout(null);

        JLabel lblTituloResumen = new JLabel("Resumen Total:");
        lblTituloResumen.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloResumen.setForeground(Color.WHITE);
        lblTituloResumen.setBounds(20, 15, 200, 25);
        panelResumen.add(lblTituloResumen);

        JSeparator sepResumen = new JSeparator();
        sepResumen.setBounds(15, 45, 310, 2);
        sepResumen.setForeground(COLOR_NARANJA);
        panelResumen.add(sepResumen);

        lblTotalConceptos = new JLabel("Total de Conceptos: 0");
        lblTotalConceptos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTotalConceptos.setForeground(Color.WHITE);
        lblTotalConceptos.setBounds(30, 65, 280, 22);
        panelResumen.add(lblTotalConceptos);

        lblPesoTotalEstimado = new JLabel("Peso Total Estimado: 0 kg");
        lblPesoTotalEstimado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblPesoTotalEstimado.setForeground(Color.WHITE);
        lblPesoTotalEstimado.setBounds(30, 100, 280, 22);
        panelResumen.add(lblPesoTotalEstimado);

        lblVolumenTotalEstimado = new JLabel("Volumen Total Estimado: 0.00 m^3");
        lblVolumenTotalEstimado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblVolumenTotalEstimado.setForeground(Color.WHITE);
        lblVolumenTotalEstimado.setBounds(30, 135, 280, 22);
        panelResumen.add(lblVolumenTotalEstimado);

        panelPrincipal.add(panelResumen);

        // ═════════════════════════════════════════════════════════════════
        // BOTONES INFERIORES
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

        btnFinalizar = new JButton("Finalizar Registro y Enviar a Evaluación") {
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
        btnFinalizar.setBounds(580, 635, 325, 32);
        btnFinalizar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setContentAreaFilled(false);
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizar.addActionListener(this::btnFinalizarActionPerformed);
        panelPrincipal.add(btnFinalizar);

        setContentPane(panelPrincipal);
    }

    // ═════════════════════════════════════════════════════════════════
    // COMPONENTES PERSONALIZADOS Y FILTRO DE PREFIJO BLOQUEADO
    // ═════════════════════════════════════════════════════════════════

    private JTextField createPrefixedTextField(String prefix, String initialValue) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CAMPO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(0, 10, 0, 10));
        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setForeground(Color.BLACK);

        field.setText(prefix + initialValue);

        ((AbstractDocument) field.getDocument()).setDocumentFilter(new PrefixedDocumentFilter(prefix));
        return field;
    }

    private String getCleanValue(JTextField field, String prefix) {
        String fullText = field.getText();
        if (fullText.startsWith(prefix)) {
            return fullText.substring(prefix.length()).trim();
        }
        return fullText.trim();
    }

    private int parseInteger(String text, int defaultValue) {
        try {
            String clean = text.replaceAll("[^0-9]", "");
            return clean.isEmpty() ? defaultValue : Integer.parseInt(clean);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private double parseDouble(String text, double defaultValue) {
        try {
            String clean = text.replaceAll("[^0-9.]", "");
            return clean.isEmpty() ? defaultValue : Double.parseDouble(clean);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private JCheckBox createCustomCheckBox(String text, boolean selected) {
        JCheckBox chk = new JCheckBox(text, selected);
        chk.setOpaque(false);
        chk.setForeground(Color.WHITE);
        chk.setFont(new Font("SansSerif", Font.PLAIN, 12));
        chk.setFocusPainted(false);
        return chk;
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

    // Clase para impedir borrar o editar el prefijo del campo
    private static class PrefixedDocumentFilter extends DocumentFilter {
        private final String prefix;

        public PrefixedDocumentFilter(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (offset < prefix.length()) {
                offset = prefix.length();
            }
            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (offset < prefix.length()) {
                int extra = prefix.length() - offset;
                offset = prefix.length();
                length = Math.max(0, length - extra);
            }
            super.replace(fb, offset, length, text, attrs);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (offset < prefix.length()) {
                int extra = prefix.length() - offset;
                offset = prefix.length();
                length = Math.max(0, length - extra);
            }
            if (length > 0) {
                super.remove(fb, offset, length);
            }
        }
    }
    
    /** Muestra las mercancías ya capturadas (por ejemplo, al regresar a esta pantalla). */
    private void cargarMercanciasPrevias() {
        for (MercanciaDTO m : solicitud.getMercancias()) {
            agregarFilaYTotales(m);
        }
    }

    private void agregarFilaYTotales(MercanciaDTO m) {
        double pesoTotalConcepto = m.getCantidad() * m.getPeso();

        Object[] fila = {
            m.getNombre(),
            m.getCantidad(),
            m.getTipoEmpaque(),
            String.format(Locale.US, "%.0f kg", pesoTotalConcepto)
        };
        modeloTabla.addRow(fila);

        totalConceptosCount++;
        acumuladoPesoKg += pesoTotalConcepto;
        acumuladoVolumenM3 += (m.getLargo() * m.getAncho() * m.getAlto() * m.getCantidad());

        lblTotalConceptos.setText("Total de Conceptos: " + totalConceptosCount);
        lblPesoTotalEstimado.setText(String.format(Locale.US, "Peso Total Estimado: %.0f kg", acumuladoPesoKg));
        lblVolumenTotalEstimado.setText(String.format(Locale.US, "Volumen Total Estimado: %.3f m^3", acumuladoVolumenM3));
    }

    /** Quita la unidad que el usuario pudo escribir ("1.25 kg", "0.35 m"). */
    private String quitarUnidad(String texto, String unidad) {
        return texto.replaceAll("(?i)\\s*" + unidad + "\\s*$", "").trim();
    }

    private void btnAgregarMercanciaActionPerformed(ActionEvent evt) {
        String empaqueFull = (String) cbTipoEmpaque.getSelectedItem();
        String tipoEmpaque = empaqueFull != null ? empaqueFull.replace("Tipo de Empaque: ", "").trim() : null;

        StringBuilder condiciones = new StringBuilder();
        if (chkFragil.isSelected()) condiciones.append("Frágil, ");
        if (chkTemperatura.isSelected()) condiciones.append("Control de temperatura, ");
        if (chkManiobras.isSelected()) condiciones.append("Maniobras especiales, ");
        if (chkPeligrosa.isSelected()) condiciones.append("Peligrosa, ");
        String condEsp = condiciones.length() > 0
                ? condiciones.substring(0, condiciones.length() - 2)
                : "Ninguna";

        MercanciaDTO m = control.agregarMercancia(
                getCleanValue(txtConcepto, "Concepto: "),
                getCleanValue(txtCantidad, "Cantidad: "),
                tipoEmpaque,
                quitarUnidad(getCleanValue(txtPeso, "Peso: "), "kg"),
                quitarUnidad(getCleanValue(txtLargo, "Largo: "), "m"),
                quitarUnidad(getCleanValue(txtAncho, "Ancho: "), "m"),
                quitarUnidad(getCleanValue(txtAlto, "Alto: "), "m"),
                condEsp,
                getCleanValue(txtObservaciones, "Observaciones del Concepto: "));

        if (m != null) {
            agregarFilaYTotales(m);
        }
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irARegistroFechas();
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.solicitarCancelacion();
    }

    private void btnFinalizarActionPerformed(ActionEvent evt) {
        control.finalizarCapturaMercancia();
    }

}
