package Control;

import Almacen.AlmacenTemporal;
import Coordinador.CoordinadorAplicacion;
import Validaciones.ValidadorPresentacion;
import DTOS.*;
import Simuladores.SimuladorCotizacion;
import Simuladores.SimuladorEvaluacion;
import Simuladores.SimuladorPropuesta;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime
 */

public class ControlMercanciaTerrestre {

    private final CoordinadorAplicacion coordinador;
    private final AlmacenTemporal almacen = AlmacenTemporal.getInstancia();
    private final ValidadorPresentacion validador = new ValidadorPresentacion();

    // simulaciones del negocio (mockeados en otro paquete)
    private final SimuladorEvaluacion simuladorEvaluacion = new SimuladorEvaluacion();
    private final SimuladorPropuesta simuladorPropuesta = new SimuladorPropuesta();
    private final SimuladorCotizacion simuladorCotizacion = new SimuladorCotizacion();

    private UsuarioDTO usuarioActual;
    private ClienteDTO clienteSeleccionado;
    private SolicitudTransporteDTO solicitudActual;
    private EvaluacionTrasladoDTO evaluacionActual;
    private String rutaSeleccionada = "A";
    private TipoUnidadDTO unidadSeleccionada;
    private boolean requiereSeguroCarga = false;
    private PropuestaOperativaDTO propuestaActual;
    private CotizacionDTO cotizacionActual;
    private OrdenTransporteDTO ordenActual;

    public ControlMercanciaTerrestre(CoordinadorAplicacion coordinador) {
        this.coordinador = coordinador;
    }

    // inicio de sesion
    public void iniciarSesion(String nombre, String contrasenia) {
        if (validador.esTextoVacio(nombre)) {
            coordinador.mostrarError("Ingrese su usuario.");
            return;
        }
        if (validador.esTextoVacio(contrasenia)) {
            coordinador.mostrarError("Ingrese su contraseña.");
            return;
        }
        UsuarioDTO usuario = almacen.buscarUsuarioPorNombre(nombre.trim());
        if (usuario == null || !usuario.getContrasenia().equals(contrasenia)) {
            coordinador.mostrarError("Usuario o contraseña incorrectos.");
            return;
        }
        this.usuarioActual = usuario;
        irAMenuClientes();
    }

    // clientes
    public void irAMenuClientes() {
        limpiarFlujoSolicitud();
        coordinador.mostrarMenuClientes(usuarioActual, almacen.obtenerClientes());
    }

    public void buscarClientes(String texto) {
        coordinador.actualizarListaClientes(almacen.buscarClientes(texto));
    }

    public void limpiarFiltrosClientes() {
        coordinador.actualizarListaClientes(almacen.obtenerClientes());
    }

    public void irARegistrarCliente() {
        coordinador.mostrarRegistrarCliente();
    }

    public void irAEditarCliente(ClienteDTO cliente) {
        this.clienteSeleccionado = cliente;
        coordinador.mostrarEditarCliente(cliente);
    }

    public void irADetalleCliente(ClienteDTO cliente) {
        this.clienteSeleccionado = cliente;
        limpiarFlujoSolicitud();
        coordinador.mostrarDetalleCliente(cliente, almacen.obtenerSolicitudesPorCliente(cliente.getIdCliente()));
    }

    public void registrarCliente(String nombre, String telefono, String correo, String rfc) {
        String error = validarDatosCliente(nombre, telefono, correo, rfc, true);
        if (error != null) {
            coordinador.mostrarError(error);
            return;
        }
        ClienteDTO nuevo = new ClienteDTO(null, nombre.trim(), correo.trim(), rfc.trim().toUpperCase(), telefono.trim());
        almacen.registrarCliente(nuevo);
        irAMenuClientes();
        coordinador.mostrarConfirmacion("Cliente registrado correctamente.");
    }

    public void guardarCambiosCliente(ClienteDTO cliente, String nombre, String correo, String telefono) {
        String error = validarDatosCliente(nombre, telefono, correo, null, false);
        if (error != null) {
            coordinador.mostrarError(error);
            return;
        }
        cliente.setNombre(nombre.trim());
        cliente.setCorreo(correo.trim());
        cliente.setTelefono(telefono.trim());
        almacen.actualizarCliente(cliente);
        irAMenuClientes();
        coordinador.mostrarConfirmacion("Cliente actualizado correctamente.");
    }

    private String validarDatosCliente(String nombre, String telefono, String correo, String rfc, boolean validarRfc) {
        
        if (validador.esTextoVacio(nombre)) {
            return "El nombre es obligatorio.";
        }
        if (!validador.esNombreValido(nombre)) {
            return "El nombre solo puede contener letras y espacios.";
        }
        if (validador.esTextoVacio(telefono)) {
            return "El teléfono es obligatorio.";
        }
        if (!validador.esTelefonoValido(telefono)) {
            return "El teléfono debe tener 10 dígitos numéricos.";
        }
        if (validador.esTextoVacio(correo)) {
            return "El correo es obligatorio.";
        }
        if (!validador.esCorreoValido(correo)) {
            return "El correo no tiene un formato válido (ejemplo: nombre@dominio.com).";
        }
        if (validarRfc) {
            if (validador.esTextoVacio(rfc)) {
                return "El RFC es obligatorio.";
            }
            if (!validador.esRfcValido(rfc)) {
                return "El RFC no tiene un formato válido (12 o 13 caracteres, ejemplo: XGUA030211VM5).";
            }
        }
        return null;
    }


    // solicitud
    
    public void iniciarSolicitud() {
        SolicitudTransporteDTO nueva = new SolicitudTransporteDTO();
        nueva.setFolio(almacen.generarFolioSolicitud());
        nueva.setCliente(clienteSeleccionado);
        nueva.setEstado("EN_EVALUACION");
        limpiarFlujoSolicitud();
        this.solicitudActual = nueva;
        coordinador.mostrarRegistroFechas(clienteSeleccionado, solicitudActual);
    }

    public void irARegistroFechas() {
        coordinador.mostrarRegistroFechas(clienteSeleccionado, solicitudActual);
    }

    public void guardarOrigenDestino(PuntoTrasladoDTO origen, PuntoTrasladoDTO destino, Date fechaCarga, Date fechaEntrega, String notas) {
        String error = validarPunto(origen, "Origen");
        if (error == null) {
            error = validarPunto(destino, "Destino");
        }
        if (error == null && !validador.esFechaValida(fechaCarga)) {
            error = "Seleccione la fecha de carga.";
        }
        if (error == null && !validador.esFechaValida(fechaEntrega)) {
            error = "Seleccione la fecha de entrega.";
        }
        LocalDate carga = null;
        LocalDate entrega = null;
        if (error == null) {
            carga = aLocalDate(fechaCarga);
            entrega = aLocalDate(fechaEntrega);
            if (entrega.isBefore(carga)) {
                error = "La fecha de entrega no puede ser anterior a la fecha de carga.";
            }
        }
        if (error != null) {
            coordinador.mostrarError(error);
            return;
        }
        solicitudActual.setOrigen(origen);
        solicitudActual.setDestino(destino);
        solicitudActual.setFechaCarga(carga);
        solicitudActual.setFechaEntrega(entrega);
        solicitudActual.setObservaciones(notas == null ? "" : notas.trim());
        coordinador.mostrarRegistroMercancia(solicitudActual);
    }

    private String validarPunto(PuntoTrasladoDTO p, String etiqueta) {
        if (validador.esTextoVacio(p.getCalle())) {
            return etiqueta + ": la calle es obligatoria.";
        }
        if (validador.esTextoVacio(p.getNumero())) {
            return etiqueta + ": el número es obligatorio.";
        }
        if (validador.esTextoVacio(p.getColonia())) {
            return etiqueta + ": la colonia es obligatoria.";
        }
        if (validador.esTextoVacio(p.getCodigoPostal())) {
            return etiqueta + ": el código postal es obligatorio.";
        }
        if (!validador.esCodigoPostalValido(p.getCodigoPostal())) {
            return etiqueta + ": el código postal debe tener 5 dígitos.";
        }
        if (validador.esTextoVacio(p.getCiudad())) {
            return etiqueta + ": la ciudad es obligatoria.";
        }
        if (validador.esTextoVacio(p.getEstado())) {
            return etiqueta + ": el estado es obligatorio.";
        }
        if (validador.esTextoVacio(p.getNomContacto())) {
            return etiqueta + ": el nombre de contacto es obligatorio.";
        }
        if (validador.esTextoVacio(p.getTelContacto())) {
            return etiqueta + ": el teléfono de contacto es obligatorio.";
        }
        if (!validador.esTelefonoValido(p.getTelContacto())) {
            return etiqueta + ": el teléfono de contacto debe tener 10 dígitos numéricos.";
        }
        if (validador.esTextoVacio(p.getVentanaHoraria())) {
            return etiqueta + ": la ventana horaria es obligatoria (ejemplo: 08:00 - 12:00).";
        }
        return null;
    }

    private LocalDate aLocalDate(Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    // cancelacion
    
    public void solicitarCancelacion() {
        coordinador.mostrarMotivoCancelacion(false);
    }

    public void solicitarRechazo() {
        coordinador.mostrarMotivoCancelacion(true);
    }

    public void cerrarDialogo() {
        coordinador.cerrarDialogo();
    }

    public void confirmarCancelacion(String motivo, boolean esRechazo) {
        if (validador.esTextoVacio(motivo)) {
            coordinador.mostrarError("Indique el motivo.");
            return;
        }
        if (esRechazo) {
            solicitudActual.setEstado("RECHAZADA");
            solicitudActual.setMotivoEstado(motivo.trim());
            registrarDecision("RECHAZAR", motivo.trim());
            almacen.registrarSolicitud(solicitudActual);
        }
        irAMenuClientes();
        coordinador.mostrarConfirmacion(esRechazo
                ? "La propuesta fue rechazada y el motivo quedó registrado."
                : "La solicitud fue cancelada.");
    }

    // captura de mercancía

    public MercanciaDTO agregarMercancia(String nombre, String cantidad, String empaque,String peso, String largo, String ancho, String alto, String condiciones, String observaciones) {
        String error = null;
        if (validador.esTextoVacio(nombre)) {
            error = "El concepto de la mercancía es obligatorio.";
        } else if (!validador.esNumeroEntero(cantidad) || Integer.parseInt(cantidad.trim()) <= 0) {
            error = "La cantidad debe ser un número entero mayor a cero.";
        } else if (!validador.esSeleccionValida(empaque)) {
            error = "Seleccione el tipo de empaque.";
        } else if (!esDecimalPositivo(peso)) {
            error = "El peso debe ser un número mayor a cero.";
        } else if (!esDecimalPositivo(largo)) {
            error = "El largo debe ser un número mayor a cero.";
        } else if (!esDecimalPositivo(ancho)) {
            error = "El ancho debe ser un número mayor a cero.";
        } else if (!esDecimalPositivo(alto)) {
            error = "El alto debe ser un número mayor a cero.";
        }
        if (error != null) {
            coordinador.mostrarError(error);
            return null;
        }
        
        MercanciaDTO m = new MercanciaDTO();
        m.setIdMercancia(almacen.generarIdMercancia());
        m.setNombre(nombre.trim());
        m.setCantidad(Integer.parseInt(cantidad.trim()));
        m.setTipoEmpaque(empaque.trim());
        m.setPeso(Double.parseDouble(peso.trim()));
        m.setLargo(Double.parseDouble(largo.trim()));
        m.setAncho(Double.parseDouble(ancho.trim()));
        m.setAlto(Double.parseDouble(alto.trim()));
        m.setCondicionesEsp(validador.esTextoVacio(condiciones) ? "Ninguna" : condiciones.trim());
        m.setInfoAdicional(validador.esTextoVacio(observaciones) ? "N/A" : observaciones.trim());
        solicitudActual.getMercancias().add(m);
        return m;
    }

    private boolean esDecimalPositivo(String texto) {
        return validador.esNumeroDecimal(texto) && Double.parseDouble(texto.trim()) > 0;
    }

    public void regresarACaptura() {
        coordinador.mostrarRegistroMercancia(solicitudActual);
    }

    public void finalizarCapturaMercancia() {
        if (solicitudActual.getMercancias().isEmpty()) {
            coordinador.mostrarAdvertencia("Agregue al menos una mercancía antes de continuar.");
            return;
        }
        coordinador.mostrarEvaluacionSolicitud(solicitudActual);
    }

    // evaluacion

    public void irAEvaluacion() {
        coordinador.mostrarEvaluacionSolicitud(solicitudActual);
    }

    public void editarMercancia(int indice) {
        if (!validador.esIndiceSeleccionado(indice) || indice >= solicitudActual.getMercancias().size()) {
            coordinador.mostrarAdvertencia("Seleccione una mercancía de la tabla para editarla.");
            return;
        }
        coordinador.mostrarEditarMercancia(solicitudActual.getMercancias().get(indice));
    }

    public void guardarEdicionMercancia(MercanciaDTO mercancia, String peso, String dimensiones,
            String empaque, String observaciones, String condiciones) {
        if (!esDecimalPositivo(peso)) {
            coordinador.mostrarError("El peso individual debe ser un número mayor a cero.");
            return;
        }
        String[] partes = dimensiones == null ? new String[0] : dimensiones.trim().split("\\s*[×xX]\\s*");
        if (partes.length != 3 || !esDecimalPositivo(partes[0]) || !esDecimalPositivo(partes[1]) || !esDecimalPositivo(partes[2])) {
            coordinador.mostrarError("Las dimensiones deben tener el formato Largo × Ancho × Alto (en metros), " + "con números mayores a cero. Ejemplo: 1×0.5×1");
            return;
        }
        if (!validador.esSeleccionValida(empaque)) {
            coordinador.mostrarError("Seleccione el tipo de empaque.");
            return;
        }
        mercancia.setPeso(Double.parseDouble(peso.trim()));
        mercancia.setLargo(Double.parseDouble(partes[0].trim()));
        mercancia.setAncho(Double.parseDouble(partes[1].trim()));
        mercancia.setAlto(Double.parseDouble(partes[2].trim()));
        mercancia.setTipoEmpaque(empaque.trim());
        mercancia.setInfoAdicional(validador.esTextoVacio(observaciones) ? "N/A" : observaciones.trim());
        mercancia.setCondicionesEsp(validador.esTextoVacio(condiciones) ? "Ninguna" : condiciones.trim());
        coordinador.mostrarEvaluacionSolicitud(solicitudActual);
    }

    public void finalizarEvaluacion() {
        if (solicitudActual.getMercancias().isEmpty()) {
            coordinador.mostrarAdvertencia("La solicitud no tiene mercancías registradas.");
            return;
        }
        this.evaluacionActual = simuladorEvaluacion.simularEvaluacion(solicitudActual);
        coordinador.mostrarEstimacionRuta(solicitudActual, rutaSeleccionada);
    }

    // ruta, unidad y cotizacion

    public void irAEstimacionRuta() {
        coordinador.mostrarEstimacionRuta(solicitudActual, rutaSeleccionada);
    }

    public void seleccionarRuta(String ruta) {
        this.rutaSeleccionada = ruta;
    }

    public void confirmarRuta(String ruta) {
        if (!validador.esSeleccionValida(ruta)) {
            coordinador.mostrarAdvertencia("Seleccione una ruta para continuar.");
            return;
        }
        this.rutaSeleccionada = ruta;
        irASeleccionUnidad();
    }

    public void irASeleccionUnidad() {
        coordinador.mostrarSeleccionUnidad(solicitudActual, almacen.obtenerTiposUnidad(),
                unidadSeleccionada, requiereSeguroCarga);
    }

    public void confirmarUnidad(String nombreUnidad, boolean seguro) {
        if (!validador.esSeleccionValida(nombreUnidad)) {
            coordinador.mostrarAdvertencia("Seleccione un tipo de unidad para continuar.");
            return;
        }
        TipoUnidadDTO tipo = almacen.buscarTipoUnidadPorNombre(nombreUnidad);
        if (tipo == null) {
            coordinador.mostrarError("El tipo de unidad seleccionado no existe en el catálogo.");
            return;
        }
        this.unidadSeleccionada = tipo;
        this.requiereSeguroCarga = seguro;
        solicitarPropuestaYCotizacion();
        coordinador.mostrarCotizacion(solicitudActual, propuestaActual, cotizacionActual);
    }

    private void solicitarPropuestaYCotizacion() {
        this.propuestaActual = simuladorPropuesta.simularPropuesta(rutaSeleccionada, unidadSeleccionada, requiereSeguroCarga);
        this.cotizacionActual = simuladorCotizacion.simularCotizacion(solicitudActual, propuestaActual);
    }

    public void irACotizacion() {
        coordinador.mostrarCotizacion(solicitudActual, propuestaActual, cotizacionActual);
    }

    public void irARevisarCotizacion() {
        coordinador.mostrarRevisarCotizacion(solicitudActual, propuestaActual, cotizacionActual);
    }

    public void irADecisionCliente() {
        coordinador.mostrarDecisionCliente(solicitudActual, propuestaActual, cotizacionActual);
    }

    public void cambiarSeguroCarga(boolean seguro) {
        this.requiereSeguroCarga = seguro;
        solicitarPropuestaYCotizacion();
        coordinador.actualizarCotizacion(cotizacionActual);
    }


    // decision

    public void registrarDecisionCliente(String tipo, String notas) {
        if (!validador.esSeleccionValida(tipo)) {
            coordinador.mostrarAdvertencia("Seleccione la decisión del cliente (aceptar, modificar o rechazar).");
            return;
        }
        switch (tipo) {
            case "ACEPTACION":
                registrarDecision("ACEPTACION", validador.esTextoVacio(notas) ? "Aceptación total de la propuesta" : notas.trim());
                coordinador.mostrarAceptacionRegistrada(solicitudActual, propuestaActual, cotizacionActual);
                break;
            case "MODIFICACIONES":
                coordinador.mostrarSolicitarModificacion();
                break;
            case "RECHAZAR":
                solicitarRechazo();
                break;
            default:
                coordinador.mostrarError("Decisión no reconocida.");
        }
    }

    public void confirmarSolicitudModificacion(boolean cambioMercancia, boolean cambioRuta) {
        if (!cambioMercancia && !cambioRuta) {
            coordinador.mostrarAdvertencia("Seleccione qué desea modificar.");
            return;
        }
        registrarDecision("MODIFICACIONES", cambioMercancia ? "Cambio de mercancía" : "Cambio de ruta");
        if (cambioMercancia) {
            coordinador.mostrarEvaluacionSolicitud(solicitudActual);
        } else {
            coordinador.mostrarEstimacionRuta(solicitudActual, rutaSeleccionada);
        }
    }

    private void registrarDecision(String tipo, String observaciones) {
        DecisionClienteDTO d = new DecisionClienteDTO(almacen.generarIdDecision(), LocalDate.now(), tipo, observaciones);
        almacen.registrarDecision(d);
    }


    // orden
 
    public void irAAceptacionRegistrada() {
        coordinador.mostrarAceptacionRegistrada(solicitudActual, propuestaActual, cotizacionActual);
    }

    public void generarOrden() {
        OrdenTransporteDTO orden = new OrdenTransporteDTO();
        orden.setIdOrdenTransporte("OT-" + solicitudActual.getFolio().replace("SOL-", ""));
        orden.setFolio(almacen.generarFolioOrden());
        orden.setFechaEmision(LocalDate.now());
        orden.setEstado("GENERADA (Lista p/ Flota)");
        orden.setImporteAutorizado(cotizacionActual.getTotal());
        orden.setCondicionesAcordadas("Ruta " + propuestaActual.getRutaSeleccionada() + " | " + unidadSeleccionada.getNombre() + (requiereSeguroCarga ? " | Con seguro de carga" : ""));
        almacen.registrarOrden(orden);

        solicitudActual.setEstado("GENERADA (Lista p/ Flota)");
        if (almacen.buscarSolicitudPorFolio(solicitudActual.getFolio()) == null) {
            almacen.registrarSolicitud(solicitudActual);
        } else {
            almacen.actualizarSolicitud(solicitudActual);
        }
        this.ordenActual = orden;
        coordinador.mostrarOrdenGenerada(solicitudActual, propuestaActual, cotizacionActual, orden);
    }

    public void enviarOrdenPorCorreo() {
        ClienteDTO cliente = solicitudActual != null ? solicitudActual.getCliente() : null;
        String correo = cliente != null ? cliente.getCorreo() : null;
        if (validador.esTextoVacio(correo) || !validador.esCorreoValido(correo)) { 
            coordinador.mostrarError("El cliente no tiene un correo válido registrado.");
            return;
        }
        coordinador.mostrarConfirmacion("La orden " + ordenActual.getFolio() + " fue enviada a " + correo + ".");
    }

    public void finalizarProceso() {
        irAMenuClientes();
        coordinador.mostrarConfirmacion("El proceso finalizó. La orden quedó registrada.");
    }

    private void limpiarFlujoSolicitud() {
        this.solicitudActual = null;
        this.evaluacionActual = null;
        this.rutaSeleccionada = "A";
        this.unidadSeleccionada = null;
        this.requiereSeguroCarga = false;
        this.propuestaActual = null;
        this.cotizacionActual = null;
        this.ordenActual = null;
    }

    public ValidadorPresentacion getValidador() {
        return validador;
    }

}
