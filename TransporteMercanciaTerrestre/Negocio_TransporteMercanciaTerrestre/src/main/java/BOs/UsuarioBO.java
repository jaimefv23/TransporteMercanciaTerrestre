/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.UsuarioDTO;

/**
 *
 * @author Jaime
 */
public class UsuarioBO implements IUsuarioBO {
    
     private final AlmacenTemporal almacen;

    UsuarioBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public UsuarioDTO consultarPorNombre(String nomUsuario) {
        if (nomUsuario == null || nomUsuario.isBlank()) {
            return null;
        }
        return almacen.buscarUsuarioPorNombre(nomUsuario);
    }
}
