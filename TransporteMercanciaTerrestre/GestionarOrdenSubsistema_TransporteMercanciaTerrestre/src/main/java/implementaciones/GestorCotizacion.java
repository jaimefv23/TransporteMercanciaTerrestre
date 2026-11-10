/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.CotizacionDTO;
import DTOS.DetalleCotizacionDTO;
import DTOS.RutaDTO;
import DTOS.ServicioDTO;
import DTOS.TipoUnidadDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tolan
 */
public class GestorCotizacion {
    private AlmacenTemporal almacen;
    
    public GestorCotizacion(AlmacenTemporal almacen){
        this.almacen = almacen;
    }
    
//    public List<ServicioDTO> obtenerServicios(TipoUnidadDTO tipoUnidad, RutaDTO ruta){
//        //List<ServicioDTO> disponibles = 
//        List<ServicioDTO> resultado = new ArrayList<>();
//        for (ServicioDTO s : disponibles) {
//            if (servicios == null || !servicios.contains(s)) {
//                resultado.add(s);
//            }
//        }
//        return resultado;
//    } 
    //Aún sin tratamiento de servicios
//    public DetalleCotizacionDTO generarDetalle(ServicioDTO servicio, String caracterServicio, boolean esIndispensable){
//        
//    }
//    public CotizacionDTO crearCotizacion(String folio, List<DetalleCotizacionDTO> detalles){
//        
//    }
    //Esperamos a servicios
}
