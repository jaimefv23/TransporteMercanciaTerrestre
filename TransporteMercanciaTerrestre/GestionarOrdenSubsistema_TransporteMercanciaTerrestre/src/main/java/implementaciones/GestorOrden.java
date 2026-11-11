/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.DecisionClienteDTO;
import DTOS.OrdenTransporteDTO;
import DTOS.SolicitudTransporteDTO;
import java.time.LocalDate;

/**
 *
 * @author tolan
 */
public class GestorOrden {
    AlmacenTemporal almacen; //Mientras implementamos los BO
    
    public GestorOrden() {
    }
    
    public GestorOrden(AlmacenTemporal almacen) {
        this.almacen = almacen;
    }
    
    public DecisionClienteDTO registrarDecision(String folio, DecisionClienteDTO decision){
         if (decision == null || decision.getIdDecision() == null
                || decision.getIdDecision().isBlank()) {
            throw new IllegalArgumentException("La decisión debe tener idDecision");
        }
        if (decision.getFechaDecision() == null) {
            decision.setFechaDecision(java.time.LocalDate.now());
        }
        if (decision.getTipoDecision() == null || decision.getTipoDecision().isBlank()) {
            throw new IllegalArgumentException("La decisión debe tener tipoDecision");
        }
        return almacen.registrarDecision(decision);
    }
    public OrdenTransporteDTO generarOrdenTransporte(String folio){
        OrdenTransporteDTO orden = new OrdenTransporteDTO();
        orden.setCondicionesAcordadas("");
        orden.setEstado("EMITIDO");
        orden.setFolio(almacen.generarFolioOrden());
        orden.setFechaEmision(java.time.LocalDate.now());
        //orden.setImporteAutorizado(cotizacion.getTotal()); Esperamos a poder tratarla
        return orden;
    }
}
