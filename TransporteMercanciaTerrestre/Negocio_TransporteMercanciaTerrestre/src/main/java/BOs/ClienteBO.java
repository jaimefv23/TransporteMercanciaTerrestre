/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import Almacen.AlmacenTemporal;
import DTOS.ClienteDTO;
import java.util.List;

/**
 *
 * @author Jaime
 */
public class ClienteBO implements IClienteBO{

    private final AlmacenTemporal almacen;

    ClienteBO() {
        this.almacen = AlmacenTemporal.getInstancia();
    }

    @Override
    public List<ClienteDTO> consultarTodos() {
        return almacen.obtenerClientes();
    }

    @Override
    public List<ClienteDTO> buscar(String texto) {
        return almacen.buscarClientes(texto);
    }

    @Override
    public ClienteDTO guardar(ClienteDTO cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        
        // el almacen asigna el idCliente si viene vacío
        return almacen.registrarCliente(cliente);
    }

    @Override
    public boolean actualizar(ClienteDTO cliente) {
        if (cliente == null || cliente.getIdCliente() == null || cliente.getIdCliente().isBlank()) {
            throw new IllegalArgumentException("El cliente a actualizar debe tener idCliente");
        }
        return almacen.actualizarCliente(cliente);
    }

    
}
