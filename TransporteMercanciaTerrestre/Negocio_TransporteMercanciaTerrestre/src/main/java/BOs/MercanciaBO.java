/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.MercanciaDTO;

/**
 *
 * @author Jaime
 */
public class MercanciaBO implements IMercanciaBO {
    
     private final AlmacenTemporal almacen;

    MercanciaBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public MercanciaDTO agregar(MercanciaDTO mercancia) {
        if (mercancia == null) {
            throw new IllegalArgumentException("La mercancía no puede ser nula");
        }
        if (mercancia.getNombre() == null || mercancia.getNombre().isBlank()) {
            throw new IllegalArgumentException("El concepto de la mercancía es obligatorio");
        }
        if (mercancia.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (mercancia.getPeso() <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a cero");
        }
        if (mercancia.getLargo() <= 0 || mercancia.getAncho() <= 0 || mercancia.getAlto() <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a cero");
        }

        if (mercancia.getIdMercancia() == null || mercancia.getIdMercancia().isBlank()) {
            mercancia.setIdMercancia(almacen.generarIdMercancia());
        }
        if (mercancia.getCondicionesEsp() == null || mercancia.getCondicionesEsp().isBlank()) {
            mercancia.setCondicionesEsp("Ninguna");
        }
        if (mercancia.getInfoAdicional() == null || mercancia.getInfoAdicional().isBlank()) {
            mercancia.setInfoAdicional("N/A");
        }
        return mercancia;
    }

}
