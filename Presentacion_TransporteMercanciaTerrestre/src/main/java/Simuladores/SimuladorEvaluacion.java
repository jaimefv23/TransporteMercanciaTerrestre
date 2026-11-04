/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simuladores;

import DTOS.EvaluacionTrasladoDTO;
import DTOS.SolicitudTransporteDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class SimuladorEvaluacion {
    public EvaluacionTrasladoDTO simularEvaluacion(SolicitudTransporteDTO solicitud) {
        
        EvaluacionTrasladoDTO eval = new EvaluacionTrasladoDTO();
        eval.setIdEvaluacion("EV-" + (solicitud != null && solicitud.getFolio() != null ? solicitud.getFolio() : "001"));
        eval.setFecha(LocalDate.now());
        eval.setCapacidadMinima(1000.0);
        eval.setCondicionEspecial("Control de temperatura / Frágil");
        eval.setObservaciones("Evaluación preliminar favorable.");
        eval.setEstado("VIABLE");
        return eval;
    }

}
