/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DTOS.OrdenTransporteDTO;

/**
 *
 * @author alex
 */
public interface IOrdenTransporteBO {
 
    public abstract String generarFolio();
 
    public abstract OrdenTransporteDTO guardar(OrdenTransporteDTO orden);
 
}
