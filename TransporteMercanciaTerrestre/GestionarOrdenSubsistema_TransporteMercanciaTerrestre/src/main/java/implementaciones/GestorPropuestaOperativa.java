/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import BOs.IPropuestaOperativaBO;
import BOs.ITipoUnidadBO;
import DTOS.PropuestaOperativaDTO;
import DTOS.RutaDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import BOs.FabricaBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tolan
 */
public class GestorPropuestaOperativa {
    
    private ITipoUnidadBO tipoUnidadBO;
    private IPropuestaOperativaBO propuestaBO;

    public GestorPropuestaOperativa() {
        this.tipoUnidadBO = FabricaBO.crearTipoUnidadBO();
        this.propuestaBO = FabricaBO.crearPropuestaOperativaBO();
    }

    public List<TipoUnidadDTO> consultarTiposUnidad() {
        return tipoUnidadBO.consultarTodos();
    }

    public TipoUnidadDTO consultarTipoUnidadPorNombre(String nombre) {
        return tipoUnidadBO.consultarPorNombre(nombre);
    }

    public PropuestaOperativaDTO generarPropuesta(String ruta, TipoUnidadDTO tipoUnidad, boolean requiereSeguroCarga) {
        return propuestaBO.generar(ruta, tipoUnidad, requiereSeguroCarga);
    }
}
