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
    GestorCliente clientesG;
    GestorSolicitud solicitudG;
    GestorEvaluacion evaluacionG;
    GestorPropuestaOperativa propuestaG;
    GestorCotizacion cotizacionG;
    GestorOrden ordenG;
    AlmacenTemporal almacen;

    public SubsistemaGestionarOrden() {
    }

    public SubsistemaGestionarOrden(AlmacenTemporal almacen) {
        this.clientesG = new GestorCliente(almacen);
        this.solicitudG = solicitudG = new GestorSolicitud(almacen);
        this.evaluacionG = evaluacionG = new GestorEvaluacion(almacen);
        this.propuestaG = propuestaG = new GestorPropuestaOperativa(almacen);
        this.cotizacionG = cotizacionG = new GestorCotizacion(almacen);
        this.ordenG = ordenG = new GestorOrden(almacen);
        this.almacen = almacen;
    }
    
    @Override
    public ClienteDTO buscarCliente(String correo) {
        return clientesG.buscarPorCorreo(correo);
    }

    @Override
    public boolean validarEstadoCliente(String idCliente) {
        return clientesG.validarEstado(idCliente);
    }

    @Override
    public SolicitudTransporteDTO iniciarSolicitud(String idCliente) {
        return solicitudG.crearSolicitud(idCliente);
    }

    @Override
    public SolicitudTransporteDTO registrarOrigenDestino(String folio, PuntoTrasladoDTO origen, PuntoTrasladoDTO destino) {
        return solicitudG.asignarOrigenDestino(folio, origen, destino);
    }

    @Override
    public SolicitudTransporteDTO agregarMercancia(String folio, MercanciaDTO mercancia) {
        return solicitudG.agregarMercancia(folio, mercancia);
    }

    @Override
    public SolicitudTransporteDTO enviarAEvaluacion(String folio) {
        return solicitudG.marcarEnEvaluacion(folio);
    }

    @Override
    public EvaluacionTrasladoDTO evaluarSolicitud(String folio, EvaluacionTrasladoDTO evaluacion) {
        return evaluacionG.evaluarViabilidad(folio, evaluacion);
    }

    @Override
    public List<RutaDTO> crearRutas(String folio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody        
    }

    @Override
    public PropuestaOperativaDTO seleccionarRuta(String folio, RutaDTO ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TipoUnidadDTO> obtenerUnidadesDisponibles(double volumenRequerido, String condicionEspecial) {
        return propuestaG.buscarUnidadesDisponibles(volumenRequerido, condicionEspecial);
    }

    @Override
    public PropuestaOperativaDTO seleccionarUnidad(String folio, TipoUnidadDTO tipoUnidad) {
        return propuestaG.seleccionarUnidad(folio, tipoUnidad);
    }

    @Override
    public List<ServicioDTO> obtenerServicios(TipoUnidadDTO tipoUnidad, RutaDTO ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DetalleCotizacionDTO generarDetalleCotizacion(ServicioDTO servicio, String caracterServicio, boolean esIndispensable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CotizacionDTO finalizarCotizacion(String folio, List<DetalleCotizacionDTO> detalles) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DecisionClienteDTO registrarDecision(String folio, DecisionClienteDTO decision) {
        return ordenG.registrarDecision(folio, decision);
    }

    @Override
    public OrdenTransporteDTO generarOrden(String folio) {
        return ordenG.generarOrdenTransporte(folio);
    }
    
}
