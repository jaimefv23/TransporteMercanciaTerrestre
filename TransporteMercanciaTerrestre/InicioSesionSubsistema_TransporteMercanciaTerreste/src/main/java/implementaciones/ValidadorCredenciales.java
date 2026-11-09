/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.UsuarioDTO;

/**
 *
 * @author tolan
 */
public class ValidadorCredenciales {
    private AlmacenTemporal almacen; //También mientras implementamos los BO y la capa de datos
    
    public ValidadorCredenciales() {
    }

    public UsuarioDTO verificarCredenciales(String nombre, String contrasenia) {
        if (nombre == null || nombre.isBlank() || contrasenia == null || contrasenia.isEmpty()) {
            return null;
        }

        UsuarioDTO usuario = almacen.buscarUsuarioPorNombre(nombre.trim().toLowerCase());
        if (usuario == null) {
            return null;
        }

        if (!usuario.getContrasenia().equals(contrasenia)) {
            return null;
        }

        return new UsuarioDTO(usuario.getIdUsuario(), usuario.getNombre(), usuario.getNombre(), usuario.isEstado());
    }

    public boolean usuarioActivo(UsuarioDTO usuario) {
        return usuario != null && usuario.isEstado();
    }
}