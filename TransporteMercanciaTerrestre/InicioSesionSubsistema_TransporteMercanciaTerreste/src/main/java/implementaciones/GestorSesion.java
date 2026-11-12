/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.UsuarioDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tolan
 */
public class GestorSesion {
    
    private final Map<String, UsuarioDTO> sesionesActivas;

    public GestorSesion() {
        this.sesionesActivas = new HashMap<>();
    }

    public void abrirSesion(UsuarioDTO usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            throw new IllegalArgumentException("Usuario o id de usuario nulo");
        }
        sesionesActivas.put(usuario.getIdUsuario(), usuario);
    }

    public boolean cerrarSesion(String idUsuario) {
        return sesionesActivas.remove(idUsuario) != null;
    }

    public boolean existeSesion(String idUsuario) {
        return idUsuario != null && sesionesActivas.containsKey(idUsuario);
    }
}
