/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import java.util.List;
import DTOS.PropuestaOperativaDTO;
import DTOS.DetalleCotizacionDTO;
import DTOS.CotizacionDTO;
import Control.ControlMercanciaTerrestre;
import javax.swing.JFrame;
import DTOS.MercanciaDTO;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
public class PantallaCotizacion extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private PropuestaOperativaDTO propuesta;
    private CotizacionDTO cotizacion;
    private SolicitudTransporteDTO solicitud;

    private JButton btnRegresar;
    private JButton btnContinuarRevision;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_AMARILLO = new Color(235, 175, 20);
    private static final Color COLOR_BADGE_FOLIO = new Color(90, 90, 90);
    private static final Color COLOR_VERDE_TOTAL = new Color(50, 205, 50);

    private final DecimalFormat df = new DecimalFormat("$#,##0.00 'MXN'", new DecimalFormatSymbols(Locale.US));

    public PantallaCotizacion(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        this.control = control;
        this.solicitud = solicitud;
        this.propuesta = propuesta;
        this.cotizacion = cotizacion;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Cotización");
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
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
            }
        };
        panelBanner.setOpaque(false);
        panelBanner.setBounds(30, 15, 880, 50);
        panelBanner.setLayout(new GridBagLayout());

        JLabel lblTituloGeneral = new JLabel("Proceso Cotización");
        lblTituloGeneral.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 22));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Badge Folio | Unidad | Ruta
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
        panelFolio.setBounds(25, 78, 885, 32);
        panelFolio.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 6));

        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        String unidadStr = (propuesta != null && propuesta.getTipoUnidadSugerida() != null) 
        ? propuesta.getTipoUnidadSugerida().getNombre() 
        : "Unidad Estándar";
        String rutaStr = "Ruta " + propuesta.getRutaSeleccionada();

        JLabel lblFolio = new JLabel("Folio Solicitud: " + folioStr + " | Unidad: " + unidadStr + " | " + rutaStr);
        lblFolio.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblFolio.setForeground(Color.WHITE);
        panelFolio.add(lblFolio);
        panelPrincipal.add(panelFolio);

        // Los importes los entrega la cotización (simulada); la vista solo los presenta.
        // 1. SERVICIO ADICIONAL
        double totalAdicional = cotizacion.getTotalPorTipo("ADICIONAL");
        JPanel panelAdicional = createServiceCard("Servicio Adicional", totalAdicional,
                filasDeServicio(cotizacion.getDetallesPorTipo("ADICIONAL"), "Sin servicios adicionales"));
        panelAdicional.setBounds(25, 120, 885, 130);
        panelPrincipal.add(panelAdicional);

        // 2. SERVICIO PRINCIPAL
        double totalPrincipal = cotizacion.getTotalPorTipo("PRINCIPAL");
        JPanel panelPrincipalService = createServiceCard("Servicio Principal", totalPrincipal,
                filasDeServicio(cotizacion.getDetallesPorTipo("PRINCIPAL"), "Sin conceptos"));
        panelPrincipalService.setBounds(25, 260, 885, 155);
        panelPrincipal.add(panelPrincipalService);

        // 3. SERVICIO OPCIONAL
        double totalOpcional = cotizacion.getTotalPorTipo("OPCIONAL");
        JPanel panelOpcionalService = createServiceCard("Servicio Opcional", totalOpcional,
                filasDeServicio(cotizacion.getDetallesPorTipo("OPCIONAL"), "Sin servicios opcionales seleccionados"));
        panelOpcionalService.setBounds(25, 425, 885, 120);
        panelPrincipal.add(panelOpcionalService);

        boolean aplicaDescuento = cotizacion.isAplicaDescuento();
        double granTotal = cotizacion.getSubtotal() - cotizacion.getDescuento();

        if (aplicaDescuento) {
            JLabel lblDescuento = new JLabel("Descuento (+10% de Carga de la unidad) - 5%");
            lblDescuento.setFont(new Font("SansSerif", Font.BOLD, 12));
            lblDescuento.setForeground(COLOR_VERDE_TOTAL);
            lblDescuento.setBounds(35, 555, 400, 20);
            panelPrincipal.add(lblDescuento);
        }

        JLabel lblResumen = new JLabel("RESUMEN TOTAL (antes de IVA): " + df.format(granTotal));
        lblResumen.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblResumen.setForeground(Color.WHITE);
        lblResumen.setBounds(35, 578, 450, 25);
        panelPrincipal.add(lblResumen);

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
        btnRegresar.setBounds(35, 635, 120, 36);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        btnContinuarRevision = new JButton("Continuar Revisión") {
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
        btnContinuarRevision.setBounds(170, 635, 185, 36);
        btnContinuarRevision.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnContinuarRevision.setForeground(Color.WHITE);
        btnContinuarRevision.setContentAreaFilled(false);
        btnContinuarRevision.setBorderPainted(false);
        btnContinuarRevision.setFocusPainted(false);
        btnContinuarRevision.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuarRevision.addActionListener(this::btnContinuarRevisionActionPerformed);
        panelPrincipal.add(btnContinuarRevision);

        setContentPane(panelPrincipal);
    }

    private JPanel createServiceCard(String tituloSeccion, double totalSeccion, String[][] filasConceptos) {
        JPanel card = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.dispose();
            }
        };
        card.setOpaque(false);

        JLabel lblTitulo = new JLabel(tituloSeccion);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(20, 15, 200, 22);
        card.add(lblTitulo);

        JLabel lblTotalSeccion = new JLabel("Total Sección: " + df.format(totalSeccion));
        lblTotalSeccion.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTotalSeccion.setForeground(COLOR_VERDE_TOTAL);
        lblTotalSeccion.setBounds(620, 15, 245, 22);
        lblTotalSeccion.setHorizontalAlignment(SwingConstants.RIGHT);
        card.add(lblTotalSeccion);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 42, 845, 2);
        sep.setForeground(new Color(80, 80, 80));
        card.add(sep);

        int yPos = 50;
        for (String[] fila : filasConceptos) {
            JLabel lblConcepto = new JLabel(fila[0]);
            lblConcepto.setFont(new Font("SansSerif", Font.PLAIN, 12));
            lblConcepto.setForeground(Color.LIGHT_GRAY);
            lblConcepto.setBounds(260, yPos, 320, 20);
            card.add(lblConcepto);

            JLabel lblMonto = new JLabel(fila[1]);
            lblMonto.setFont(new Font("SansSerif", Font.BOLD, 12));
            lblMonto.setForeground(Color.WHITE);
            lblMonto.setBounds(680, yPos, 185, 20);
            lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
            card.add(lblMonto);

            yPos += 22;
        }

        return card;
    }

    private String[][] filasDeServicio(List<DetalleCotizacionDTO> detalles, String textoVacio) {
        if (detalles.isEmpty()) {
            return new String[][]{{textoVacio, df.format(0.00)}};
        }
        String[][] filas = new String[detalles.size()][2];
        for (int i = 0; i < detalles.size(); i++) {
            filas[i][0] = detalles.get(i).getDescripcion();
            filas[i][1] = df.format(detalles.get(i).getImporte());
        }
        return filas;
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irASeleccionUnidad();
    }

    private void btnContinuarRevisionActionPerformed(ActionEvent evt) {
        control.irARevisarCotizacion();
    }
}
