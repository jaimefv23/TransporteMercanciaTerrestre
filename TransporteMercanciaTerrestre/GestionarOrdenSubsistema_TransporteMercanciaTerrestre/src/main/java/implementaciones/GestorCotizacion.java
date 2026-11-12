/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import BOs.ICotizacionBO;
import DTOS.CotizacionDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;
import BOs.FabricaBO;

/**
 *
 * @author tolan
 */
public class GestorCotizacion {
    
     private ICotizacionBO cotizacionBO;

    public GestorCotizacion() {
        this.cotizacionBO = FabricaBO.crearCotizacionBO();
    }

    public CotizacionDTO generarCotizacion(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta) {
        return cotizacionBO.generar(solicitud, propuesta);
    }
}
