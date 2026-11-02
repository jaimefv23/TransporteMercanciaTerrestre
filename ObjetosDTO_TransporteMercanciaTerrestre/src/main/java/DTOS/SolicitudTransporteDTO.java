/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class SolicitudTransporteDTO {
    private String folio;
    private LocalDate fechaCarga;
    private LocalDate fechaEntrega;
    private String condiciones;
    private String observaciones;
    private String estado;
    private String motivoEstado;
    
    private ClienteDTO cliente;
    private PuntoTrasladoDTO origen;
    private PuntoTrasladoDTO destino;
    private List<MercanciaDTO> mercancias = new ArrayList<>();

    public SolicitudTransporteDTO() {
    }

    public SolicitudTransporteDTO(String folio, LocalDate fechaCarga, LocalDate fechaEntrega, String condiciones, String observaciones, String estado, String motivoEstado, ClienteDTO cliente, PuntoTrasladoDTO origen, PuntoTrasladoDTO destino) {
        this.folio = folio;
        this.fechaCarga = fechaCarga;
        this.fechaEntrega = fechaEntrega;
        this.condiciones = condiciones;
        this.observaciones = observaciones;
        this.estado = estado;
        this.motivoEstado = motivoEstado;
        this.cliente = cliente;
        this.origen = origen;
        this.destino = destino;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDate fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
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

    public String getMotivoEstado() {
        return motivoEstado;
    }

    public void setMotivoEstado(String motivoEstado) {
        this.motivoEstado = motivoEstado;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
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

    public List<MercanciaDTO> getMercancias() {
        return mercancias;
    }

    public void setMercancias(List<MercanciaDTO> mercancias) {
        this.mercancias = mercancias;
    }
    
    
}
