/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import BOs.IEvaluacionTrasladoBO;
import DTOS.EvaluacionTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import BOs.FabricaBO;

/**
 *
 * @author tolan
 */
public class GestorEvaluacion {
    
    private IEvaluacionTrasladoBO evaluacionBO;

    public GestorEvaluacion() {
        this.evaluacionBO = FabricaBO.crearEvaluacionTrasladoBO();
    }

    public EvaluacionTrasladoDTO evaluarSolicitud(SolicitudTransporteDTO solicitud) {
        return evaluacionBO.evaluar(solicitud);
    }
}
