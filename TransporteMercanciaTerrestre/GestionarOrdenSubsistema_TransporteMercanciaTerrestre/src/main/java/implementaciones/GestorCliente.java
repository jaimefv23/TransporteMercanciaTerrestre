/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Almacen.AlmacenTemporal;
import DTOS.ClienteDTO;

/**
 *
 * @author tolan
 */
public class GestorCliente {
    AlmacenTemporal almacen; //Mientras implementamos los BO
    
    public GestorCliente() {
    }
    
    public GestorCliente(AlmacenTemporal almacen) {
        this.almacen = almacen;
    }

    public ClienteDTO buscarPorCorreo(String correo){
        ClienteDTO cliente = almacen.buscarClientePorCorreo(correo);
        if(validarEstado(cliente.getIdCliente())){
            return cliente;
        }
        return null;
    }
    public boolean validarEstado(String idCliente){
        return almacen.buscarClientePorId(idCliente).isEstado();
    }
}
