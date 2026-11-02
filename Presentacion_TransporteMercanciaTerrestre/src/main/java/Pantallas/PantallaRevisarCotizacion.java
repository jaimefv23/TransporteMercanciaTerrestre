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
public class PantallaRevisarCotizacion extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private PropuestaOperativaDTO propuesta;
    private CotizacionDTO cotizacion;
    private SolicitudTransporteDTO solicitud;

    private JButton btnRegresar;
    private JButton btnFinalizarCotizacion;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_AMARILLO_TEXTO = new Color(245, 210, 40);
    private static final Color COLOR_VERDE_TEXTO = new Color(50, 205, 50);
    private static final Color COLOR_BADGE_FOLIO = new Color(90, 90, 90);

    private final DecimalFormat df = new DecimalFormat("$#,##0.00", new DecimalFormatSymbols(Locale.US));

    public PantallaRevisarCotizacion(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        this.control = control;
        this.solicitud = solicitud;
        this.propuesta = propuesta;
        this.cotizacion = cotizacion;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Revisión cotización");
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

        // Subtítulo Badge "Cotización #FOLIO | Cliente: NOMBRE" y "PENDIENTE DE DECISIÓN"
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
        panelFolio.setBounds(25, 95, 430, 26);
        panelFolio.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));

        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        ClienteDTO cliente = (solicitud != null) ? solicitud.getCliente() : null;
        String nombreCliente = (cliente != null) ? cliente.getNombre() : "Alejandro Efraín Ibarra Armenta";

        JLabel lblFolio = new JLabel("Cotización #" + folioStr + " | Cliente: " + nombreCliente);
        lblFolio.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblFolio.setForeground(Color.WHITE);
        panelFolio.add(lblFolio);
        panelPrincipal.add(panelFolio);

        JLabel lblEstadoDecision = new JLabel("PENDIENTE DE DECISIÓN");
        lblEstadoDecision.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblEstadoDecision.setForeground(COLOR_AMARILLO_TEXTO);
        lblEstadoDecision.setBounds(470, 97, 250, 22);
        panelPrincipal.add(lblEstadoDecision);

        // ═════════════════════════════════════════════════════════════════
        // COLUMNA IZQUIERDA: DETALLES DEL SERVICIO EVALUADO
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloDetalles = new JLabel("Detalles del Servicio Evaluado");
        lblTituloDetalles.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloDetalles.setForeground(Color.WHITE);
        lblTituloDetalles.setBounds(25, 135, 350, 25);
        panelPrincipal.add(lblTituloDetalles);

        int yLeft = 175;

        // 1. Cliente
        JLabel lblSubCliente = new JLabel("Cliente");
        lblSubCliente.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSubCliente.setForeground(COLOR_AMARILLO_TEXTO);
        lblSubCliente.setBounds(25, yLeft, 200, 16);
        panelPrincipal.add(lblSubCliente);

        JLabel lblValCliente = new JLabel(nombreCliente);
        lblValCliente.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValCliente.setForeground(Color.WHITE);
        lblValCliente.setBounds(25, yLeft + 18, 380, 20);
        panelPrincipal.add(lblValCliente);
        yLeft += 48;

        // 2. Contacto y Teléfono
        JLabel lblSubContacto = new JLabel("Contacto y Teléfono");
        lblSubContacto.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSubContacto.setForeground(COLOR_AMARILLO_TEXTO);
        lblSubContacto.setBounds(25, yLeft, 200, 16);
        panelPrincipal.add(lblSubContacto);

        String tel = (cliente != null && cliente.getTelefono() != null) ? cliente.getTelefono() : "(644) 258-3713";
        String email = (cliente != null && cliente.getCorreo() != null) ? cliente.getCorreo() : "alexibarra123@gmail.com";

        JLabel lblValContacto = new JLabel("Ing. " + nombreCliente.split(" ")[0] + " (" + tel + ")");
        lblValContacto.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValContacto.setForeground(Color.WHITE);
        lblValContacto.setBounds(25, yLeft + 18, 380, 20);
        panelPrincipal.add(lblValContacto);

        JLabel lblValEmail = new JLabel(email);
        lblValEmail.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValEmail.setForeground(Color.WHITE);
        lblValEmail.setBounds(25, yLeft + 36, 380, 20);
        panelPrincipal.add(lblValEmail);
        yLeft += 66;

        // 3. Origen / Destino
        JLabel lblSubRuta = new JLabel("Origen / Destino");
        lblSubRuta.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSubRuta.setForeground(COLOR_AMARILLO_TEXTO);
        lblSubRuta.setBounds(25, yLeft, 200, 16);
        panelPrincipal.add(lblSubRuta);

        PuntoTrasladoDTO orig = (solicitud != null) ? solicitud.getOrigen() : null;
        PuntoTrasladoDTO dest = (solicitud != null) ? solicitud.getDestino() : null;
        String origStr = (orig != null && orig.getCiudad() != null) ? orig.getCiudad() : "Cd. Obregón";
        String destStr = (dest != null && dest.getCiudad() != null) ? dest.getCiudad() : "Hermosillo";

        JLabel lblValRuta = new JLabel(origStr + " → " + destStr);
        lblValRuta.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValRuta.setForeground(Color.WHITE);
        lblValRuta.setBounds(25, yLeft + 18, 420, 20);
        panelPrincipal.add(lblValRuta);
        yLeft += 48;

        // 4. Unidad Sugerida y Tiempo
        JLabel lblSubUnidad = new JLabel("Unidad Sugerida y Tiempo");
        lblSubUnidad.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSubUnidad.setForeground(COLOR_AMARILLO_TEXTO);
        lblSubUnidad.setBounds(25, yLeft, 200, 16);
        panelPrincipal.add(lblSubUnidad);

        String unidadStr = propuesta.getTipoUnidadSugerida().getNombre();
        String tiempoEstimado = propuesta.getTiempoEstimado() + " Estimado";

        JLabel lblValUnidad = new JLabel(unidadStr + " | " + tiempoEstimado);
        lblValUnidad.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValUnidad.setForeground(Color.WHITE);
        lblValUnidad.setBounds(25, yLeft + 18, 420, 20);
        panelPrincipal.add(lblValUnidad);
        yLeft += 48;

        // 5. Carga Registrada
        JLabel lblSubCarga = new JLabel("Carga Registrada");
        lblSubCarga.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSubCarga.setForeground(COLOR_AMARILLO_TEXTO);
        lblSubCarga.setBounds(25, yLeft, 200, 16);
        panelPrincipal.add(lblSubCarga);

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

        JLabel lblValCarga = new JLabel(totalCantidad + " " + descMercancia + " (" + String.format(Locale.US, "%.0f", pesoTotalKg) + " Kg)");
        lblValCarga.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblValCarga.setForeground(Color.WHITE);
        lblValCarga.setBounds(25, yLeft + 18, 420, 20);
        panelPrincipal.add(lblValCarga);

        // ═════════════════════════════════════════════════════════════════
        // COLUMNA DERECHA: DESGLOSE ECONÓMICO
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloDesglose = new JLabel("Desglose Económico");
        lblTituloDesglose.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloDesglose.setForeground(Color.WHITE);
        lblTituloDesglose.setBounds(480, 135, 300, 25);
        panelPrincipal.add(lblTituloDesglose);

        // Importes entregados por la cotización (simulada)
        double servicioPrincipal = cotizacion.getTotalPorTipo("PRINCIPAL");
        double servicioAdicional = cotizacion.getTotalPorTipo("ADICIONAL");
        double servicioOpcional = cotizacion.getTotalPorTipo("OPCIONAL");
        double subtotal = cotizacion.getSubtotal();
        boolean aplicaDescuento = cotizacion.isAplicaDescuento();
        double montoDescuento = cotizacion.getDescuento();
        double iva = cotizacion.getIva();
        double totalCotizado = cotizacion.getTotal();

        int yRight = 175;

        // Row 1: Servicio Principal
        addCostRow(panelPrincipal, "Servicio Principal (Traslado Base)", df.format(servicioPrincipal), yRight, Color.WHITE);
        yRight += 32;

        // Row 2: Servicio Adicional
        addCostRow(panelPrincipal, "Servicio Adicional de Carga (Indispensable)", df.format(servicioAdicional), yRight, Color.WHITE);
        yRight += 32;

        // Row 3: Servicio Opcional
        addCostRow(panelPrincipal, "Servicio Opcional", df.format(servicioOpcional), yRight, Color.WHITE);
        yRight += 48;

        // Row 4: Subtotal
        addCostRow(panelPrincipal, "Subtotal", df.format(subtotal), yRight, Color.WHITE);
        yRight += 32;

        // Row 5: Descuento Aplicado (Sólo si aplica el 10% de carga)
        if (aplicaDescuento) {
            addCostRow(panelPrincipal, "Descuento Aplicado (5%)", "-" + df.format(montoDescuento), yRight, COLOR_VERDE_TEXTO);
            yRight += 32;
        }

        // Row 6: IVA (16%)
        addCostRow(panelPrincipal, "IVA (16%)", df.format(iva), yRight, Color.GRAY);
        yRight += 45;

        // Row 7: Total Cotizado
        JLabel lblTotalCotizadoTag = new JLabel("Total Cotizado");
        lblTotalCotizadoTag.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotalCotizadoTag.setForeground(COLOR_AMARILLO_TEXTO);
        lblTotalCotizadoTag.setBounds(480, yRight, 200, 25);
        panelPrincipal.add(lblTotalCotizadoTag);

        JLabel lblTotalCotizadoVal = new JLabel(df.format(totalCotizado));
        lblTotalCotizadoVal.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotalCotizadoVal.setForeground(Color.WHITE);
        lblTotalCotizadoVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalCotizadoVal.setBounds(680, yRight - 2, 220, 30);
        panelPrincipal.add(lblTotalCotizadoVal);

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

        btnFinalizarCotizacion = new JButton("Finalizar Cotización") {
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
        btnFinalizarCotizacion.setBounds(710, 635, 200, 36);
        btnFinalizarCotizacion.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnFinalizarCotizacion.setForeground(Color.WHITE);
        btnFinalizarCotizacion.setContentAreaFilled(false);
        btnFinalizarCotizacion.setBorderPainted(false);
        btnFinalizarCotizacion.setFocusPainted(false);
        btnFinalizarCotizacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizarCotizacion.addActionListener(this::btnFinalizarCotizacionActionPerformed);
        panelPrincipal.add(btnFinalizarCotizacion);

        setContentPane(panelPrincipal);
    }

    private void addCostRow(JPanel container, String labelText, String valueText, int y, Color color) {
        JLabel lblConcepto = new JLabel(labelText);
        lblConcepto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblConcepto.setForeground(color);
        lblConcepto.setBounds(480, y, 320, 20);
        container.add(lblConcepto);

        JLabel lblMonto = new JLabel(valueText);
        lblMonto.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMonto.setForeground(color);
        lblMonto.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMonto.setBounds(700, y, 200, 20);
        container.add(lblMonto);
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irACotizacion();
    }

    private void btnFinalizarCotizacionActionPerformed(ActionEvent evt) {
        control.irADecisionCliente();
    }
}
