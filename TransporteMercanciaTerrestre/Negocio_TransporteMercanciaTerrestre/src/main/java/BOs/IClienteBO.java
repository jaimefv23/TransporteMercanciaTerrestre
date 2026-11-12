/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.ClienteDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public interface IClienteBO {
    
    public abstract List<ClienteDTO> consultarTodos();

    public abstract List<ClienteDTO> buscar(String texto);

    public abstract ClienteDTO guardar(ClienteDTO cliente);

    public abstract boolean actualizar(ClienteDTO cliente);

}
