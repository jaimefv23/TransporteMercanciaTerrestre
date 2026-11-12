/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs;

import DTOS.UsuarioDTO;

/**
 *
 * @author Jaime
 */
public interface IUsuarioBO {
    
    public abstract UsuarioDTO consultarPorNombre(String nomUsuario);

}
