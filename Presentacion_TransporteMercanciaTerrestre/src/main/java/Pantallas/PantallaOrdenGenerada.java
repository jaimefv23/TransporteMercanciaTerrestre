/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import DTOS.PropuestaOperativaDTO;
import DTOS.OrdenTransporteDTO;
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
public class PantallaOrdenGenerada extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private PropuestaOperativaDTO propuesta;
    private CotizacionDTO cotizacion;
    private OrdenTransporteDTO orden;
    private SolicitudTransporteDTO solicitud;

    private JButton btnEnviarCorreo;
    private JButton btnFinalizarProceso;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_CARD = new Color(42, 42, 42);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_VERDE_BADGE = new Color(0, 160, 20);
    private static final Color COLOR_AMARILLO_ETIQUETA = new Color(245, 210, 40);
    private static final Color COLOR_AZUL_BOTON = new Color(30, 60, 175);

    private final DecimalFormat df = new DecimalFormat("$#,##0.00", new DecimalFormatSymbols(Locale.US));

    public PantallaOrdenGenerada(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion, OrdenTransporteDTO orden) {
        this.control = control;
        this.solicitud = solicitud;
        this.propuesta = propuesta;
        this.cotizacion = cotizacion;
        this.orden = orden;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Orden Generada");
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

        // Insignia Verde "✔ ¡Orden Generada Exitosamente!"
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
        panelBadge.setBounds(30, 95, 875, 30);
        panelBadge.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 4));

        JLabel lblBadgeText = new JLabel("✔  ¡Orden Generada Exitosamente!");
        lblBadgeText.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblBadgeText.setForeground(Color.WHITE);
        panelBadge.add(lblBadgeText);
        panelPrincipal.add(panelBadge);

        // ═════════════════════════════════════════════════════════════════
        // TARJETA DE RESUMEN DE LA ORDEN ENVIADA
        // ═════════════════════════════════════════════════════════════════
        JPanel cardResumen = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_CARD);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
            }
        };
        cardResumen.setOpaque(false);
        cardResumen.setBounds(30, 140, 875, 410);

        JLabel lblTituloCard = new JLabel("RESUMEN DE LA ORDEN ENVIADA");
        lblTituloCard.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblTituloCard.setForeground(Color.WHITE);
        lblTituloCard.setBounds(20, 15, 350, 22);
        cardResumen.add(lblTituloCard);

        JSeparator sepCard = new JSeparator();
        sepCard.setBounds(20, 42, 835, 2);
        sepCard.setForeground(new Color(80, 80, 80));
        cardResumen.add(sepCard);

        // DATOS DINÁMICOS
        String folioStr = (solicitud != null && solicitud.getFolio() != null) ? solicitud.getFolio() : "SOL-205";
        ClienteDTO cliente = (solicitud != null) ? solicitud.getCliente() : null;
        String nombreCliente = (cliente != null) ? cliente.getNombre() : "Alejandro Efraín Ibarra Armenta";
        String fechaHoy = orden.getFechaEmision().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        PuntoTrasladoDTO orig = (solicitud != null) ? solicitud.getOrigen() : null;
        PuntoTrasladoDTO dest = (solicitud != null) ? solicitud.getDestino() : null;
        String origStr = (orig != null && orig.getCiudad() != null) ? orig.getCiudad() : "Cd. Obregón";
        String destStr = (dest != null && dest.getCiudad() != null) ? dest.getCiudad() : "Hermosillo";

        String tiempoEstimado = propuesta.getTiempoEstimado();
        String rutaStr = "Ruta " + propuesta.getRutaSeleccionada();
        String unidadStr = propuesta.getTipoUnidadSugerida().getNombre();

        double pesoTotalKg = 0;
        int totalCantidad = 0;
        String descMercancia = "Pasteles";
        String condEspeciales = "Cuidado al Manejar";

        if (solicitud != null && solicitud.getMercancias() != null && !solicitud.getMercancias().isEmpty()) {
            MercanciaDTO m0 = solicitud.getMercancias().get(0);
            descMercancia = m0.getNombre();
            if (m0.getCondicionesEsp() != null && !m0.getCondicionesEsp().isEmpty()) {
                condEspeciales = m0.getCondicionesEsp();
            }
            for (MercanciaDTO m : solicitud.getMercancias()) {
                pesoTotalKg += (m.getCantidad() * m.getPeso());
                totalCantidad += m.getCantidad();
            }
        } else {
            totalCantidad = 20;
            pesoTotalKg = 30;
        }

        // Columna Izquierda
        int yL = 55;
        addCardField(cardResumen, "Folio de Cotización:", "#" + folioStr, 20, yL); yL += 45;
        addCardField(cardResumen, "Cliente:", nombreCliente, 20, yL); yL += 45;
        addCardField(cardResumen, "Origen:", origStr, 20, yL); yL += 45;
        addCardField(cardResumen, "Mercancía:", totalCantidad + " " + descMercancia, 20, yL); yL += 45;
        addCardField(cardResumen, "Ruta Referencia:", rutaStr, 20, yL); yL += 45;

        // Lista de Servicios en Columna Izquierda
        JLabel lblServiciosTag = new JLabel("Servicios");
        lblServiciosTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblServiciosTag.setForeground(COLOR_AMARILLO_ETIQUETA);
        lblServiciosTag.setBounds(20, yL, 200, 18);
        cardResumen.add(lblServiciosTag);
        yL += 20;

        addCardBullet(cardResumen, "Traslado Base", 30, yL); yL += 20;
        addCardBullet(cardResumen, "Maniobra Especial de Carga", 30, yL); yL += 20;
        if (propuesta.isRequiereSeguroCarga()) {
            addCardBullet(cardResumen, "Seguro Adicional de Carga", 30, yL);
        }

        // Columna Derecha
        int yR = 55;
        addCardField(cardResumen, "Fecha de Emisión:", fechaHoy, 440, yR); yR += 45;
        addCardField(cardResumen, "Estado de Operación:", orden.getEstado(), 440, yR); yR += 45;
        addCardField(cardResumen, "Destino:", destStr, 440, yR); yR += 45;
        addCardField(cardResumen, "Tiempo Estimado:", tiempoEstimado, 440, yR); yR += 45;
        addCardField(cardResumen, "Unidad Requerida:", unidadStr, 440, yR); yR += 45;
        addCardField(cardResumen, "Condiciones:", condEspeciales, 440, yR);

        panelPrincipal.add(cardResumen);

        // ═════════════════════════════════════════════════════════════════
        // CÁLCULO Y TOTAL AUTORIZADO
        // ═════════════════════════════════════════════════════════════════
        // Importe autorizado entregado por la cotización (simulada)
        double totalAutorizado = orden.getImporteAutorizado();

        JLabel lblTotalTag = new JLabel("Total Autorizado (Con IVA):");
        lblTotalTag.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTotalTag.setForeground(COLOR_AMARILLO_ETIQUETA);
        lblTotalTag.setBounds(30, 565, 320, 30);
        panelPrincipal.add(lblTotalTag);

        JLabel lblTotalVal = new JLabel(df.format(totalAutorizado));
        lblTotalVal.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTotalVal.setForeground(Color.WHITE);
        lblTotalVal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalVal.setBounds(650, 565, 255, 30);
        panelPrincipal.add(lblTotalVal);

        // ═════════════════════════════════════════════════════════════════
        // BOTONES INFERIORES
        // ═════════════════════════════════════════════════════════════════
        // Único botón de comunicación: Enviar Por Correo
        btnEnviarCorreo = createCustomButton("Enviar Por Correo", COLOR_AZUL_BOTON);
        btnEnviarCorreo.setBounds(30, 640, 200, 38);
        btnEnviarCorreo.addActionListener(this::btnEnviarCorreoActionPerformed);
        panelPrincipal.add(btnEnviarCorreo);

        // Botón Finalizar Proceso
        btnFinalizarProceso = createCustomButton("Finalizar Proceso", COLOR_NARANJA);
        btnFinalizarProceso.setBounds(685, 640, 220, 38);
        btnFinalizarProceso.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnFinalizarProceso.addActionListener(this::btnFinalizarProcesoActionPerformed);
        panelPrincipal.add(btnFinalizarProceso);

        setContentPane(panelPrincipal);
    }

    private void addCardField(JPanel card, String label, String value, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(COLOR_AMARILLO_ETIQUETA);
        lbl.setBounds(x, y, 200, 18);
        card.add(lbl);

        JLabel val = new JLabel(value);
        val.setFont(new Font("SansSerif", Font.PLAIN, 13));
        val.setForeground(Color.WHITE);
        val.setBounds(x, y + 18, 380, 20);
        card.add(val);
    }

    private void addCardBullet(JPanel card, String text, int x, int y) {
        JLabel lblBullet = new JLabel("•  " + text);
        lblBullet.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblBullet.setForeground(Color.WHITE);
        lblBullet.setBounds(x, y, 350, 18);
        card.add(lblBullet);
    }

    private JButton createCustomButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void btnFinalizarProcesoActionPerformed(ActionEvent evt) {
        control.finalizarProceso();
    }

    private void btnEnviarCorreoActionPerformed(ActionEvent evt) {
        control.enviarOrdenPorCorreo();
    }
}
