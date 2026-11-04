/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simuladores;

import DTOS.CotizacionDTO;
import DTOS.DetalleCotizacionDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */

public class SimuladorCotizacion {
    private static final double TASA_IVA_SIMULADA = 0.16;

    public CotizacionDTO simularCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta) {
        CotizacionDTO cot = new CotizacionDTO();
        cot.setIdCotizacion("COT-001");
        cot.setFechaEmision(LocalDate.now());

        // servicio adicional
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-01", "Renta de Remolque", "ADICIONAL", 1.0, 1500.0));
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-02", "Mano de Obra", "ADICIONAL", 1.0, 900.0));
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-03", "Equipo Especializado", "ADICIONAL", 1.0, 1200.0));

        // servicio principal
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-04", "Gasolina Para La Unidad (80L Diesel)", "PRINCIPAL", 1.0, 7600.0));
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-05", "Tiempo de Trayecto (4.5 hrs)", "PRINCIPAL", 1.0, 5998.50));
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-06", "Casetas de cobro + Aduana (3×400)", "PRINCIPAL", 1.0, 1200.0));

        double pesoTotal = 15.0;
        if (solicitud != null && !solicitud.getMercancias().isEmpty()) {
            pesoTotal = solicitud.getMercancias().stream().mapToDouble(m -> m.getPeso() * m.getCantidad()).sum();
        }
        cot.getDetalles().add(new DetalleCotizacionDTO("DET-07", "Carga de mercancía (" + (int) pesoTotal + " kg)", "PRINCIPAL", 1.0, 1875.0));

        // srvicio opcional (seguro de carga que viene en la propuesta)
        if (propuesta != null && propuesta.isRequiereSeguroCarga()) {
            cot.getDetalles().add(new DetalleCotizacionDTO("DET-08", "Equipo de aseguramiento", "OPCIONAL", 1.0, 2000.0));
            cot.getDetalles().add(new DetalleCotizacionDTO("DET-09", "Seguro de reposición contra accidentes", "OPCIONAL", 1.0, 1000.0));
        }

        // calculo de totales (simulado)
        double subtotal = 0;
        for (DetalleCotizacionDTO d : cot.getDetalles()) {
            subtotal += d.getImporte();
        }
        double iva = subtotal * TASA_IVA_SIMULADA;

        cot.setSubtotal(subtotal);
        cot.setDescuento(0.0);
        cot.setIva(iva);
        cot.setTotal(subtotal + iva);
        cot.setEstado("VIGENTE");
        return cot;
    }

}
