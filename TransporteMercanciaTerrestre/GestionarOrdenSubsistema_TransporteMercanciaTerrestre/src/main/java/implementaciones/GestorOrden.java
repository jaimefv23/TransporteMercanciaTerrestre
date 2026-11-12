/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import BOs.IDecisionClienteBO;
import BOs.IOrdenTransporteBO;
import DTOS.CotizacionDTO;
import DTOS.DecisionClienteDTO;
import DTOS.OrdenTransporteDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;
import BOs.FabricaBO;
import java.time.LocalDate;

/**
 *
 * @author tolan
 */
public class GestorOrden {

    private IDecisionClienteBO decisionBO;
    private IOrdenTransporteBO ordenBO;

    public GestorOrden() {
        this.decisionBO = FabricaBO.crearDecisionClienteBO();
        this.ordenBO = FabricaBO.crearOrdenTransporteBO();
    }

    public DecisionClienteDTO registrarDecision(String tipoDecision, String observaciones) {
        DecisionClienteDTO decision = new DecisionClienteDTO();
        decision.setTipoDecision(tipoDecision);
        decision.setObservaciones(observaciones);
        return decisionBO.guardar(decision);
    }

    public OrdenTransporteDTO generarOrden(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        if (solicitud == null || propuesta == null || cotizacion == null) {
            throw new IllegalArgumentException("Faltan datos para generar la orden");
        }
 
        String numero = solicitud.getFolio().replace("SOL-", "");
        String condiciones = "Ruta " + propuesta.getRutaSeleccionada() + " | " + propuesta.getTipoUnidadSugerida().getNombre();
        if (propuesta.isRequiereSeguroCarga()) {
            condiciones = condiciones + " | Con seguro de carga";
        }
 
        OrdenTransporteDTO orden = new OrdenTransporteDTO();
        orden.setIdOrdenTransporte("OT-" + numero);
        orden.setFolio(ordenBO.generarFolio());
        orden.setFechaEmision(LocalDate.now());
        orden.setEstado("GENERADA (Lista p/ Flota)");
        orden.setImporteAutorizado(cotizacion.getTotal());
        orden.setCondicionesAcordadas(condiciones);
        return ordenBO.guardar(orden);
    }
}
