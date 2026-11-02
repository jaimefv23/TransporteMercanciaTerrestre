/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.time.LocalDate;

/**
 *
 * @author Jaime
 */
public class PropuestaOperativaDTO {
    
    private String idPropuesta;
    private LocalDate fecha;
    private double distanciaEstimada;
    private String tiempoEstimado;
    private String estado;
    private TipoUnidadDTO tipoUnidadSugerida;
    private String rutaSeleccionada;
    private boolean requiereSeguroCarga;

    public PropuestaOperativaDTO() {
    }

    public PropuestaOperativaDTO(String idPropuesta, LocalDate fecha, double distanciaEstimada, String tiempoEstimado, String estado, TipoUnidadDTO tipoUnidadSugerida) {
        this.idPropuesta = idPropuesta;
        this.fecha = fecha;
        this.distanciaEstimada = distanciaEstimada;
        this.tiempoEstimado = tiempoEstimado;
        this.estado = estado;
        this.tipoUnidadSugerida = tipoUnidadSugerida;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getDistanciaEstimada() {
        return distanciaEstimada;
    }

    public void setDistanciaEstimada(double distanciaEstimada) {
        this.distanciaEstimada = distanciaEstimada;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoUnidadDTO getTipoUnidadSugerida() {
        return tipoUnidadSugerida;
    }

    public void setTipoUnidadSugerida(TipoUnidadDTO tipoUnidadSugerida) {
        this.tipoUnidadSugerida = tipoUnidadSugerida;
    }
    
    

    public String getRutaSeleccionada() {
        return rutaSeleccionada;
    }

    public void setRutaSeleccionada(String rutaSeleccionada) {
        this.rutaSeleccionada = rutaSeleccionada;
    }

    public boolean isRequiereSeguroCarga() {
        return requiereSeguroCarga;
    }

    public void setRequiereSeguroCarga(boolean requiereSeguroCarga) {
        this.requiereSeguroCarga = requiereSeguroCarga;
    }
}
