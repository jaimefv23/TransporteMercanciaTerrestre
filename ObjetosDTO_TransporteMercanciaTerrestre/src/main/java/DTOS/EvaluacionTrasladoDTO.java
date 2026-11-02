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
public class EvaluacionTrasladoDTO {
    
    private String idEvaluacion;
    private LocalDate fecha;
    private double capacidadMinima;
    private String condicionEspecial;
    private String observaciones;
    private String estado;

    public EvaluacionTrasladoDTO() {
    }

    public EvaluacionTrasladoDTO(String idEvaluacion, LocalDate fecha, double capacidadMinima, String condicionEspecial, String observaciones, String estado) {
        this.idEvaluacion = idEvaluacion;
        this.fecha = fecha;
        this.capacidadMinima = capacidadMinima;
        this.condicionEspecial = condicionEspecial;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public String getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(String idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCapacidadMinima() {
        return capacidadMinima;
    }

    public void setCapacidadMinima(double capacidadMinima) {
        this.capacidadMinima = capacidadMinima;
    }

    public String getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
