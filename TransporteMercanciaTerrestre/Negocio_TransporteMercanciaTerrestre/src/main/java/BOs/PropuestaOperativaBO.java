/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOS.PropuestaOperativaDTO;
import DTOS.TipoUnidadDTO;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author Jaime
 */
public class PropuestaOperativaBO implements IPropuestaOperativaBO {

   public static final String ESTADO_PROPUESTA = "PROPUESTA";

    // rutas disponibles (mismos datos que muestra la pantalla de estimacion de ruta)
    private static final double DISTANCIA_RUTA_A = 320.0;
    private static final String TIEMPO_RUTA_A = "4.5 Hrs";
    private static final double DISTANCIA_RUTA_B = 400.0;
    private static final String TIEMPO_RUTA_B = "6 Hrs";

    PropuestaOperativaBO() {
    }

    @Override
    public PropuestaOperativaDTO generar(String ruta, TipoUnidadDTO tipoUnidad, boolean requiereSeguroCarga) {
        if (tipoUnidad == null) {
            throw new IllegalArgumentException("Debe seleccionarse un tipo de unidad");
        }
        String rutaNormalizada = (ruta == null || ruta.isBlank()) ? "A" : ruta.trim().toUpperCase();

        double distancia;
        String tiempo;
        switch (rutaNormalizada) {
            case "A":
                distancia = DISTANCIA_RUTA_A;
                tiempo = TIEMPO_RUTA_A;
                break;
            case "B":
                distancia = DISTANCIA_RUTA_B;
                tiempo = TIEMPO_RUTA_B;
                break;
            default:
                throw new IllegalArgumentException("Ruta no reconocida: " + ruta);
        }

        PropuestaOperativaDTO propuesta = new PropuestaOperativaDTO();
        propuesta.setIdPropuesta("PROP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        propuesta.setFecha(LocalDate.now());
        propuesta.setRutaSeleccionada(rutaNormalizada);
        propuesta.setTipoUnidadSugerida(tipoUnidad);
        propuesta.setRequiereSeguroCarga(requiereSeguroCarga);
        propuesta.setDistanciaEstimada(distancia);
        propuesta.setTiempoEstimado(tiempo);
        propuesta.setEstado(ESTADO_PROPUESTA);
        return propuesta;
    }

    
}
