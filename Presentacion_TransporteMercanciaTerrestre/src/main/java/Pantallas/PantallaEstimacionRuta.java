/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Control.ControlMercanciaTerrestre;
import javax.swing.JFrame;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author Jaime
 */
public class PantallaEstimacionRuta extends JFrame {
    
    private ControlMercanciaTerrestre control;
    private SolicitudTransporteDTO solicitud;

    private JCheckBox chkRutaA;
    private JCheckBox chkRutaB;

    private JButton btnRegresar;
    private JButton btnConfirmar;

    private String ciudadOrigen = "Cd. Obregón";
    private String ciudadDestino = "Hermosillo";

    private static final Color COLOR_FONDO = new Color(54, 54, 54);
    private static final Color COLOR_PANEL_INTERNO = new Color(42, 42, 42);
    private static final Color COLOR_MAPA_FONDO = new Color(30, 30, 30);
    private static final Color COLOR_NARANJA = new Color(255, 130, 28);
    private static final Color COLOR_BADGE_FOLIO = new Color(90, 90, 90);
    private static final Color COLOR_RED_NODE = new Color(230, 80, 80);
    private static final Color COLOR_GREEN_NODE = new Color(110, 210, 70);

    public PantallaEstimacionRuta(ControlMercanciaTerrestre control, SolicitudTransporteDTO solicitud, String rutaSeleccionada) {
        this.control = control;
        this.solicitud = solicitud;
        extraerCiudades();
        initComponents();
        seleccionarRuta("B".equalsIgnoreCase(rutaSeleccionada) ? "B" : "A");
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("EstimacionRuta");
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

        JLabel lblTituloGeneral = new JLabel("Selección De Ruta");
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
        // RUTA A (IZQUIERDA)
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloRutaA = new JLabel("Ruta De Referencia (A)");
        lblTituloRutaA.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTituloRutaA.setForeground(Color.WHITE);
        lblTituloRutaA.setBounds(50, 125, 175, 25);
        panelPrincipal.add(lblTituloRutaA);

        chkRutaA = new JCheckBox("", true);
        chkRutaA.setOpaque(false);
        chkRutaA.setBounds(225, 126, 25, 25);
        chkRutaA.addActionListener(e -> seleccionarRuta("A"));
        panelPrincipal.add(chkRutaA);

        JPanel mapaA = createMapCanvasA();
        mapaA.setBounds(40, 155, 410, 280);
        panelPrincipal.add(mapaA);

        JPanel infoA = createInfoPanel("320 km", "4.5 Horas", "Federal / Autopista");
        infoA.setBounds(40, 445, 410, 70);
        panelPrincipal.add(infoA);

        // ═════════════════════════════════════════════════════════════════
        // RUTA B (DERECHA)
        // ═════════════════════════════════════════════════════════════════
        JLabel lblTituloRutaB = new JLabel("Ruta De Alternativa (B)");
        lblTituloRutaB.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTituloRutaB.setForeground(Color.WHITE);
        lblTituloRutaB.setBounds(500, 125, 175, 25);
        panelPrincipal.add(lblTituloRutaB);

        chkRutaB = new JCheckBox("", false);
        chkRutaB.setOpaque(false);
        chkRutaB.setBounds(675, 126, 25, 25);
        chkRutaB.addActionListener(e -> seleccionarRuta("B"));
        panelPrincipal.add(chkRutaB);

        JPanel mapaB = createMapCanvasB();
        mapaB.setBounds(490, 155, 410, 280);
        panelPrincipal.add(mapaB);

        JPanel infoB = createInfoPanel("400 km", "6 Horas", "Federal / Autopista");
        infoB.setBounds(490, 445, 410, 70);
        panelPrincipal.add(infoB);

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
        btnRegresar.setBounds(45, 630, 110, 34);
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        panelPrincipal.add(btnRegresar);

        btnConfirmar = new JButton("Confirmar y Comenzar Elegir Unidad") {
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
        btnConfirmar.setBounds(610, 630, 285, 34);
        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setContentAreaFilled(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);
        panelPrincipal.add(btnConfirmar);

        setContentPane(panelPrincipal);
    }

    private void seleccionarRuta(String opcion) {
        if ("A".equals(opcion)) {
            chkRutaA.setSelected(true);
            chkRutaB.setSelected(false);
            control.seleccionarRuta("A");
        } else {
            chkRutaA.setSelected(false);
            chkRutaB.setSelected(true);
            control.seleccionarRuta("B");
        }
    }

    private JPanel createMapCanvasA() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo panel mapa
                g2.setColor(COLOR_MAPA_FONDO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));

                // Rejilla de mapa
                g2.setColor(new Color(50, 50, 50));
                for (int x = 50; x < getWidth(); x += 60) {
                    g2.drawLine(x, 0, x, getHeight());
                }
                for (int y = 50; y < getHeight(); y += 60) {
                    g2.drawLine(0, y, getWidth(), y);
                }

                // Círculos concéntricos de radar
                g2.setColor(new Color(60, 60, 60));
                g2.drawOval(40, 30, 240, 200);
                g2.drawOval(80, 60, 160, 140);

                // Nodos de Ruta A
                int ax = 150, ay = 200; // Punto A
                int ex = 240, ey = 160; // Empalme
                int bx = 310, by = 80;  // Punto B

                // Línea punteada
                Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0);
                g2.setStroke(dashed);
                g2.setColor(COLOR_NARANJA);
                g2.drawLine(ax, ay, ex, ey);
                g2.drawLine(ex, ey, bx, by);

                // Dibujar Puntos
                drawMapNode(g2, ax, ay, COLOR_RED_NODE);
                drawNodeLabel(g2, "A — " + ciudadOrigen, ax - 45, ay + 12);

                drawMapNode(g2, ex, ey, COLOR_RED_NODE);
                drawNodeLabel(g2, "Empalme", ex - 28, ey + 12);

                drawMapNode(g2, bx, by, COLOR_GREEN_NODE);
                drawNodeLabel(g2, "B — " + ciudadDestino, bx - 45, by - 26);

                g2.dispose();
            }
        };
    }

    private JPanel createMapCanvasB() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo panel mapa
                g2.setColor(COLOR_MAPA_FONDO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));

                // Rejilla de mapa
                g2.setColor(new Color(50, 50, 50));
                for (int x = 50; x < getWidth(); x += 60) {
                    g2.drawLine(x, 0, x, getHeight());
                }
                for (int y = 50; y < getHeight(); y += 60) {
                    g2.drawLine(0, y, getWidth(), y);
                }

                // Círculos concéntricos de radar
                g2.setColor(new Color(60, 60, 60));
                g2.drawOval(90, 30, 240, 200);
                g2.drawOval(130, 60, 160, 140);

                // Nodos de Ruta B
                int ax = 160, ay = 200; // Punto A
                int vx = 130, vy = 145; // Vicam
                int sx = 270, sy = 155; // San Carlos
                int bx = 310, by = 80;  // Punto B

                // Línea punteada
                Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0);
                g2.setStroke(dashed);
                g2.setColor(COLOR_NARANJA);
                g2.drawLine(ax, ay, vx, vy);
                g2.drawLine(vx, vy, sx, sy);
                g2.drawLine(sx, sy, bx, by);

                // Dibujar Puntos
                drawMapNode(g2, ax, ay, COLOR_RED_NODE);
                drawNodeLabel(g2, "A — " + ciudadOrigen, ax - 45, ay + 12);

                drawMapNode(g2, vx, vy, COLOR_RED_NODE);
                drawNodeLabel(g2, "Vicam", vx - 22, vy + 12);

                drawMapNode(g2, sx, sy, COLOR_RED_NODE);
                drawNodeLabel(g2, "San Carlos", sx - 30, sy + 12);

                drawMapNode(g2, bx, by, COLOR_GREEN_NODE);
                drawNodeLabel(g2, "B — " + ciudadDestino, bx - 45, by - 26);

                g2.dispose();
            }
        };
    }

    private void drawMapNode(Graphics2D g2, int x, int y, Color color) {
        g2.setColor(color);
        g2.fillOval(x - 7, y - 7, 14, 14);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x - 7, y - 7, 14, 14);
    }

    private void drawNodeLabel(Graphics2D g2, String text, int x, int y) {
        Font font = new Font("SansSerif", Font.BOLD, 10);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int width = fm.stringWidth(text) + 12;
        int height = 18;

        g2.setColor(new Color(40, 40, 40, 220));
        g2.fill(new RoundRectangle2D.Float(x, y, width, height, 8, 8));
        g2.setColor(Color.WHITE);
        g2.drawString(text, x + 6, y + 13);
    }

    private JPanel createInfoPanel(String distancia, String tiempo, String tipoCarretera) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_PANEL_INTERNO);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
            }
        };
        panel.setOpaque(false);

        // Distancia Estimada
        JLabel lblDistTitle = new JLabel("Distancia Estimada");
        lblDistTitle.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblDistTitle.setForeground(Color.GRAY);
        lblDistTitle.setBounds(15, 10, 110, 15);
        panel.add(lblDistTitle);

        JLabel lblDistVal = new JLabel(distancia);
        lblDistVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblDistVal.setForeground(Color.WHITE);
        lblDistVal.setBounds(15, 30, 110, 22);
        panel.add(lblDistVal);

        JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
        sep1.setBounds(130, 12, 2, 45);
        sep1.setForeground(Color.DARK_GRAY);
        panel.add(sep1);

        // Tiempo Estimado
        JLabel lblTiempoTitle = new JLabel("Tiempo Estimado");
        lblTiempoTitle.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblTiempoTitle.setForeground(Color.GRAY);
        lblTiempoTitle.setBounds(145, 10, 110, 15);
        panel.add(lblTiempoTitle);

        JLabel lblTiempoVal = new JLabel(tiempo);
        lblTiempoVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTiempoVal.setForeground(Color.WHITE);
        lblTiempoVal.setBounds(145, 30, 110, 22);
        panel.add(lblTiempoVal);

        JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
        sep2.setBounds(265, 12, 2, 45);
        sep2.setForeground(Color.DARK_GRAY);
        panel.add(sep2);

        // Tipo de Carretera
        JLabel lblTipoTitle = new JLabel("Tipo de Carretera");
        lblTipoTitle.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblTipoTitle.setForeground(Color.GRAY);
        lblTipoTitle.setBounds(280, 10, 120, 15);
        panel.add(lblTipoTitle);

        JLabel lblTipoVal = new JLabel(tipoCarretera);
        lblTipoVal.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTipoVal.setForeground(Color.WHITE);
        lblTipoVal.setBounds(280, 30, 120, 22);
        panel.add(lblTipoVal);

        return panel;
    }
    
    private void extraerCiudades() {
            PuntoTrasladoDTO orig = solicitud.getOrigen();
                ciudadOrigen = orig.getCiudad().trim();
            
            PuntoTrasladoDTO dest = solicitud.getDestino();
                ciudadDestino = dest.getCiudad().trim();
    }

    private void btnRegresarActionPerformed(ActionEvent evt) {
        control.irAEvaluacion();
    }

    private void btnConfirmarActionPerformed(ActionEvent evt) {
        String ruta = chkRutaA.isSelected() ? "A" : (chkRutaB.isSelected() ? "B" : null);
        control.confirmarRuta(ruta);
    }
}
