/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class MercanciaDTO {
    
    private String idMercancia;
    private String nombre;
    private int cantidad;
    private String tipoEmpaque;
    private double peso;
    private double alto;
    private double largo;
    private double ancho;
    private String infoAdicional;
    private String condicionesEsp;

    public MercanciaDTO() {
    }

    public MercanciaDTO(String idMercancia, String nombre, int cantidad, String tipoEmpaque, double peso, double alto, double largo, double ancho, String infoAdicional, String condicionesEsp) {
        this.idMercancia = idMercancia;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoEmpaque = tipoEmpaque;
        this.peso = peso;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.infoAdicional = infoAdicional;
        this.condicionesEsp = condicionesEsp;
    }

    public String getIdMercancia() {
        return idMercancia;
    }

    public void setIdMercancia(String idMercancia) {
        this.idMercancia = idMercancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    public String getCondicionesEsp() {
        return condicionesEsp;
    }

    public void setCondicionesEsp(String condicionesEsp) {
        this.condicionesEsp = condicionesEsp;
    }
    
    
}
