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
    
    // Clientes
    public abstract List<ClienteDTO> consultarClientes();
    public abstract List<ClienteDTO> buscarClientes(String texto);
    public abstract ClienteDTO registrarCliente(ClienteDTO cliente);
    public abstract boolean actualizarCliente(ClienteDTO cliente);

    // Solicitud
    public abstract List<SolicitudTransporteDTO> consultarSolicitudesPorCliente(String idCliente);
    public abstract SolicitudTransporteDTO generarSolicitud(ClienteDTO cliente);
    public abstract MercanciaDTO agregarMercancia(SolicitudTransporteDTO solicitud, MercanciaDTO mercancia);
    public abstract SolicitudTransporteDTO guardarSolicitud(SolicitudTransporteDTO solicitud);

    // Evaluación
    public abstract EvaluacionTrasladoDTO evaluarSolicitud(SolicitudTransporteDTO solicitud);

    // Propuesta operativa
    public abstract List<TipoUnidadDTO> consultarTiposUnidad();
    public abstract TipoUnidadDTO consultarTipoUnidadPorNombre(String nombre);
    public abstract PropuestaOperativaDTO generarPropuesta(String ruta, TipoUnidadDTO tipoUnidad, boolean requiereSeguroCarga);

    // Cotización
    public abstract  CotizacionDTO generarCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta);

    // Oeden
    public abstract DecisionClienteDTO registrarDecision(String tipoDecision, String observaciones);
    public abstract OrdenTransporteDTO generarOrden(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion);
   
}
