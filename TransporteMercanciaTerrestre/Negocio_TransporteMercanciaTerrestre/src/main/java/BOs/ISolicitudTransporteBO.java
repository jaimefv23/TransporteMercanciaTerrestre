/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.SolicitudTransporteDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ISolicitudTransporteBO {
    
    public abstract String generarFolio();

    public abstract SolicitudTransporteDTO guardar(SolicitudTransporteDTO solicitud);

    public abstract List<SolicitudTransporteDTO> consultarPorCliente(String idCliente);

}
