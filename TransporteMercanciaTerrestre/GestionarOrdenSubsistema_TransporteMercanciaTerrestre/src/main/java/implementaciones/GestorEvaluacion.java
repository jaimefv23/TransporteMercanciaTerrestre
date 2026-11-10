/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.EvaluacionTrasladoDTO;
import DTOS.SolicitudTransporteDTO;

/**
 *
 * @author tolan
 */
public class GestorEvaluacion {
    AlmacenTemporal almacen;
    
    public EvaluacionTrasladoDTO evaluarViabilidad(String folio, EvaluacionTrasladoDTO evaluacion){
        SolicitudTransporteDTO solicitud = almacen.buscarSolicitudPorFolio(folio);
        evaluacion = new EvaluacionTrasladoDTO();

        return evaluacion;
    }
    
    public boolean esViable(EvaluacionTrasladoDTO evaluacion){
        if(evaluacion.getEstado()=="APROBADA"){
            return true;
        }
        return false;
    }
}
