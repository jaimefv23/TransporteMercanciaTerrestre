/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.ClienteDTO;
import DTOS.CotizacionDTO;
import DTOS.DecisionClienteDTO;
import DTOS.DetalleCotizacionDTO;
import DTOS.EvaluacionTrasladoDTO;
import DTOS.MercanciaDTO;
import DTOS.OrdenTransporteDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.RutaDTO;
import DTOS.ServicioDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import interfaces.ISubsistemaGestionarOrden;
import java.util.List;

/**
 *
 * @author tolan
 */
public class SubsistemaGestionarOrden implements ISubsistemaGestionarOrden {
    
    private GestorCliente clienteG;
    private GestorSolicitud solicitudG;
    private GestorEvaluacion evaluacionG;
    private GestorPropuestaOperativa propuestaG;
    private GestorCotizacion cotizacionG;
    private GestorOrden ordenG;

    public SubsistemaGestionarOrden() {
        this.clienteG = new GestorCliente();
        this.solicitudG = new GestorSolicitud();
        this.evaluacionG = new GestorEvaluacion();
        this.propuestaG = new GestorPropuestaOperativa();
        this.cotizacionG = new GestorCotizacion();
        this.ordenG = new GestorOrden();
    }

    @Override
    public List<ClienteDTO> consultarClientes() {
        return clienteG.consultarClientes();
    }

    @Override
    public List<ClienteDTO> buscarClientes(String texto) {
        return clienteG.buscarClientes(texto);
    }

    @Override
    public ClienteDTO registrarCliente(ClienteDTO cliente) {
        return clienteG.registrarCliente(cliente);
    }

    @Override
    public boolean actualizarCliente(ClienteDTO cliente) {
        return clienteG.actualizarCliente(cliente);
    }

    @Override
    public List<SolicitudTransporteDTO> consultarSolicitudesPorCliente(String idCliente) {
        return solicitudG.consultarSolicitudesPorCliente(idCliente);
    }

    @Override
    public SolicitudTransporteDTO generarSolicitud(ClienteDTO cliente) {
        return solicitudG.iniciarSolicitud(cliente);
    }

    @Override
    public MercanciaDTO agregarMercancia(SolicitudTransporteDTO solicitud, MercanciaDTO mercancia) {
        return solicitudG.agregarMercancia(solicitud, mercancia);
    }

    @Override
    public SolicitudTransporteDTO guardarSolicitud(SolicitudTransporteDTO solicitud) {
        return solicitudG.guardarSolicitud(solicitud);
    }

    @Override
    public EvaluacionTrasladoDTO evaluarSolicitud(SolicitudTransporteDTO solicitud) {
        return evaluacionG.evaluarSolicitud(solicitud);
    }

    @Override
    public List<TipoUnidadDTO> consultarTiposUnidad() {
        return propuestaG.consultarTiposUnidad();
    }

    @Override
    public TipoUnidadDTO consultarTipoUnidadPorNombre(String nombre) {
        return propuestaG.consultarTipoUnidadPorNombre(nombre);
    }

    @Override
    public PropuestaOperativaDTO generarPropuesta(String ruta, TipoUnidadDTO tipoUnidad, boolean requiereSeguroCarga) {
        return propuestaG.generarPropuesta(ruta, tipoUnidad, requiereSeguroCarga);
    }

    @Override
    public CotizacionDTO generarCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta) {
        return cotizacionG.generarCotizacion(solicitud, propuesta);
    }

    @Override
    public DecisionClienteDTO registrarDecision(String tipoDecision, String observaciones) {
        return ordenG.registrarDecision(tipoDecision, observaciones);
    }

    @Override
    public OrdenTransporteDTO generarOrden(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        OrdenTransporteDTO orden = ordenG.generarOrden(solicitud, propuesta, cotizacion);
        solicitud.setEstado("GENERADA (Lista p/ Flota)");
        solicitudG.guardarSolicitud(solicitud);
        return orden;
    }
}
