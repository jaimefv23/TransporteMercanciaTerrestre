/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simuladores;

import DTOS.PropuestaOperativaDTO;
import DTOS.TipoUnidadDTO;
import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class SimuladorPropuesta {
    
    public PropuestaOperativaDTO simularPropuesta(String ruta, TipoUnidadDTO unidad, boolean requiereSeguro) {
        PropuestaOperativaDTO prop = new PropuestaOperativaDTO();
        prop.setIdPropuesta("PROP-" + (System.currentTimeMillis() % 1000));
        prop.setFecha(LocalDate.now());
        prop.setRutaSeleccionada(ruta != null ? ruta : "A");
        prop.setTipoUnidadSugerida(unidad);
        prop.setRequiereSeguroCarga(requiereSeguro);
        prop.setDistanciaEstimada(250.0);
        prop.setTiempoEstimado("4.5 Hrs Estimado");
        prop.setEstado("PROPUESTA");
        return prop;
    }

}
