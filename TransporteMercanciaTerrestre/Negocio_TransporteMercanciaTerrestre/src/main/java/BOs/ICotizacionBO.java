/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.CotizacionDTO;
import DTOS.PropuestaOperativaDTO;
import DTOS.SolicitudTransporteDTO;

/**
 *
 * @author Jaime
 */
public interface ICotizacionBO {
    
    public abstract CotizacionDTO generar(SolicitudTransporteDTO solicitud, PropuestaOperativaDTO propuesta);

}
