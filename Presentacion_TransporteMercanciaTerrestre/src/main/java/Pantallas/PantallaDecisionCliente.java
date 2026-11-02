/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import DTOS.PropuestaOperativaDTO;
import DTOS.CotizacionDTO;
import Control.ControlMercanciaTerrestre;
import javax.swing.JFrame;
import DTOS.ClienteDTO;
import DTOS.MercanciaDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
/**
 *
 * @author Jaime
 */
public class PantallaDecisionCliente extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private PropuestaOperativaDTO propuesta;
    private CotizacionDTO cotizacion;
    private SolicitudTransporteDTO solicitud;

    // Checkboxes Conceptos
    private JCheckBox chkManiobraBloq;
    private JCheckBox chkSeguroOpcional;

    // Checkboxes Recolección
    private JCheckBox chkRecoleccionSi;
    private JCheckBox chkRecoleccionNo;

    // Checkboxes Decisión
    private JCheckBox chkAceptacionTotal;
    private JCheckBox chkSolicitarModificaciones;
    private JCheckBox chkRechazarPropuesta;

    // Etiquetas de Desglose
    private JLabel lblSubtotalVal;
    private JLabel lblDescuentoTag;
    private JLabel lblDescuentoVal;
    private JLabel lblIvaVal;
    private JLabel lblTotalAutorizadoVal;

    private JTextArea txtNotasAcuerdos;

    private JButton btnRegresar;
    private JButton btnRegistrarAceptacion;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_AMARILLO_TEXTO = new Color(245, 210, 40);
    private static final Color COLOR_VERDE_TEXTO = new Color(50, 205, 50);
    private static final Color COLOR_BADGE_FOLIO = new Color(90, 90, 90);
    private static final Color COLOR_CAMPO_NOTAS = new Color(225, 225, 225);

    private final DecimalFormat df = new DecimalFormat("$#,##0.00", new DecimalFormatSymbols(Locale.US));

    public PantallaDecisionCliente(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        this.control = control;
        this.solicitud = solicitud;
        this.propuesta = propuesta;
        this.cotizacion = cotizacion;
        initComponents();
        actualizarDesglose(cotizacion);
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Registro Decisión Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(940, 780);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Banner Superior
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
        panelBanner.setBounds(180, 15, 580, 65);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Transporte de Mercancía Terrestre");
        lblTituloGeneral.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 24));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Badge Cotización y Cliente
        JPanel panelFolio = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_BADGE_FOLIO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
            }
        };
        panelFolio.setOpaque(false);
        panelFolio.setBounds(25, 95, 500, 26);
        panelFolio.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));

        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        ClienteDTO cliente = (solicitud != null) ? solicitud.getCliente() : null;
        String nombreCliente = (cliente != null) ? cliente.getNombre() : "Alejandro Efraín Ibarra Armenta";

        JLabel lblFolio = new JLabel("Cotización #" + folioStr + " | Cliente: " + nombreCliente);
        lblFolio.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblFolio.setForeground(Color.WHITE);
        panelFolio.add(lblFolio);
        panelPrincipal.add(panelFolio);

        // ═════════════════════════════════════════════════════════════════
        // COLUMNA IZQUIERDA
        // ═════════════════════════════════════════════════════════════════

        // 1. Resumen Cotización
        JLabel lblTituloResumen = new JLabel("Resumen Cotización");
        lblTituloResumen.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloResumen.setForeground(Color.WHITE);
        lblTituloResumen.setBounds(25, 132, 350, 22);
        panelPrincipal.add(lblTituloResumen);

        JSeparator sepResumen = new JSeparator();
        sepResumen.setBounds(25, 156, 430, 2);
        sepResumen.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepResumen);

        JLabel lblClienteTag = new JLabel("Cliente:");
        lblClienteTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblClienteTag.setForeground(COLOR_AMARILLO_TEXTO);
        lblClienteTag.setBounds(25, 165, 60, 20);
        panelPrincipal.add(lblClienteTag);

        JLabel lblClienteVal = new JLabel(nombreCliente);
        lblClienteVal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblClienteVal.setForeground(Color.WHITE);
        lblClienteVal.setBounds(85, 165, 360, 20);
        panelPrincipal.add(lblClienteVal);

        PuntoTrasladoDTO orig = (solicitud != null) ? solicitud.getOrigen() : null;
        PuntoTrasladoDTO dest = (solicitud != null) ? solicitud.getDestino() : null;
        String origStr = (orig != null && orig.getCiudad() != null) ? orig.getCiudad() : "Cd. Obregón";
        String destStr = (dest != null && dest.getCiudad() != null) ? dest.getCiudad() : "Hermosillo";

        JLabel lblRutaTag = new JLabel("Origen / Destino:");
        lblRutaTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblRutaTag.setForeground(COLOR_AMARILLO_TEXTO);
        lblRutaTag.setBounds(25, 190, 110, 20);
        panelPrincipal.add(lblRutaTag);

        JLabel lblRutaVal = new JLabel(origStr + " → " + destStr);
        lblRutaVal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblRutaVal.setForeground(Color.WHITE);
        lblRutaVal.setBounds(140, 190, 310, 20);
        panelPrincipal.add(lblRutaVal);

        String unidadStr = propuesta.getTipoUnidadSugerida().getNombre();

        JLabel lblUnidadTag = new JLabel("Unidad:");
        lblUnidadTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblUnidadTag.setForeground(COLOR_AMARILLO_TEXTO);
        lblUnidadTag.setBounds(25, 215, 60, 20);
        panelPrincipal.add(lblUnidadTag);

        JLabel lblUnidadVal = new JLabel(unidadStr);
        lblUnidadVal.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblUnidadVal.setForeground(Color.WHITE);
        lblUnidadVal.setBounds(85, 215, 360, 20);
        panelPrincipal.add(lblUnidadVal);

        // 2. Conceptos Cotizados
        JLabel lblTituloConceptos = new JLabel("Conceptos Cotizados");
        lblTituloConceptos.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloConceptos.setForeground(Color.WHITE);
        lblTituloConceptos.setBounds(25, 250, 350, 22);
        panelPrincipal.add(lblTituloConceptos);

        JSeparator sepConceptos = new JSeparator();
        sepConceptos.setBounds(25, 274, 430, 2);
        sepConceptos.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepConceptos);

        chkManiobraBloq = new JCheckBox("Maniobra Carga [Bloq]", true);
        chkManiobraBloq.setOpaque(false);
        chkManiobraBloq.setEnabled(false);
        chkManiobraBloq.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkManiobraBloq.setForeground(Color.WHITE);
        chkManiobraBloq.setBounds(25, 285, 250, 24);
        panelPrincipal.add(chkManiobraBloq);

        chkSeguroOpcional = new JCheckBox("Seguro Carga (Opcional)", propuesta.isRequiereSeguroCarga());
        chkSeguroOpcional.setOpaque(false);
        chkSeguroOpcional.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkSeguroOpcional.setForeground(Color.WHITE);
        chkSeguroOpcional.setBounds(25, 318, 250, 24);
        chkSeguroOpcional.addActionListener(e -> {
            control.cambiarSeguroCarga(chkSeguroOpcional.isSelected());
        });
        panelPrincipal.add(chkSeguroOpcional);

        // 3. Desglose
        JLabel lblTituloDesglose = new JLabel("Desglose");
        lblTituloDesglose.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDesglose.setForeground(Color.WHITE);
        lblTituloDesglose.setBounds(25, 365, 200, 22);
        panelPrincipal.add(lblTituloDesglose);

        JSeparator sepDesglose = new JSeparator();
        sepDesglose.setBounds(25, 389, 430, 2);
        sepDesglose.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepDesglose);

        int yDesglose = 400;

        JLabel lblSubtotalTag = new JLabel("Subtotal");
        lblSubtotalTag.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtotalTag.setForeground(Color.WHITE);
        lblSubtotalTag.setBounds(25, yDesglose, 150, 20);
        panelPrincipal.add(lblSubtotalTag);

        lblSubtotalVal = new JLabel("$0.00");
        lblSubtotalVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblSubtotalVal.setForeground(Color.WHITE);
        lblSubtotalVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSubtotalVal.setBounds(270, yDesglose, 185, 20);
        panelPrincipal.add(lblSubtotalVal);

        yDesglose += 28;

        lblDescuentoTag = new JLabel("Descuento (5%)");
        lblDescuentoTag.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDescuentoTag.setForeground(COLOR_VERDE_TEXTO);
        lblDescuentoTag.setBounds(25, yDesglose, 180, 20);
        panelPrincipal.add(lblDescuentoTag);

        lblDescuentoVal = new JLabel("-$0.00");
        lblDescuentoVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDescuentoVal.setForeground(COLOR_VERDE_TEXTO);
        lblDescuentoVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescuentoVal.setBounds(270, yDesglose, 185, 20);
        panelPrincipal.add(lblDescuentoVal);

        yDesglose += 28;

        JLabel lblIvaTag = new JLabel("IVA (16%)");
        lblIvaTag.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblIvaTag.setForeground(Color.GRAY);
        lblIvaTag.setBounds(25, yDesglose, 150, 20);
        panelPrincipal.add(lblIvaTag);

        lblIvaVal = new JLabel("$0.00");
        lblIvaVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblIvaVal.setForeground(Color.GRAY);
        lblIvaVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIvaVal.setBounds(270, yDesglose, 185, 20);
        panelPrincipal.add(lblIvaVal);

        yDesglose += 35;

        JLabel lblTotalAutTag = new JLabel("Total Autorizado");
        lblTotalAutTag.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTotalAutTag.setForeground(Color.WHITE);
        lblTotalAutTag.setBounds(25, yDesglose, 180, 22);
        panelPrincipal.add(lblTotalAutTag);

        lblTotalAutorizadoVal = new JLabel("$0.00");
        lblTotalAutorizadoVal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotalAutorizadoVal.setForeground(Color.WHITE);
        lblTotalAutorizadoVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalAutorizadoVal.setBounds(250, yDesglose, 205, 22);
        panelPrincipal.add(lblTotalAutorizadoVal);

        // ═════════════════════════════════════════════════════════════════
        // COLUMNA DERECHA
        // ═════════════════════════════════════════════════════════════════

        // 1. Recolección
        JLabel lblTituloRecoleccion = new JLabel("Recolección");
        lblTituloRecoleccion.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloRecoleccion.setForeground(Color.WHITE);
        lblTituloRecoleccion.setBounds(485, 132, 200, 22);
        panelPrincipal.add(lblTituloRecoleccion);

        JSeparator sepRecoleccion = new JSeparator();
        sepRecoleccion.setBounds(485, 156, 425, 2);
        sepRecoleccion.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepRecoleccion);

        chkRecoleccionSi = new JCheckBox("Si", true);
        chkRecoleccionSi.setOpaque(false);
        chkRecoleccionSi.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkRecoleccionSi.setForeground(Color.WHITE);
        chkRecoleccionSi.setBounds(485, 165, 80, 24);
        chkRecoleccionSi.addActionListener(e -> {
            chkRecoleccionSi.setSelected(true);
            chkRecoleccionNo.setSelected(false);
        });
        panelPrincipal.add(chkRecoleccionSi);

        chkRecoleccionNo = new JCheckBox("No", false);
        chkRecoleccionNo.setOpaque(false);
        chkRecoleccionNo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkRecoleccionNo.setForeground(Color.WHITE);
        chkRecoleccionNo.setBounds(485, 195, 80, 24);
        chkRecoleccionNo.addActionListener(e -> {
            chkRecoleccionSi.setSelected(false);
            chkRecoleccionNo.setSelected(true);
        });
        panelPrincipal.add(chkRecoleccionNo);

        // 2. Decisión del Cliente
        JLabel lblTituloDecision = new JLabel("Decisión del Cliente");
        lblTituloDecision.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloDecision.setForeground(Color.WHITE);
        lblTituloDecision.setBounds(485, 235, 250, 22);
        panelPrincipal.add(lblTituloDecision);

        JSeparator sepDecision = new JSeparator();
        sepDecision.setBounds(485, 259, 425, 2);
        sepDecision.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepDecision);

        chkAceptacionTotal = new JCheckBox("Aceptación Total", true);
        chkAceptacionTotal.setOpaque(false);
        chkAceptacionTotal.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkAceptacionTotal.setForeground(Color.WHITE);
        chkAceptacionTotal.setBounds(485, 268, 250, 24);
        chkAceptacionTotal.addActionListener(e -> seleccionarDecision("ACEPTACION"));
        panelPrincipal.add(chkAceptacionTotal);

        chkSolicitarModificaciones = new JCheckBox("Solicitar Modificaciones", false);
        chkSolicitarModificaciones.setOpaque(false);
        chkSolicitarModificaciones.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkSolicitarModificaciones.setForeground(Color.WHITE);
        chkSolicitarModificaciones.setBounds(485, 305, 250, 24);
        chkSolicitarModificaciones.addActionListener(e -> seleccionarDecision("MODIFICACIONES"));
        panelPrincipal.add(chkSolicitarModificaciones);

        chkRechazarPropuesta = new JCheckBox("Rechazar Propuesta", false);
        chkRechazarPropuesta.setOpaque(false);
        chkRechazarPropuesta.setFont(new Font("SansSerif", Font.PLAIN, 13));
        chkRechazarPropuesta.setForeground(Color.WHITE);
        chkRechazarPropuesta.setBounds(485, 342, 250, 24);
        chkRechazarPropuesta.addActionListener(e -> seleccionarDecision("RECHAZAR"));
        panelPrincipal.add(chkRechazarPropuesta);

        // 3. Notas / Acuerdos
        JLabel lblTituloNotas = new JLabel("Notas / Acuerdos");
        lblTituloNotas.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTituloNotas.setForeground(Color.WHITE);
        lblTituloNotas.setBounds(485, 385, 250, 22);
        panelPrincipal.add(lblTituloNotas);

        JSeparator sepNotas = new JSeparator();
        sepNotas.setBounds(485, 409, 425, 2);
        sepNotas.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepNotas);

        txtNotasAcuerdos = new JTextArea("El Cliente autorizo presencialmente la solicitud de transporte.");
        txtNotasAcuerdos.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtNotasAcuerdos.setLineWrap(true);
        txtNotasAcuerdos.setWrapStyleWord(true);
        txtNotasAcuerdos.setBackground(COLOR_CAMPO_NOTAS);
        txtNotasAcuerdos.setForeground(Color.BLACK);

        JScrollPane scrollNotas = new JScrollPane(txtNotasAcuerdos);
        scrollNotas.setBounds(485, 420, 425, 95);
        panelPrincipal.add(scrollNotas);

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
        btnRegresar.setBounds(25, 635, 120, 36);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        btnRegistrarAceptacion = new JButton("Registrar Aceptación") {
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
        btnRegistrarAceptacion.setBounds(710, 635, 200, 36);
        btnRegistrarAceptacion.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegistrarAceptacion.setForeground(Color.WHITE);
        btnRegistrarAceptacion.setContentAreaFilled(false);
        btnRegistrarAceptacion.setBorderPainted(false);
        btnRegistrarAceptacion.setFocusPainted(false);
        btnRegistrarAceptacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrarAceptacion.addActionListener(this::btnRegistrarAceptacionActionPerformed);
        panelPrincipal.add(btnRegistrarAceptacion);

        setContentPane(panelPrincipal);
    }

    private void seleccionarDecision(String tipo) {
        chkAceptacionTotal.setSelected("ACEPTACION".equals(tipo));
        chkSolicitarModificaciones.setSelected("MODIFICACIONES".equals(tipo));
        chkRechazarPropuesta.setSelected("RECHAZAR".equals(tipo));
    }

   
    public void actualizarDesglose(CotizacionDTO cot) {
        this.cotizacion = cot;
        lblSubtotalVal.setText(df.format(cot.getSubtotal()));

        if (cot.isAplicaDescuento()) {
            lblDescuentoTag.setVisible(true);
            lblDescuentoVal.setVisible(true);
            lblDescuentoVal.setText("-" + df.format(cot.getDescuento()));
        } else {
            lblDescuentoTag.setVisible(false);
            lblDescuentoVal.setVisible(false);
        }

        lblIvaVal.setText(df.format(cot.getIva()));
        lblTotalAutorizadoVal.setText(df.format(cot.getTotal()));
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irARevisarCotizacion();
    }

    private void btnRegistrarAceptacionActionPerformed(ActionEvent evt) {
        String tipo = null;
        if (chkAceptacionTotal.isSelected()) {
            tipo = "ACEPTACION";
        } else if (chkSolicitarModificaciones.isSelected()) {
            tipo = "MODIFICACIONES";
        } else if (chkRechazarPropuesta.isSelected()) {
            tipo = "RECHAZAR";
        }
        control.registrarDecisionCliente(tipo, txtNotasAcuerdos.getText());
    }
}
