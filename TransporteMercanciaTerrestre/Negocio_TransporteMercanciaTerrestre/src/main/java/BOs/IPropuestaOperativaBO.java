/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.PropuestaOperativaDTO;
import DTOS.TipoUnidadDTO;

/**
 *
 * @author Jaime
 */
public interface IPropuestaOperativaBO {
    
    public abstract PropuestaOperativaDTO generar(String ruta, TipoUnidadDTO tipoUnidad, boolean requiereSeguroCarga);

}
