/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import DTOS.UsuarioDTO;

/**
 *
 * @author tolan
 */
public interface ISubsistemaInicioSesion {
    public UsuarioDTO autenticarUsuario(String nombre, String contrasena);
    
    public boolean cerrarSesion(String idUsuario);
    
    public boolean validarSesionActiva(String idUsuario);
}
