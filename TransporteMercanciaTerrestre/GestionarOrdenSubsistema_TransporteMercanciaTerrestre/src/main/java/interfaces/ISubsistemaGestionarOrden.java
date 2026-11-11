/*
 * Click nbfs//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs//nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

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
import java.util.List;

/**
 *
 * @author tolan
 */
public interface ISubsistemaGestionarOrden {
    
    public ClienteDTO buscarCliente(String correo);
    public boolean validarEstadoCliente(String idCliente);
    public SolicitudTransporteDTO iniciarSolicitud(String idCliente);
    public SolicitudTransporteDTO registrarOrigenDestino(String folio, PuntoTrasladoDTO origen, PuntoTrasladoDTO destino);
    public SolicitudTransporteDTO agregarMercancia(String folio, MercanciaDTO mercancia);
    public SolicitudTransporteDTO enviarAEvaluacion(String folio);
    public EvaluacionTrasladoDTO evaluarSolicitud(String folio, EvaluacionTrasladoDTO evaluacion);
    public List<RutaDTO> crearRutas(String folio);
    public PropuestaOperativaDTO seleccionarRuta(String folio, RutaDTO ruta);
    public List<TipoUnidadDTO> obtenerUnidadesDisponibles(double volumenRequerido, String condicionEspecial);
    public PropuestaOperativaDTO seleccionarUnidad(String folio, TipoUnidadDTO tipoUnidad);
    public List<ServicioDTO> obtenerServicios(TipoUnidadDTO tipoUnidad, RutaDTO ruta);
    public DetalleCotizacionDTO generarDetalleCotizacion(ServicioDTO servicio, String caracterServicio, boolean esIndispensable);
    public CotizacionDTO finalizarCotizacion(String folio, List<DetalleCotizacionDTO> detalles);
    public DecisionClienteDTO registrarDecision(String folio, DecisionClienteDTO decision);
    public OrdenTransporteDTO generarOrden(String folio);
   
}
