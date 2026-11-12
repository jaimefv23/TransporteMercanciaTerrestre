/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.TipoUnidadDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface ITipoUnidadBO {
    
    public abstract List<TipoUnidadDTO> consultarTodos();

    public abstract TipoUnidadDTO consultarPorNombre(String nombre);

}
