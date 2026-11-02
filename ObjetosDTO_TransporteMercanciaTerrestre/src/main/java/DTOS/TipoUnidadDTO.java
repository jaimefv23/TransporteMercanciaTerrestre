/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class TipoUnidadDTO {
    
    private String idTipoUnidad;
    private String nombre;
    private double capacidadCarga;
    private double capacidadVolumen;
    private String tipoCaja;
    private String condicionEspecial;

    public TipoUnidadDTO() {
    }

    public TipoUnidadDTO(String idTipoUnidad, String nombre, double capacidadCarga, double capacidadVolumen, String tipoCaja, String condicionEspecial) {
        this.idTipoUnidad = idTipoUnidad;
        this.nombre = nombre;
        this.capacidadCarga = capacidadCarga;
        this.capacidadVolumen = capacidadVolumen;
        this.tipoCaja = tipoCaja;
        this.condicionEspecial = condicionEspecial;
    }

    public String getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(String idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadVolumen() {
        return capacidadVolumen;
    }

    public void setCapacidadVolumen(double capacidadVolumen) {
        this.capacidadVolumen = capacidadVolumen;
    }

    public String getTipoCaja() {
        return tipoCaja;
    }

    public void setTipoCaja(String tipoCaja) {
        this.tipoCaja = tipoCaja;
    }

    public String getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }
    
    
}
