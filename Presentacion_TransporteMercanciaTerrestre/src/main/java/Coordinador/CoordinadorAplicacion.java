package Coordinador;

import Control.ControlMercanciaTerrestre;
import DTOS.ClienteDTO;
import DTOS.CotizacionDTO;
import DTOS.MercanciaDTO;
import DTOS.OrdenTransporteDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import DTOS.UsuarioDTO;
import Pantallas.PantallaAceptacionRegistrada;
import Pantallas.PantallaCotizacion;
import Pantallas.PantallaDecisionCliente;
import Pantallas.PantallaDetalleCliente;
import Pantallas.PantallaEditarCliente;
import Pantallas.PantallaEditarSolicitudMercancia;
import Pantallas.PantallaEstimacionRuta;
import Pantallas.PantallaEvaluacionSolicitud;
import Pantallas.PantallaInicioSesion;
import Pantallas.PantallaMenuClientes;
import Pantallas.PantallaMotivoCancelacion;
import Pantallas.PantallaOrdenGenerada;
import Pantallas.PantallaRegistrarCliente;
import Pantallas.PantallaRegistroSolicitudFechas;
import Pantallas.PantallaRegistroSolicitudMercancia;
import Pantallas.PantallaRevisarCotizacion;
import Pantallas.PantallaSeleccionUnidad;
import Pantallas.PantallaSolicitarModificacion;
import java.awt.Window;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * - crea, muestra, oculta y cierra ventanas.
 * - entrega a cada vista los DTOs que necesita mostrar.
 * - no valida, no consulta el Almacen Temporal y no conoce reglas de negocio.
 *
 */

/**
 *
 * @author alex
 */
public class CoordinadorAplicacion {

    private ControlMercanciaTerrestre controlParaVistas;
    private JFrame ventanaActual;
    private JDialog dialogoActual;

    public void setControlParaVistas(ControlMercanciaTerrestre control) {
        this.controlParaVistas = control;
    }

    private void mostrar(JFrame nuevaVentana) {
        JFrame vVieja = ventanaActual;
        ventanaActual = nuevaVentana;
        nuevaVentana.setVisible(true);
        
        if (dialogoActual != null) {
            dialogoActual.dispose();
            dialogoActual = null;
        }
        
        if (vVieja != null) {
            vVieja.dispose();
        }
    }

    private void mostrarDialogo(JDialog diag) {
        dialogoActual = diag;
        diag.setVisible(true);
        dialogoActual = null; 
    }

    public void cerrarDialogo() {
        if (dialogoActual != null) {
            dialogoActual.dispose();
            dialogoActual = null;
        }
    }

    public void cerrarTodo() {
        cerrarDialogo();
        if (ventanaActual != null) {
            ventanaActual.dispose();
            ventanaActual = null;
        }
    }

    private Window getPadre() {
        if (dialogoActual != null) {
            return dialogoActual;
        } else {
            return ventanaActual;
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(getPadre(), mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(getPadre(), mensaje, "Cuidado", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(getPadre(), mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean preguntarConfirmacion(String mensaje) {
        int res = JOptionPane.showConfirmDialog(getPadre(), mensaje, "Seguro?", JOptionPane.YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }

    public void mostrarInicioSesion() {
        PantallaInicioSesion p = new PantallaInicioSesion(controlParaVistas);
        mostrar(p);
    }

    public void mostrarMenuClientes(UsuarioDTO usuario, List<ClienteDTO> clientes) {
        PantallaMenuClientes p = new PantallaMenuClientes(controlParaVistas, usuario, clientes);
        mostrar(p);
    }

    public void actualizarListaClientes(List<ClienteDTO> clientes) {
        if (ventanaActual instanceof PantallaMenuClientes) {
            PantallaMenuClientes pantalla = (PantallaMenuClientes) ventanaActual;
            pantalla.mostrarClientes(clientes);
        }
    }
    
    public void mostrarRegistrarCliente() {
        mostrar(new PantallaRegistrarCliente(controlParaVistas));
    }

    public void mostrarEditarCliente(ClienteDTO cliente) {
        mostrar(new PantallaEditarCliente(controlParaVistas, cliente));
    }

    public void mostrarDetalleCliente(ClienteDTO cliente, List<SolicitudTransporteDTO> solicitudes) {
        mostrar(new PantallaDetalleCliente(controlParaVistas, cliente, solicitudes));
    }

    public void mostrarRegistroFechas(ClienteDTO cliente, SolicitudTransporteDTO solicitud) {
        mostrar(new PantallaRegistroSolicitudFechas(controlParaVistas, cliente, solicitud));
    }

    public void mostrarRegistroMercancia(SolicitudTransporteDTO solicitud) {
        mostrar(new PantallaRegistroSolicitudMercancia(controlParaVistas, solicitud));
    }

    public void mostrarEvaluacionSolicitud(SolicitudTransporteDTO solicitud) {
        mostrar(new PantallaEvaluacionSolicitud(controlParaVistas, solicitud));
    }

    public void mostrarEditarMercancia(MercanciaDTO mercancia) {
        mostrar(new PantallaEditarSolicitudMercancia(controlParaVistas, mercancia));
    }

    public void mostrarEstimacionRuta(SolicitudTransporteDTO solicitud, String rutaSeleccionada) {
        mostrar(new PantallaEstimacionRuta(controlParaVistas, solicitud, rutaSeleccionada));
    }

    public void mostrarSeleccionUnidad(SolicitudTransporteDTO solicitud, List<TipoUnidadDTO> unidades,TipoUnidadDTO unidadSeleccionada, boolean requiereSeguro) {
        mostrar(new PantallaSeleccionUnidad(controlParaVistas, solicitud, unidades, unidadSeleccionada, requiereSeguro));
    }

    public void mostrarCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        mostrar(new PantallaCotizacion(controlParaVistas, solicitud, propuesta, cotizacion));
    }

    public void mostrarRevisarCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        mostrar(new PantallaRevisarCotizacion(controlParaVistas, solicitud, propuesta, cotizacion));
    }

    public void mostrarDecisionCliente(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        mostrar(new PantallaDecisionCliente(controlParaVistas, solicitud, propuesta, cotizacion));
    }

    public void actualizarCotizacion(CotizacionDTO cotizacion) {
        if (ventanaActual instanceof PantallaDecisionCliente) {
            ((PantallaDecisionCliente) ventanaActual).actualizarDesglose(cotizacion);
        }
    }

    public void mostrarAceptacionRegistrada(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta, CotizacionDTO cotizacion) {
        mostrar(new PantallaAceptacionRegistrada(controlParaVistas, solicitud, propuesta, cotizacion));
    }

    public void mostrarOrdenGenerada(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta,CotizacionDTO cotizacion, OrdenTransporteDTO orden) {
        mostrar(new PantallaOrdenGenerada(controlParaVistas, solicitud, propuesta, cotizacion, orden));
    }

    public void mostrarMotivoCancelacion(boolean esRechazo) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PantallaMotivoCancelacion d = new PantallaMotivoCancelacion(ventanaActual, controlParaVistas, esRechazo);
                mostrarDialogo(d);
            }
        });
    }

    public void mostrarSolicitarModificacion() {
        SwingUtilities.invokeLater(() -> {
            PantallaSolicitarModificacion d = new PantallaSolicitarModificacion(ventanaActual, controlParaVistas);
            mostrarDialogo(d);
        });
    }
}
