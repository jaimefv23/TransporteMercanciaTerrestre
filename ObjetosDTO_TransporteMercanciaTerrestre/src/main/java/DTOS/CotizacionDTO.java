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
public class CotizacionDTO {
    private String idCotizacion;
    private LocalDate fechaEmision;
    private double subtotal;
    private double descuento;
    private double iva;
    private double total;
    private String estado;
    private List<DetalleCotizacionDTO> detalles = new ArrayList<>();

    public CotizacionDTO() {
    }

    public CotizacionDTO(String idCotizacion, LocalDate fechaEmision, double subtotal, double descuento, double iva, double total, String estado, List<DetalleCotizacionDTO> detalles) {
        this.idCotizacion = idCotizacion;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.iva = iva;
        this.total = total;
        this.estado = estado;
        this.detalles = detalles;
    }

    public String getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(String idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleCotizacionDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacionDTO> detalles) {
        this.detalles = detalles;
    }
    
    

    /**
     * Suma de los importes de los detalles de un tipo de concepto
     * (PRINCIPAL, ADICIONAL u OPCIONAL). Es solo una suma de presentación
     * sobre los datos del DTO; no calcula precios.
     */
    public double getTotalPorTipo(String tipoConcepto) {
        double suma = 0;
        if (detalles != null) {
            for (DetalleCotizacionDTO d : detalles) {
                if (tipoConcepto.equalsIgnoreCase(d.getTipoConcepto())) {
                    suma += d.getImporte();
                }
            }
        }
        return suma;
    }

    public List<DetalleCotizacionDTO> getDetallesPorTipo(String tipoConcepto) {
        List<DetalleCotizacionDTO> lista = new ArrayList<>();
        if (detalles != null) {
            for (DetalleCotizacionDTO d : detalles) {
                if (tipoConcepto.equalsIgnoreCase(d.getTipoConcepto())) {
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public boolean isAplicaDescuento() {
        return descuento > 0;
    }
}
