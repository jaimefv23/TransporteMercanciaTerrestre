/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.TipoUnidadDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class TipoUnidadBO implements ITipoUnidadBO {
    
     private final AlmacenTemporal almacen;

    TipoUnidadBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public List<TipoUnidadDTO> consultarTodos() {
        return almacen.obtenerTiposUnidad();
    }

    @Override
    public TipoUnidadDTO consultarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return null;
        }
        return almacen.buscarTipoUnidadPorNombre(nombre.trim());
    }

}
