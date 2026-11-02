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
public class OrdenTransporteDTO {
    private String idOrdenTransporte;
    private String folio;
    private LocalDate fechaEmision;
    private String estado;
    private double importeAutorizado;
    private String condicionesAcordadas;

    public OrdenTransporteDTO() {
    }

    public OrdenTransporteDTO(String idOrdenTransporte, String folio, LocalDate fechaEmision, String estado, double importeAutorizado, String condicionesAcordadas) {
        this.idOrdenTransporte = idOrdenTransporte;
        this.folio = folio;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.importeAutorizado = importeAutorizado;
        this.condicionesAcordadas = condicionesAcordadas;
    }

    public String getIdOrdenTransporte() {
        return idOrdenTransporte;
    }

    public void setIdOrdenTransporte(String idOrdenTransporte) {
        this.idOrdenTransporte = idOrdenTransporte;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getImporteAutorizado() {
        return importeAutorizado;
    }

    public void setImporteAutorizado(double importeAutorizado) {
        this.importeAutorizado = importeAutorizado;
    }

    public String getCondicionesAcordadas() {
        return condicionesAcordadas;
    }

    public void setCondicionesAcordadas(String condicionesAcordadas) {
        this.condicionesAcordadas = condicionesAcordadas;
    }
    
    
}
