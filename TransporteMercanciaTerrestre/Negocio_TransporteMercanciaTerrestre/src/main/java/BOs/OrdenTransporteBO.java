/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.OrdenTransporteDTO;

/**
 *
 * @author xRaid
 */
public class OrdenTransporteBO implements IOrdenTransporteBO {
 
    private final AlmacenTemporal almacen;
 
    OrdenTransporteBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }
 
    @Override
    public String generarFolio() {
        return almacen.generarFolioOrden();
    }
 
    @Override
    public OrdenTransporteDTO guardar(OrdenTransporteDTO orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser nula");
        }
        return almacen.registrarOrden(orden);
    }
}
