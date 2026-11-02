package Almacen;

import DTOS.ClienteDTO;
import DTOS.DecisionClienteDTO;
import DTOS.MercanciaDTO;
import DTOS.OrdenTransporteDTO;
import DTOS.PuntoTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import DTOS.UsuarioDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author alex
 */
public class AlmacenTemporal {

    private static AlmacenTemporal instancia;

    private final List<UsuarioDTO> usuarios = new ArrayList<>();
    private final List<ClienteDTO> clientes = new ArrayList<>();
    private final List<SolicitudTransporteDTO> solicitudes = new ArrayList<>();
    private final List<OrdenTransporteDTO> ordenes = new ArrayList<>();
    private final List<DecisionClienteDTO> decisiones = new ArrayList<>();
    private final List<TipoUnidadDTO> tiposUnidad = new ArrayList<>();

    public static synchronized AlmacenTemporal getInstancia() {
        if (instancia == null) {
            instancia = new AlmacenTemporal();
        }
        return instancia;
    }

    private AlmacenTemporal() {
        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        
        usuarios.add(new UsuarioDTO("USR-001", "Coordinador", "1234"));

        ClienteDTO c1 = new ClienteDTO(null, "Alejandro", "alexibarra123@gmail.com", "XGUA030211VM5", "6442583713");
        ClienteDTO c2 = new ClienteDTO(null, "Pablo", "pablo@gmail.com", "RKIN120601WN1", "6555555555");
        ClienteDTO c3 = new ClienteDTO(null, "Diego", "diego@gmail.com", "YGKK710620ZI3", "6555555555");
        ClienteDTO c4 = new ClienteDTO(null, "Maria", "maria@gmail.com", "CRXK860601OS5", "6555555555");
        
        registrarCliente(c1);
        registrarCliente(c2);
        registrarCliente(c3);
        registrarCliente(c4);

        TipoUnidadDTO t1 = new TipoUnidadDTO("TU-01", "Camión Refrigerado", 20000, 40, "Refrigerada", "Control de temperatura");
        TipoUnidadDTO t2 = new TipoUnidadDTO("TU-02", "Torton con Cortina", 15000, 35, "Cortina", "Ninguna");
        TipoUnidadDTO t3 = new TipoUnidadDTO("TU-03", "Trailer Caja Seca", 30000, 80, "Seca", "Ninguna");
        TipoUnidadDTO t4 = new TipoUnidadDTO("TU-04", "Camión Plataforma", 25000, 60, "Plataforma", "Maniobras especiales");
        TipoUnidadDTO t5 = new TipoUnidadDTO("TU-05", "Camioneta 3.5 Ton", 3500, 12, "Seca", "Ninguna");
        TipoUnidadDTO t6 = new TipoUnidadDTO("TU-06", "Trailer Refrigerado", 30000, 80, "Refrigerada", "Control de temperatura");
        
        tiposUnidad.add(t1);
        tiposUnidad.add(t2);
        tiposUnidad.add(t3);
        tiposUnidad.add(t4);
        tiposUnidad.add(t5);
        tiposUnidad.add(t6);
       

        ClienteDTO alejandro = clientes.get(0);
        SolicitudTransporteDTO previa = new SolicitudTransporteDTO();
        previa.setFolio(generarFolioSolicitud());
        previa.setCliente(alejandro);
        previa.setFechaCarga(LocalDate.now().minusDays(10));
        previa.setFechaEntrega(LocalDate.now().minusDays(9));
        previa.setEstado("GENERADA (Lista p/ Flota)");
        previa.setOrigen(new PuntoTrasladoDTO("PT-1", "Av. Sonora", "100", "Centro", "85000", "Sonora", "Cd. Obregón","", "Alejandro", "6442583713", "08:00 - 12:00"));
        previa.setDestino(new PuntoTrasladoDTO("PT-2", "Blvd. Kino", "250", "Pitic", "83150", "Sonora", "Hermosillo","", "Pablo", "6555555555", "09:00 - 14:00"));
        MercanciaDTO m = new MercanciaDTO(generarIdMercancia(), "Pasteles", 20, "Cajas", 1.5, 0.3, 0.4, 0.4, "N/A", "Frágil");
        previa.getMercancias().add(m);
        registrarSolicitud(previa);
    }


    // ids mediante UUID

    public String generarIdCliente() {
        return "CLI-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String generarFolioSolicitud() {
        return "SOL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String generarIdMercancia() {
        return "MER-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String generarFolioOrden() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String generarIdDecision() {
        return "DEC-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }


    public UsuarioDTO buscarUsuarioPorNombre(String nombre) {
        for (UsuarioDTO u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

    public ClienteDTO registrarCliente(ClienteDTO cliente) {
        if (cliente.getIdCliente() == null || cliente.getIdCliente().isBlank()) {
            cliente.setIdCliente(generarIdCliente());
        }
        clientes.add(cliente);
        return cliente;
    }

    public ClienteDTO buscarClientePorId(String idCliente) {
        for (ClienteDTO c : clientes) {
            if (c.getIdCliente().equals(idCliente)) {
                return c;
            }
        }
        return null;
    }

    public ClienteDTO buscarClientePorCorreo(String correo) {
        for (ClienteDTO c : clientes) {
            if (c.getCorreo() != null && c.getCorreo().equalsIgnoreCase(correo)) {
                return c;
            }
        }
        return null;
    }

    public ClienteDTO buscarClientePorRfc(String rfc) {
        for (ClienteDTO c : clientes) {
            if (c.getRFC() != null && c.getRFC().equalsIgnoreCase(rfc)) {
                return c;
            }
        }
        return null;
    }

    public List<ClienteDTO> buscarClientes(String texto) {
        List<ClienteDTO> resultado = new ArrayList<>();
        String t = texto == null ? "" : texto.trim().toLowerCase();
        for (ClienteDTO c : clientes) {
            if (t.isEmpty()
                    || contiene(c.getNombre(), t) || contiene(c.getCorreo(), t)
                    || contiene(c.getTelefono(), t) || contiene(c.getRFC(), t)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private boolean contiene(String valor, String texto) {
        return valor != null && valor.toLowerCase().contains(texto);
    }

    public List<ClienteDTO> obtenerClientes() {
        return new ArrayList<>(clientes);
    }

    public boolean actualizarCliente(ClienteDTO cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(cliente.getIdCliente())) {
                clientes.set(i, cliente);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(String idCliente) {
        return clientes.removeIf(c -> c.getIdCliente().equals(idCliente));
    }

    public SolicitudTransporteDTO registrarSolicitud(SolicitudTransporteDTO solicitud) {
        if (solicitud.getFolio() == null || solicitud.getFolio().isBlank()) {
            solicitud.setFolio(generarFolioSolicitud());
        }
        solicitudes.add(solicitud);
        return solicitud;
    }

    public SolicitudTransporteDTO buscarSolicitudPorFolio(String folio) {
        for (SolicitudTransporteDTO s : solicitudes) {
            if (s.getFolio().equals(folio)) {
                return s;
            }
        }
        return null;
    }

    public List<SolicitudTransporteDTO> obtenerSolicitudes() {
        return new ArrayList<>(solicitudes);
    }

    public List<SolicitudTransporteDTO> obtenerSolicitudesPorCliente(String idCliente) {
        List<SolicitudTransporteDTO> resultado = new ArrayList<>();
        for (SolicitudTransporteDTO s : solicitudes) {
            if (s.getCliente() != null && s.getCliente().getIdCliente().equals(idCliente)) {
                resultado.add(s);
            }
        }
        return resultado;
    }

    public boolean actualizarSolicitud(SolicitudTransporteDTO solicitud) {
        for (int i = 0; i < solicitudes.size(); i++) {
            if (solicitudes.get(i).getFolio().equals(solicitud.getFolio())) {
                solicitudes.set(i, solicitud);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarSolicitud(String folio) {
        return solicitudes.removeIf(s -> s.getFolio().equals(folio));
    }

    public OrdenTransporteDTO registrarOrden(OrdenTransporteDTO orden) {
        ordenes.add(orden);
        return orden;
    }

    public OrdenTransporteDTO buscarOrdenPorFolio(String folio) {
        for (OrdenTransporteDTO o : ordenes) {
            if (o.getFolio().equals(folio)) {
                return o;
            }
        }
        return null;
    }

    public List<OrdenTransporteDTO> obtenerOrdenes() {
        return new ArrayList<>(ordenes);
    }

    public DecisionClienteDTO registrarDecision(DecisionClienteDTO decision) {
        decisiones.add(decision);
        return decision;
    }

    public List<DecisionClienteDTO> obtenerDecisiones() {
        return new ArrayList<>(decisiones);
    }

    public List<TipoUnidadDTO> obtenerTiposUnidad() {
        return new ArrayList<>(tiposUnidad);
    }

    public TipoUnidadDTO buscarTipoUnidadPorNombre(String nombre) {
        for (TipoUnidadDTO t : tiposUnidad) {
            if (t.getNombre().equalsIgnoreCase(nombre)) {
                return t;
            }
        }
        return null;
    }
}
