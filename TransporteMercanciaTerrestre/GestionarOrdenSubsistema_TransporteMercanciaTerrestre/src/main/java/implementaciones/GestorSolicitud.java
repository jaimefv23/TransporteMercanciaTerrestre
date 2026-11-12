/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import BOs.IMercanciaBO;
import BOs.ISolicitudTransporteBO;
import DTOS.ClienteDTO;
import DTOS.MercanciaDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import BOs.FabricaBO;
import java.util.List;

/**
 *
 * @author tolan
 */
public class GestorSolicitud {
    
    private ISolicitudTransporteBO solicitudBO;
    private IMercanciaBO mercanciaBO;

    public GestorSolicitud() {
        this.solicitudBO = FabricaBO.crearSolicitudTransporteBO();
        this.mercanciaBO = FabricaBO.crearMercanciaBO();
    }

    public SolicitudTransporteDTO iniciarSolicitud(ClienteDTO cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Debe seleccionarse un cliente");
        }
        SolicitudTransporteDTO solicitud = new SolicitudTransporteDTO();
        solicitud.setFolio(solicitudBO.generarFolio());
        solicitud.setCliente(cliente);
        solicitud.setEstado("EN_EVALUACION");
        return solicitud;
    }

    public List<SolicitudTransporteDTO> consultarSolicitudesPorCliente(String idCliente) {
        return solicitudBO.consultarPorCliente(idCliente);
    }

    public MercanciaDTO agregarMercancia(SolicitudTransporteDTO solicitud, MercanciaDTO mercancia) {
        MercanciaDTO agregada = mercanciaBO.agregar(mercancia);
        solicitud.getMercancias().add(agregada);
        return agregada;
    }

    public SolicitudTransporteDTO guardarSolicitud(SolicitudTransporteDTO solicitud) {
        return solicitudBO.guardar(solicitud);
    }
    
}
