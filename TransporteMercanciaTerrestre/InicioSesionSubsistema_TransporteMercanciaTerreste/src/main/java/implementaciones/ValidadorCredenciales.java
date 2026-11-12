/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import BOs.IUsuarioBO;
import DTOS.UsuarioDTO;
import BOs.FabricaBO;

/**
 *
 * @author tolan
 */
public class ValidadorCredenciales {
    
    private IUsuarioBO usuarioBO;

    public ValidadorCredenciales() {
        this.usuarioBO = FabricaBO.crearUsuarioBO();
    }

    public UsuarioDTO verificarCredenciales(String nombre, String contrasenia) {
        if (nombre == null || nombre.isBlank() || contrasenia == null || contrasenia.isEmpty()) {
            return null;
        }

        UsuarioDTO usuario = usuarioBO.consultarPorNombre(nombre);
        if (usuario == null) {
            return null;
        }
        if (!usuario.getContrasenia().equals(contrasenia)) {
            return null;
        }

        // se regresa una copia sin la contraseña
        return new UsuarioDTO(usuario.getIdUsuario(), usuario.getNombre(), null);
    }

    public boolean usuarioActivo(UsuarioDTO usuario) {
        return usuario != null;
    }
}