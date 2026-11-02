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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author Jaime
 */
public class PantallaAceptacionRegistrada extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private PropuestaOperativaDTO propuesta;
    private CotizacionDTO cotizacion;
    private SolicitudTransporteDTO solicitud;

    private JButton btnRegresar;
    private JButton btnGenerarOrden;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_VERDE_BADGE = new Color(0, 160, 20);
    private static final Color COLOR_AMARILLO_ETIQUETA = new Color(245, 210, 40);
    private static final Color COLOR_VERDE_TEXTO = new Color(50, 205, 50);

    private final DecimalFormat df = new DecimalFormat("$#,##0.00", new DecimalFormatSymbols(Locale.US));

    public PantallaAceptacionRegistrada(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        this.control = control;
        this.solicitud = solicitud;
        this.propuesta = propuesta;
        this.cotizacion = cotizacion;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Aceptación Registrada Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(940, 780);
        setResizable(false);

        JPanel panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(COLOR_FONDO);

        // Banner Superior "Transporte de Mercancía Terrestre"
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

        // Insignia Verde: "✓ Aceptación Registrada Exitosamente!"
        JPanel panelBadge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_VERDE_BADGE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
            }
        };
        panelBadge.setOpaque(false);
        panelBadge.setBounds(30, 95, 340, 26);
        panelBadge.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 3));

        JLabel lblBadgeText = new JLabel("✓ Aceptación Registrada Exitosamente!");
        lblBadgeText.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblBadgeText.setForeground(Color.WHITE);
        panelBadge.add(lblBadgeText);
        panelPrincipal.add(panelBadge);

        JSeparator sepEncabezado = new JSeparator();
        sepEncabezado.setBounds(30, 130, 875, 2);
        sepEncabezado.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepEncabezado);

        // Encabezado de Metadatos (Folio, Fecha, Cliente, Estado)
        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        ClienteDTO cliente = (solicitud != null) ? solicitud.getCliente() : null;
        String nombreCliente = (cliente != null) ? cliente.getNombre() : "Alejandro Efraín Ibarra Armenta";
        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        // Columna Izquierda
        addLabeledValue(panelPrincipal, "Folio:", "#" + folioStr, 30, 140, 60, 200);
        addLabeledValue(panelPrincipal, "Cliente:", nombreCliente, 30, 168, 60, 360);

        // Columna Derecha
        addLabeledValue(panelPrincipal, "Fecha:", fechaHoy, 440, 140, 60, 200);
        addLabeledValue(panelPrincipal, "Estado:", "ACEPTADA (Lista p/ Flota)", 440, 168, 60, 300);

        JSeparator sepMetadatos = new JSeparator();
        sepMetadatos.setBounds(30, 200, 875, 2);
        sepMetadatos.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepMetadatos);

        // ═════════════════════════════════════════════════════════════════
        // DETALLES OPERATIVOS AUTORIZADOS
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloOperativos = new JLabel("Detalles Operativos Autorizados");
        lblTituloOperativos.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloOperativos.setForeground(Color.WHITE);
        lblTituloOperativos.setBounds(30, 212, 380, 25);
        panelPrincipal.add(lblTituloOperativos);

        PuntoTrasladoDTO orig = (solicitud != null) ? solicitud.getOrigen() : null;
        PuntoTrasladoDTO dest = (solicitud != null) ? solicitud.getDestino() : null;
        String origStr = (orig != null && orig.getCiudad() != null) ? orig.getCiudad() : "Cd. Obregón";
        String destStr = (dest != null && dest.getCiudad() != null) ? dest.getCiudad() : "Hermosillo";

        String tiempoEstimado = propuesta.getTiempoEstimado();
        String unidadStr = propuesta.getTipoUnidadSugerida().getNombre();

        double pesoTotalKg = 0;
        int totalCantidad = 0;
        String descMercancia = "Mercancías";

        if (solicitud != null && solicitud.getMercancias() != null && !solicitud.getMercancias().isEmpty()) {
            MercanciaDTO m0 = solicitud.getMercancias().get(0);
            descMercancia = m0.getNombre();
            for (MercanciaDTO m : solicitud.getMercancias()) {
                pesoTotalKg += (m.getCantidad() * m.getPeso());
                totalCantidad += m.getCantidad();
            }
        } else {
            totalCantidad = 20;
            pesoTotalKg = 30;
            descMercancia = "Pasteles";
        }

        // Fila 1 Operativos
        addLabeledValue(panelPrincipal, "Origen:" , origStr, 25, 250, 70, 320);
        addLabeledValue(panelPrincipal, "Destino:", destStr, 440, 250, 70, 320);

        // Fila 2 Operativos
        addLabeledValue(panelPrincipal, "Unidad:", unidadStr, 25, 280, 70, 320);
        addLabeledValue(panelPrincipal, "Tiempo Estimado:", tiempoEstimado, 440, 280, 130, 250);

        // Fila 3 Operativos
        addLabeledValue(panelPrincipal, "Mercancia:", totalCantidad + " " + descMercancia, 25, 310, 85, 300);
        addLabeledValue(panelPrincipal, "Peso Total:", String.format(Locale.US, "%.0f Kg", pesoTotalKg), 440, 310, 85, 250);

        JSeparator sepOperativos = new JSeparator();
        sepOperativos.setBounds(30, 350, 875, 2);
        sepOperativos.setForeground(COLOR_NARANJA);
        panelPrincipal.add(sepOperativos);

        // ═════════════════════════════════════════════════════════════════
        // SERVICIOS APROBADOS Y TOTAL CONTRATADO
        // ═════════════════════════════════════════════════════════════════
        // Columna Izquierda: Servicios Aprobados
        JLabel lblTituloServicios = new JLabel("Servicios Aprobados");
        lblTituloServicios.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloServicios.setForeground(Color.WHITE);
        lblTituloServicios.setBounds(30, 365, 300, 25);
        panelPrincipal.add(lblTituloServicios);

        int yBullet = 405;
        addBulletPoint(panelPrincipal, "Traslado Base", yBullet);
        yBullet += 32;

        addBulletPoint(panelPrincipal, "Maniobra Especial de Carga", yBullet);
        yBullet += 32;

        if (propuesta.isRequiereSeguroCarga()) {
            addBulletPoint(panelPrincipal, "Seguro Adicional de Carga", yBullet);
        }

        // Columna Derecha: Total Contratado
        JLabel lblTituloTotal = new JLabel("Total Contratado");
        lblTituloTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloTotal.setForeground(Color.WHITE);
        lblTituloTotal.setBounds(440, 365, 300, 25);
        panelPrincipal.add(lblTituloTotal);

        // Importes entregados por la cotización (simulada)
        double subtotal = cotizacion.getSubtotal();
        boolean aplicaDescuento = cotizacion.isAplicaDescuento();
        double montoDescuento = cotizacion.getDescuento();
        double iva = cotizacion.getIva();
        double totalAutorizado = cotizacion.getTotal();

        int yTotal = 405;

        // Subtotal
        addTotalRow(panelPrincipal, "Subtotal", df.format(subtotal), yTotal, Color.WHITE, false);
        yTotal += 28;

        // Descuento
        if (aplicaDescuento) {
            addTotalRow(panelPrincipal, "Descuento (5%)", "-" + df.format(montoDescuento), yTotal, COLOR_VERDE_TEXTO, false);
            yTotal += 28;
        }

        // IVA
        addTotalRow(panelPrincipal, "IVA (16%)", df.format(iva), yTotal, Color.GRAY, false);
        yTotal += 35;

        // Total Autorizado
        addTotalRow(panelPrincipal, "Total Autorizado", df.format(totalAutorizado), yTotal, Color.WHITE, true);

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
        btnRegresar.setBounds(30, 635, 140, 38);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        btnGenerarOrden = new JButton("Generar Orden") {
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
        btnGenerarOrden.setBounds(715, 635, 190, 38);
        btnGenerarOrden.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGenerarOrden.setForeground(Color.WHITE);
        btnGenerarOrden.setContentAreaFilled(false);
        btnGenerarOrden.setBorderPainted(false);
        btnGenerarOrden.setFocusPainted(false);
        btnGenerarOrden.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGenerarOrden.addActionListener(this::btnGenerarOrdenActionPerformed);
        panelPrincipal.add(btnGenerarOrden);

        setContentPane(panelPrincipal);
    }

    private void addLabeledValue(JPanel panel, String label, String value, int x, int y, int labelWidth, int valueWidth) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        lbl.setForeground(COLOR_AMARILLO_ETIQUETA);
        lbl.setBounds(x, y, labelWidth, 20);
        panel.add(lbl);

        JLabel val = new JLabel(value);
        val.setFont(new Font("SansSerif", Font.PLAIN, 13));
        val.setForeground(Color.WHITE);
        val.setBounds(x + labelWidth + 5, y, valueWidth, 20);
        panel.add(val);
    }

    private void addBulletPoint(JPanel panel, String text, int y) {
        JLabel lblDot = new JLabel("•");
        lblDot.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblDot.setForeground(Color.WHITE);
        lblDot.setBounds(40, y, 15, 20);
        panel.add(lblDot);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblText.setForeground(Color.WHITE);
        lblText.setBounds(60, y, 320, 20);
        panel.add(lblText);
    }

    private void addTotalRow(JPanel panel, String tag, String value, int y, Color color, boolean isBold) {
        JLabel lblTag = new JLabel(tag);
        lblTag.setFont(new Font("SansSerif", isBold ? Font.BOLD : Font.PLAIN, isBold ? 15 : 13));
        lblTag.setForeground(color);
        lblTag.setBounds(440, y, 180, 22);
        panel.add(lblTag);

        JLabel lblVal = new JLabel(value);
        lblVal.setFont(new Font("SansSerif", isBold ? Font.BOLD : Font.PLAIN, isBold ? 16 : 13));
        lblVal.setForeground(color);
        lblVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVal.setBounds(680, y, 225, 22);
        panel.add(lblVal);
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irADecisionCliente();
    }

    private void btnGenerarOrdenActionPerformed(ActionEvent evt) {
        control.generarOrden();
    }
}
