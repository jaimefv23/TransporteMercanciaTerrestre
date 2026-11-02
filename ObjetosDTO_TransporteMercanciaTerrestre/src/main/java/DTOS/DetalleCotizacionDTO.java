/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class DetalleCotizacionDTO {
    
    private String idDetalle;
    private String descripcion;
    private String tipoConcepto;
    private double precioUnitario;
    private double importe;

    public DetalleCotizacionDTO() {
    }

    public DetalleCotizacionDTO(String idDetalle, String descripcion, String tipoConcepto, double precioUnitario, double importe) {
        this.idDetalle = idDetalle;
        this.descripcion = descripcion;
        this.tipoConcepto = tipoConcepto;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    
}
