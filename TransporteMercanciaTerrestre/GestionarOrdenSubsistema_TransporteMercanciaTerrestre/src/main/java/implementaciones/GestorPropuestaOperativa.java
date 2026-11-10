/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.PropuestaOperativaDTO;
import DTOS.RutaDTO;
import DTOS.SolicitudTransporteDTO;
import DTOS.TipoUnidadDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tolan
 */
public class GestorPropuestaOperativa {
    AlmacenTemporal almacen;
    
//    public List<RutaDTO> crearRutas(String folio){
//    }
//    
//    public PropuestaOperativaDTO seleccionarRuta(String folio, RutaDTO ruta){
//    }
    //No estoy seguro de cómo manejar rutas
    
    public List<TipoUnidadDTO> buscarUnidadesDisponibles(double volumenRequerido, String condicionEspecial){
        List<TipoUnidadDTO> todas = almacen.obtenerTiposUnidad();
        List<TipoUnidadDTO> apropiadas = new ArrayList<>();
        
        for(int i = 0;i<todas.size();i++){
            if(volumenRequerido <= todas.get(i).getCapacidadVolumen() && condicionEspecial.equals(todas.get(i).getCondicionEspecial())){
                apropiadas.add(todas.get(i));
            }
        }
        
        return apropiadas;
    }
    public PropuestaOperativaDTO seleccionarUnidad(String folio, TipoUnidadDTO tipoUnidad){
        PropuestaOperativaDTO propuesta = new PropuestaOperativaDTO();
        propuesta.setTipoUnidadSugerida(tipoUnidad);
        return propuesta;
    }
}
