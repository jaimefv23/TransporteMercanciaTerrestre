/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.EvaluacionTrasladoDTO;
import DTOS.MercanciaDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Jaime
 */
public class EvaluacionTrasladoBO implements IEvaluacionTrasladoBO {
    
    public static final String ESTADO_VIABLE = "VIABLE";
    public static final String ESTADO_NO_VIABLE = "NO_VIABLE";

    private final AlmacenTemporal almacen;

    EvaluacionTrasladoBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public EvaluacionTrasladoDTO evaluar(SolicitudTransporteDTO solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }

        List<String> problemas = new ArrayList<>();
        List<MercanciaDTO> mercancias = solicitud.getMercancias() == null ? new ArrayList<>() : solicitud.getMercancias();

        if (solicitud.getCliente() == null) {
            problemas.add("La solicitud no tiene cliente asignado.");
        }
        if (solicitud.getOrigen() == null || solicitud.getDestino() == null) {
            problemas.add("Falta el origen o el destino del traslado.");
        }
        if (solicitud.getFechaCarga() == null || solicitud.getFechaEntrega() == null) {
            problemas.add("Faltan las fechas de carga y/o entrega.");
        } else if (solicitud.getFechaEntrega().isBefore(solicitud.getFechaCarga())) {
            problemas.add("La fecha de entrega es anterior a la fecha de carga.");
        }
        if (mercancias.isEmpty()) {
            problemas.add("La solicitud no tiene mercancías registradas.");
        }

        // totales de la carga
        double pesoTotal = 0;
        double volumenTotal = 0;
        Set<String> condiciones = new LinkedHashSet<>();
        for (MercanciaDTO m : mercancias) {
            pesoTotal += m.getPeso() * m.getCantidad();
            volumenTotal += m.getLargo() * m.getAncho() * m.getAlto() * m.getCantidad();
            String c = m.getCondicionesEsp();
            if (c != null && !c.isBlank() && !c.trim().equalsIgnoreCase("Ninguna")) {
                condiciones.add(c.trim());
            }
        }

        if (!mercancias.isEmpty() && !existeUnidadCapaz(pesoTotal, volumenTotal)) {
            problemas.add("Ningún tipo de unidad del catálogo soporta el peso/volumen de la carga.");
        }

        EvaluacionTrasladoDTO eval = new EvaluacionTrasladoDTO();
        String folio = solicitud.getFolio();
        eval.setIdEvaluacion("EV-" + (folio != null && !folio.isBlank() ? folio : UUID.randomUUID().toString().substring(0, 6).toUpperCase()));
        eval.setFecha(LocalDate.now());
        eval.setCapacidadMinima(pesoTotal);
        eval.setCondicionEspecial(condiciones.isEmpty() ? "Ninguna" : String.join(" / ", condiciones));

        if (problemas.isEmpty()) {
            eval.setEstado(ESTADO_VIABLE);
            eval.setObservaciones(String.format(Locale.US,
                    "Evaluación preliminar favorable. Peso total: %.2f kg, volumen total: %.2f m3.",
                    pesoTotal, volumenTotal));
        } else {
            eval.setEstado(ESTADO_NO_VIABLE);
            eval.setObservaciones(String.join(" ", problemas));
        }
        return eval;
    }

    private boolean existeUnidadCapaz(double pesoTotal, double volumenTotal) {
        for (TipoUnidadDTO t : almacen.obtenerTiposUnidad()) {
            if (t.getCapacidadCarga() >= pesoTotal && t.getCapacidadVolumen() >= volumenTotal) {
                return true;
            }
        }
        return false;
    }
}
