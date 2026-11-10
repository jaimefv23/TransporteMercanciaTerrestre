/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.MercanciaDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;

/**
 *
 * @author tolan
 */
public class GestorSolicitud {
    AlmacenTemporal almacen; //Mientras implementamos los BO
    
    public SolicitudTransporteDTO crearSolicitud(String idCliente){
        SolicitudTransporteDTO nueva = new SolicitudTransporteDTO();
        nueva.setFolio(almacen.generarFolioSolicitud());
        nueva.setCliente(almacen.buscarClientePorId(idCliente));
        
        return nueva;
    }
    
    public SolicitudTransporteDTO asignarOrigenDestino(String folio, PuntoTrasladoDTO origen, PuntoTrasladoDTO destino){
        SolicitudTransporteDTO solicitud = almacen.buscarSolicitudPorFolio(folio);
        solicitud.setOrigen(origen);
        solicitud.setDestino(destino);
        return solicitud;
    }
    
    public SolicitudTransporteDTO agregarMercancia(String folio, MercanciaDTO mercancia){
        SolicitudTransporteDTO solicitud = almacen.buscarSolicitudPorFolio(folio);
        
        return solicitud;
    }
    public SolicitudTransporteDTO marcarEnEvaluacion(String folio){
        SolicitudTransporteDTO solicitud = almacen.buscarSolicitudPorFolio(folio);
        solicitud.setEstado("EN_EVALUACION");
        
        return solicitud;
    }
    
}
