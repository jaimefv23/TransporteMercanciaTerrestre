/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class ServicioDTO {
    private String idServicio;
    private String nombre;
    private String descripcion;
    private double precioReferencia;

    public ServicioDTO() {
    }

    public ServicioDTO(String idServicio, String nombre, String descripcion, double precioReferencia) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioReferencia = precioReferencia;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioReferencia() {
        return precioReferencia;
    }

    public void setPrecioReferencia(double precioReferencia) {
        this.precioReferencia = precioReferencia;
    }
    
    
}
