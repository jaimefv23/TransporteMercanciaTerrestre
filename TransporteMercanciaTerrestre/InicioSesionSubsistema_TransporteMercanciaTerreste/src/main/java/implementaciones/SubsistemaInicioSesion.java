/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.UsuarioDTO;
import interfaces.ISubsistemaInicioSesion;

/**
 *
 * @author tolan
 */
public class SubsistemaInicioSesion implements ISubsistemaInicioSesion {
    
    private ValidadorCredenciales validador;
    private GestorSesion gestorSesion;

    public SubsistemaInicioSesion() {
        this.validador = new ValidadorCredenciales();
        this.gestorSesion = new GestorSesion();
    }

    @Override
    public UsuarioDTO autenticarUsuario(String nombre, String contrasena) {
        UsuarioDTO usuario = validador.verificarCredenciales(nombre, contrasena);
        if (validador.usuarioActivo(usuario)) {
            gestorSesion.abrirSesion(usuario);
            return usuario;
        }
        return null;
    }

    @Override
    public boolean cerrarSesion(String idUsuario) {
        return gestorSesion.cerrarSesion(idUsuario);
    }

    @Override
    public boolean validarSesionActiva(String idUsuario) {
        return gestorSesion.existeSesion(idUsuario);
    }
}