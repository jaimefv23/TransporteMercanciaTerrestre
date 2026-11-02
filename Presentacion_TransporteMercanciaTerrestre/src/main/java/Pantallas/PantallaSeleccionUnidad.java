/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import javax.swing.JFrame;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jaime
 */
public class PantallaSeleccionUnidad extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private SolicitudTransporteDTO solicitud;
    private List<TipoUnidadDTO> unidades;
    private boolean requiereSeguro;

    private String unidadSeleccionada = "Camión Refrigerado";
    private JCheckBox chkSeguroCarga;

    private List<UnidadCardPanel> tarjetasUnidad = new ArrayList<>();

    private JButton btnRegresar;
    private JButton btnConfirmarCotizacion;

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_CARD_FONDO = new Color(48, 48, 48);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_ROJO = new Color(230, 30, 30);
    private static final Color COLOR_BADGE_FOLIO = new Color(90, 90, 90);
    private static final Color COLOR_BORDE_GRIS = new Color(75, 75, 75);

    public PantallaSeleccionUnidad(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud,
            List<TipoUnidadDTO> unidades, TipoUnidadDTO unidadActual, boolean requiereSeguro) {
        this.control = control;
        this.solicitud = solicitud;
        this.unidades = unidades;
        this.requiereSeguro = requiereSeguro;
        if (unidadActual != null) {
            this.unidadSeleccionada = unidadActual.getNombre();
        } else if (!unidades.isEmpty()) {
            this.unidadSeleccionada = unidades.get(0).getNombre();
        }
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Seleccion Unidad");
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

        JLabel lblTituloGeneral = new JLabel("Selección De Unidad");
        lblTituloGeneral.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 22));
        lblTituloGeneral.setForeground(Color.WHITE);
        panelBanner.add(lblTituloGeneral);
        panelPrincipal.add(panelBanner);

        // Badge Folio
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
        JLabel lblFolio = new JLabel("Folio Solicitud: " + folioStr);
        lblFolio.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblFolio.setForeground(Color.WHITE);
        panelFolio.add(lblFolio);
        panelPrincipal.add(panelFolio);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN IZQUIERDA: SERVICIOS REQUERIDOS
        // ═════════════════════════════════════════════════════════════════
        JPanel panelServicios = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
            }
        };
        panelServicios.setOpaque(false);
        panelServicios.setBounds(25, 125, 415, 480);
        panelServicios.setLayout(null);

        JLabel lblTituloServicios = new JLabel("Servicios Requeridos");
        lblTituloServicios.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloServicios.setForeground(Color.WHITE);
        lblTituloServicios.setBounds(20, 20, 300, 25);
        panelServicios.add(lblTituloServicios);

        JSeparator sepServicios = new JSeparator();
        sepServicios.setBounds(20, 52, 375, 2);
        sepServicios.setForeground(COLOR_NARANJA);
        panelServicios.add(sepServicios);

        // Service 1: Maniobra de Carga (Siempre marcado de rojo y bloqueado)
        JPanel iconRedBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_ROJO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 6, 6));
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawLine(4, 9, 8, 13);
                g2.drawLine(8, 13, 14, 4);
                g2.dispose();
            }
        };
        iconRedBox.setOpaque(false);
        iconRedBox.setBounds(20, 75, 18, 18);
        panelServicios.add(iconRedBox);

        JLabel lblManiobra = new JLabel("Maniobra de Carga");
        lblManiobra.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblManiobra.setForeground(Color.WHITE);
        lblManiobra.setBounds(48, 72, 250, 22);
        panelServicios.add(lblManiobra);

        JLabel lblManiobraSub = new JLabel("Equipo y personal certificado");
        lblManiobraSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblManiobraSub.setForeground(Color.GRAY);
        lblManiobraSub.setBounds(48, 94, 250, 18);
        panelServicios.add(lblManiobraSub);

        // Subtítulo Servicio Opcional
        JLabel lblOpcional = new JLabel("Servicio Opcional:");
        lblOpcional.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblOpcional.setForeground(Color.LIGHT_GRAY);
        lblOpcional.setBounds(20, 140, 200, 22);
        panelServicios.add(lblOpcional);

        // Checkbox Seguro de Carga
        chkSeguroCarga = new JCheckBox("Seguro de Carga");
        chkSeguroCarga.setOpaque(false);
        chkSeguroCarga.setFont(new Font("SansSerif", Font.BOLD, 14));
        chkSeguroCarga.setForeground(Color.WHITE);
        chkSeguroCarga.setSelected(requiereSeguro);
        chkSeguroCarga.setBounds(16, 172, 200, 24);
        panelServicios.add(chkSeguroCarga);

        JLabel lblSeguroSub = new JLabel("Protección integral contra siniestros");
        lblSeguroSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSeguroSub.setForeground(Color.GRAY);
        lblSeguroSub.setBounds(48, 198, 280, 18);
        panelServicios.add(lblSeguroSub);

        panelPrincipal.add(panelServicios);

        // ═════════════════════════════════════════════════════════════════
        // SECCIÓN DERECHA: TIPO DE UNIDADES
        // ═════════════════════════════════════════════════════════════════
        JPanel panelUnidadesContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
            }
        };
        panelUnidadesContainer.setOpaque(false);
        panelUnidadesContainer.setBounds(455, 125, 455, 480);
        panelUnidadesContainer.setLayout(null);

        JLabel lblTituloUnidades = new JLabel("Tipo de Unidades");
        lblTituloUnidades.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTituloUnidades.setForeground(Color.WHITE);
        lblTituloUnidades.setBounds(20, 20, 250, 25);
        panelUnidadesContainer.add(lblTituloUnidades);

        JSeparator sepUnidades = new JSeparator();
        sepUnidades.setBounds(20, 52, 415, 2);
        sepUnidades.setForeground(COLOR_NARANJA);
        panelUnidadesContainer.add(sepUnidades);

        // Grid 3x2 de Unidades
        int cardW = 125;
        int cardH = 185;
        int gapX = 15;
        int gapY = 15;
        int startX = 20;
        int startY = 70;

        // El catálogo de unidades lo entrega el Coordinador (viene del Almacén Temporal)
        String[][] datosUnidades = new String[unidades.size()][2];
        for (int k = 0; k < unidades.size(); k++) {
            TipoUnidadDTO t = unidades.get(k);
            double ton = t.getCapacidadCarga() / 1000.0;
            String tonTxt = ton == Math.floor(ton) ? String.valueOf((int) ton) : String.valueOf(ton);
            datosUnidades[k][0] = t.getNombre();
            datosUnidades[k][1] = "Capacidad: " + tonTxt + " Ton";
        }

        for (int i = 0; i < datosUnidades.length; i++) {
            int col = i % 3;
            int row = i / 3;

            int x = startX + col * (cardW + gapX);
            int y = startY + row * (cardH + gapY);

            String nombre = datosUnidades[i][0];
            String capacidad = datosUnidades[i][1];

            UnidadCardPanel card = new UnidadCardPanel(nombre, capacidad);
            card.setBounds(x, y, cardW, cardH);
            tarjetasUnidad.add(card);
            panelUnidadesContainer.add(card);

            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    unidadSeleccionada = card.getNombreUnidad();
                    actualizarSeleccionTarjetas();
                }
            });
        }

        actualizarSeleccionTarjetas();
        panelPrincipal.add(panelUnidadesContainer);

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

        btnConfirmarCotizacion = new JButton("Confirmar Y Comenzar Cotización") {
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
        btnConfirmarCotizacion.setBounds(585, 635, 325, 36);
        btnConfirmarCotizacion.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnConfirmarCotizacion.setForeground(Color.WHITE);
        btnConfirmarCotizacion.setContentAreaFilled(false);
        btnConfirmarCotizacion.setBorderPainted(false);
        btnConfirmarCotizacion.setFocusPainted(false);
        btnConfirmarCotizacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmarCotizacion.addActionListener(this::btnConfirmarCotizacionActionPerformed);
        panelPrincipal.add(btnConfirmarCotizacion);

        setContentPane(panelPrincipal);
    }

    // ═════════════════════════════════════════════════════════════════
    // PANEL INTERACTIVO DE UNIDAD
    // ═════════════════════════════════════════════════════════════════
    private static class UnidadCardPanel extends JPanel {

        private final String nombreUnidad;
        private final String capacidadText;
        private boolean isSelected = false;

        public UnidadCardPanel(String nombreUnidad, String capacidadText) {
            this.nombreUnidad = nombreUnidad;
            this.capacidadText = capacidadText;
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public String getNombreUnidad() {
            return nombreUnidad;
        }

        public void setSelected(boolean selected) {
            this.isSelected = selected;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // Fondo de la tarjeta
            g2.setColor(COLOR_CARD_FONDO);
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, 14, 14));

            // Borde Naranja (si seleccionada) o Gris
            if (isSelected) {
                g2.setColor(COLOR_NARANJA);
                g2.setStroke(new BasicStroke(2.5f));
            } else {
                g2.setColor(COLOR_BORDE_GRIS);
                g2.setStroke(new BasicStroke(1.2f));
            }
            g2.draw(new RoundRectangle2D.Float(1, 1, w - 2, h - 2, 14, 14));

            // Dibujar Icono del Camión
            g2.setColor(COLOR_NARANJA);
            int startX = 14;
            int startY = 18;

            // Cabina y Caja del Camión en dibujo vectorial
            g2.drawRect(startX, startY + 12, 18, 14); // Cabina
            g2.drawRect(startX + 18, startY + 4, 28, 22); // Caja
            g2.drawOval(startX + 4, startY + 24, 7, 7); // Rueda 1
            g2.drawOval(startX + 24, startY + 24, 7, 7); // Rueda 2
            g2.drawOval(startX + 34, startY + 24, 7, 7); // Rueda 3

            // Rayo de potencia
            g2.drawLine(startX + 2, startY + 4, startX + 6, startY + 10);

            // Nombre de la Unidad
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 12));

            // Ajuste multi-palabra manual si es largo
            String[] words = nombreUnidad.split(" ");
            int textY = 78;
            if (words.length > 1) {
                StringBuilder line1 = new StringBuilder();
                StringBuilder line2 = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    if (i < 1 || (i == 1 && words.length > 2)) {
                        line1.append(words[i]).append(" ");
                    } else {
                        line2.append(words[i]).append(" ");
                    }
                }
                g2.drawString(line1.toString().trim(), 12, textY);
                g2.drawString(line2.toString().trim(), 12, textY + 16);
            } else {
                g2.drawString(nombreUnidad, 12, textY);
            }

            // Subtítulo Capacidad
            g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawString(capacidadText, 12, h - 18);

            g2.dispose();
        }
    }
    
        private void actualizarSeleccionTarjetas() {
            for (UnidadCardPanel card : tarjetasUnidad) {
                card.setSelected(card.getNombreUnidad().equals(unidadSeleccionada));
            }
        }

        private void btnRegresarActionPerformed(ActionEvent evt) {
            control.irAEstimacionRuta();
        }

        private void btnConfirmarCotizacionActionPerformed(ActionEvent evt) {
            control.confirmarUnidad(unidadSeleccionada, chkSeguroCarga.isSelected());
        }
}
