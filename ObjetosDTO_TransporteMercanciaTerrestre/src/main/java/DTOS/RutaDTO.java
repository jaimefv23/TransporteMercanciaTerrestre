/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class RutaDTO {
    private PuntoTrasladoDTO origen;
    private PuntoTrasladoDTO destino;
    private String tiempoEstimado;
    private double distancia;

    public RutaDTO() {
    }

    public RutaDTO(PuntoTrasladoDTO origen, PuntoTrasladoDTO destino, String tiempoEstimado, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.tiempoEstimado = tiempoEstimado;
        this.distancia = distancia;
    }

    public PuntoTrasladoDTO getOrigen() {
        return origen;
    }

    public void setOrigen(PuntoTrasladoDTO origen) {
        this.origen = origen;
    }

    public PuntoTrasladoDTO getDestino() {
        return destino;
    }

    public void setDestino(PuntoTrasladoDTO destino) {
        this.destino = destino;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
}
