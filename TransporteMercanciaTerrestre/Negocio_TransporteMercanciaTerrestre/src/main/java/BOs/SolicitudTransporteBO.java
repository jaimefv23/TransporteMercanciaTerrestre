/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.SolicitudTransporteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class SolicitudTransporteBO implements ISolicitudTransporteBO {

     private final AlmacenTemporal almacen;

    SolicitudTransporteBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public String generarFolio() {
        return almacen.generarFolioSolicitud();
    }

    @Override
    public SolicitudTransporteDTO guardar(SolicitudTransporteDTO solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        // si no trae folio el almacen lo genera, si ya existe, lo reemplaza
        return almacen.guardarSolicitud(solicitud);
    }

    @Override
    public List<SolicitudTransporteDTO> consultarPorCliente(String idCliente) {
        if (idCliente == null || idCliente.isBlank()) {
            return new ArrayList<>();
        }
        return almacen.obtenerSolicitudesPorCliente(idCliente);
    }

}
