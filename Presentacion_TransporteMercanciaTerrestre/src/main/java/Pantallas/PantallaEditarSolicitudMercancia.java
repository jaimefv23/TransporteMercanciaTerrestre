/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import javax.swing.JFrame;
import DTOS.MercanciaDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author Jaime
 */
public class PantallaEditarSolicitudMercancia extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private MercanciaDTO mercanciaDTO;

    private JTextField txtPeso;
    private JTextField txtDimensiones;
    private JComboBox<String> cbTipoEmpaque;
    private JTextField txtObservaciones;

    private JCheckBox chkFragil;
    private JCheckBox chkTemperatura;
    private JCheckBox chkManiobras;
    private JCheckBox chkPeligrosa;

    private JButton btnCancelar;
    private JButton btnGuardar;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_CAMPO = new Color(225, 225, 225);
    private static final Color COLOR_ROJO = new Color(230, 20, 20);
    private static final Color COLOR_VERDE = new Color(30, 185, 60);

    public PantallaEditarSolicitudMercancia(ControlMercanciaTerrestre control, MercanciaDTO mercanciaDTO) {
        this.control = control;
        this.mercanciaDTO = mercanciaDTO;
        initComponents();
        cargarDatosMercancia();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Revision");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 620);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 35, 25, 35));

        // Header Superior: Banner "Revision"
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
        panelBanner.setPreferredSize(new Dimension(0, 65));
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Revision");
        lblTituloGeneral.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);

        // Panel Central
        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);

        // Líneas laterales de acento
        JPanel lineaIzquierda = createVerticalLine();
        lineaIzquierda.setBounds(20, 20, 2, 430);
        panelCentro.add(lineaIzquierda);

        JPanel lineaDerecha = createVerticalLine();
        lineaDerecha.setBounds(655, 20, 2, 430);
        panelCentro.add(lineaDerecha);

        // Campo 1: Peso Individual
        txtPeso = createPrefixedTextField("Peso Individual: ", "1.50 Kg");
        txtPeso.setBounds(45, 30, 585, 40);
        panelCentro.add(txtPeso);

        // Campo 2: Dimensiones / Volumen
        txtDimensiones = createPrefixedTextField("Dimensiones / Volumen: Largo × Ancho × Alto (en metros): ", "1×0.5×1");
        txtDimensiones.setBounds(45, 85, 585, 40);
        panelCentro.add(txtDimensiones);

        // Campo 3: Selector de Empaque
        cbTipoEmpaque = new JComboBox<>(new String[]{
            "Empaque: Tarimas",
            "Empaque: Cajas rígidas de cartón",
            "Empaque: Bultos",
            "Empaque: Contenedor"
        });
        cbTipoEmpaque.setBounds(45, 140, 585, 40);
        cbTipoEmpaque.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cbTipoEmpaque.setBackground(COLOR_CAMPO);
        cbTipoEmpaque.setForeground(Color.BLACK);
        panelCentro.add(cbTipoEmpaque);

        // Campo 4: Observaciones
        txtObservaciones = createPrefixedTextField("Observaciones: ", "Mucho cuidado al transportarlo por su fragilidad.");
        txtObservaciones.setBounds(45, 195, 585, 75);
        panelCentro.add(txtObservaciones);

        // Checkboxes Condiciones Especiales
        chkFragil = createCustomCheckBox("Frágil", true);
        chkFragil.setBounds(65, 285, 500, 24);
        panelCentro.add(chkFragil);

        chkTemperatura = createCustomCheckBox("Control de Temperatura (Refrigerado / Congelado)", true);
        chkTemperatura.setBounds(65, 320, 500, 24);
        panelCentro.add(chkTemperatura);

        chkManiobras = createCustomCheckBox("Requiere Maniobras Especiales de Descarga", false);
        chkManiobras.setBounds(65, 355, 500, 24);
        panelCentro.add(chkManiobras);

        chkPeligrosa = createCustomCheckBox("Peligrosa / Manejo Especial", false);
        chkPeligrosa.setBounds(65, 390, 500, 24);
        panelCentro.add(chkPeligrosa);

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
        btnCancelar.setBounds(45, 435, 130, 36);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        panelCentro.add(btnCancelar);

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
        btnGuardar.setBounds(500, 435, 130, 36);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        panelCentro.add(btnGuardar);

        panelPrincipal.add(panelBanner, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    private void parseAndSetDimensions(String raw) {
        try {
            String[] parts = raw.split("[×xX]");
            if (parts.length >= 3) {
                mercanciaDTO.setLargo(parseDouble(parts[0].trim(), mercanciaDTO.getLargo()));
                mercanciaDTO.setAncho(parseDouble(parts[1].trim(), mercanciaDTO.getAncho()));
                mercanciaDTO.setAlto(parseDouble(parts[2].trim(), mercanciaDTO.getAlto()));
            }
        } catch (Exception ignored) {
        }
    }

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
        field.setBorder(new EmptyBorder(0, 12, 0, 12));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
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
        chk.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chk.setFocusPainted(false);
        return chk;
    }

    private JPanel createVerticalLine() {
        JPanel line = new JPanel();
        line.setBackground(COLOR_NARANJA);
        return line;
    }

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
    
    private void cargarDatosMercancia() {
        if (mercanciaDTO != null) {
            txtPeso.setText(mercanciaDTO.getPeso() + " Kg");
            txtDimensiones.setText(mercanciaDTO.getLargo() + "×" + mercanciaDTO.getAncho() + "×" + mercanciaDTO.getAlto());

            // el empaque
            String emp = mercanciaDTO.getTipoEmpaque();
            boolean encontrado = false;
            for (int i = 0; emp != null && i < cbTipoEmpaque.getItemCount(); i++) {
                if (cbTipoEmpaque.getItemAt(i).toLowerCase().contains(emp.toLowerCase())) {
                    cbTipoEmpaque.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado && emp != null) {
                cbTipoEmpaque.addItem("Empaque: " + emp);
                cbTipoEmpaque.setSelectedItem("Empaque: " + emp);
            }
        
            txtObservaciones.setText(mercanciaDTO.getInfoAdicional() != null ? mercanciaDTO.getInfoAdicional() : "");
            
            // marcar checkboxes 
            String cond = mercanciaDTO.getCondicionesEsp() != null ? mercanciaDTO.getCondicionesEsp().toLowerCase() : "";
            chkFragil.setSelected(cond.contains("frágil") || cond.contains("fragil"));
            chkTemperatura.setSelected(cond.contains("temperatura") || cond.contains("refrigerado"));
            chkManiobras.setSelected(cond.contains("maniobras"));
            chkPeligrosa.setSelected(cond.contains("peligrosa"));
        }
    }

    private void btnCancelarActionPerformed(ActionEvent evt) {
        control.irAEvaluacion();
    }

    private void btnGuardarActionPerformed(ActionEvent evt) {
        String peso = getCleanValue(txtPeso, "Peso Individual: ").replaceAll("(?i)\\s*kg\\s*$", "").trim();
        String dimensiones = getCleanValue(txtDimensiones, "Dimensiones / Volumen: Largo × Ancho × Alto (en metros): ");
        String empaque = cbTipoEmpaque.getSelectedItem() == null ? null
                : ((String) cbTipoEmpaque.getSelectedItem()).replace("Empaque: ", "").trim();
        String observaciones = getCleanValue(txtObservaciones, "Observaciones: ");

        StringBuilder sb = new StringBuilder();
        if (chkFragil.isSelected()) sb.append("Frágil, ");
        if (chkTemperatura.isSelected()) sb.append("Control de temperatura, ");
        if (chkManiobras.isSelected()) sb.append("Maniobras especiales, ");
        if (chkPeligrosa.isSelected()) sb.append("Peligrosa, ");
        String condiciones = sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "Ninguna";

        control.guardarEdicionMercancia(mercanciaDTO, peso, dimensiones, empaque, observaciones, condiciones);
    }
}
