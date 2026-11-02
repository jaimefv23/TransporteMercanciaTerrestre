/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS;

/**
 *
 * @author Jaime
 */
public class PuntoTrasladoDTO {
    private String idPuntoTraslado;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String estado;
    private String infoAdicional;
    private String nomContacto;
    private String telContacto;
    private String ventanaHoraria;

    public PuntoTrasladoDTO() {
    }

    public PuntoTrasladoDTO(String idPuntoTraslado, String calle, String numero, String colonia, String codigoPostal, String estado, String ciudad, String infoAdicional, String nomContacto, String telContacto, String ventanaHoraria) {
        this.idPuntoTraslado = idPuntoTraslado;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.estado = estado;
        this.ciudad = ciudad;
        this.infoAdicional = infoAdicional;
        this.nomContacto = nomContacto;
        this.telContacto = telContacto;
        this.ventanaHoraria = ventanaHoraria;
    }

    public String getIdPuntoTraslado() {
        return idPuntoTraslado;
    }

    public void setIdPuntoTraslado(String idPuntoTraslado) {
        this.idPuntoTraslado = idPuntoTraslado;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    public String getNomContacto() {
        return nomContacto;
    }

    public void setNomContacto(String nomContacto) {
        this.nomContacto = nomContacto;
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }

    public String getVentanaHoraria() {
        return ventanaHoraria;
    }

    public void setVentanaHoraria(String ventanaHoraria) {
        this.ventanaHoraria = ventanaHoraria;
    }
    
    
}
