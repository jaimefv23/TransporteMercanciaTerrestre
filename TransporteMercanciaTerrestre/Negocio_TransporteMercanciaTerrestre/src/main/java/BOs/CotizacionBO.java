/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOS.CotizacionDTO;
import DTOS.DetalleCotizacionDTO;
import DTOS.MercanciaDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jaime
 */
public class CotizacionBO implements ICotizacionBO {

    public static final String ESTADO_VIGENTE = "VIGENTE";

    public static final String PRINCIPAL = "PRINCIPAL";
    public static final String ADICIONAL = "ADICIONAL";
    public static final String OPCIONAL = "OPCIONAL";

    // Tarifas de referencia
    private static final double TASA_IVA = 0.16;
    private static final double PRECIO_DIESEL_LITRO = 95.0;
    private static final double CONSUMO_LITROS_POR_KM = 0.32;
    private static final double COSTO_HORA_TRAYECTO = 1333.0;
    private static final double KM_POR_CASETA = 80.0;
    private static final double COSTO_CASETA = 400.0;
    private static final double COSTO_POR_KG = 125.0;
    private static final double VELOCIDAD_PROMEDIO_KMH = 60.0;

    private static final Pattern NUMERO = Pattern.compile("(\\d+(?:[.,]\\d+)?)");

    CotizacionBO() {
    }

    @Override
    public CotizacionDTO generar(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        if (solicitud.getMercancias() == null || solicitud.getMercancias().isEmpty()) {
            throw new IllegalArgumentException("La solicitud no tiene mercancías registradas");
        }
        if (propuesta == null || propuesta.getDistanciaEstimada() <= 0) {
            throw new IllegalArgumentException("La propuesta operativa debe tener una distancia estimada");
        }

        CotizacionDTO cot = new CotizacionDTO();
        cot.setIdCotizacion("COT-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        cot.setFechaEmision(LocalDate.now());

        double distancia = propuesta.getDistanciaEstimada();
        double horas = obtenerHoras(propuesta.getTiempoEstimado(), distancia);
        double pesoTotal = 0;
        for (MercanciaDTO m : solicitud.getMercancias()) {
            pesoTotal += m.getPeso() * m.getCantidad();
        }

        int n = 1;
        // conceptos principales
        double litros = distancia * CONSUMO_LITROS_POR_KM;
        cot.getDetalles().add(detalle(n++, "Gasolina Para La Unidad (" + Math.round(litros) + "L Diesel)",
                PRINCIPAL, PRECIO_DIESEL_LITRO, litros));
        cot.getDetalles().add(detalle(n++, "Tiempo de Trayecto (" + formatear(horas) + " hrs)",
                PRINCIPAL, COSTO_HORA_TRAYECTO, horas));
        int casetas = Math.max(1, (int) (distancia / KM_POR_CASETA));
        cot.getDetalles().add(detalle(n++, "Casetas de cobro + Aduana (" + casetas + "×" + (int) COSTO_CASETA + ")",
                PRINCIPAL, COSTO_CASETA, casetas));
        cot.getDetalles().add(detalle(n++, "Carga de mercancía (" + (int) pesoTotal + " kg)",
                PRINCIPAL, COSTO_POR_KG, pesoTotal));

        // servicios adicionales
        cot.getDetalles().add(detalle(n++, "Renta de Remolque", ADICIONAL, 1500.0, 1));
        cot.getDetalles().add(detalle(n++, "Mano de Obra", ADICIONAL, 900.0, 1));
        cot.getDetalles().add(detalle(n++, "Equipo Especializado", ADICIONAL, 1200.0, 1));

        // servicios opcionales (seguro de carga solicitado en la propuesta)
        if (propuesta.isRequiereSeguroCarga()) {
            cot.getDetalles().add(detalle(n++, "Equipo de aseguramiento", OPCIONAL, 2000.0, 1));
            cot.getDetalles().add(detalle(n++, "Seguro de reposición contra accidentes", OPCIONAL, 1000.0, 1));
        }

        // totales
        double subtotal = 0;
        for (DetalleCotizacionDTO d : cot.getDetalles()) {
            subtotal += d.getImporte();
        }
        subtotal = redondear(subtotal);
        double descuento = 0.0;
        double iva = redondear((subtotal - descuento) * TASA_IVA);

        cot.setSubtotal(subtotal);
        cot.setDescuento(descuento);
        cot.setIva(iva);
        cot.setTotal(redondear(subtotal - descuento + iva));
        cot.setEstado(ESTADO_VIGENTE);
        return cot;
    }

    private DetalleCotizacionDTO detalle(int consecutivo, String descripcion, String tipo, double precioUnitario, double cantidad) {
        return new DetalleCotizacionDTO(String.format("DET-%02d", consecutivo), descripcion, tipo, precioUnitario, redondear(precioUnitario * cantidad));
    }

    // toma las horas del texto de la propuesta (ej. "4.5 Hrs") si no hay numero, las deduce de la distancia
    private double obtenerHoras(String tiempoEstimado, double distancia) {
        if (tiempoEstimado != null) {
            Matcher m = NUMERO.matcher(tiempoEstimado);
            if (m.find()) {
                return Double.parseDouble(m.group(1).replace(',', '.'));
            }
        }
        return distancia / VELOCIDAD_PROMEDIO_KMH;
    }

    private String formatear(double horas) {
        return horas == Math.rint(horas) ? String.valueOf((int) horas) : String.valueOf(horas);
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
