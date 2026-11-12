/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.EvaluacionTrasladoDTO;
import DTOS.SolicitudTransporteDTO;

/**
 *
 * @author Jaime
 */
public interface IEvaluacionTrasladoBO {
    
    public abstract EvaluacionTrasladoDTO evaluar(SolicitudTransporteDTO solicitud);

}
