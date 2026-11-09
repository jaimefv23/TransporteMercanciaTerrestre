/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import DTOS.UsuarioDTO;
import java.util.Map;

/**
 *
 * @author tolan
 */
public class GestorSesion {
    private Map<String, UsuarioDTO> sesionesActivas;
    
    public void abrirSesion(UsuarioDTO usuario){
        sesionesActivas.put(usuario.getIdUsuario(), usuario);
    }
    
    public boolean cerrarSesion(String idUsuario){
        if(existeSesion(idUsuario)){
            sesionesActivas.remove(idUsuario);
            return true;
        }
        return false;
    }
    
    public boolean existeSesion(String idUsuario){
        return sesionesActivas.containsKey(idUsuario);
    }
}
