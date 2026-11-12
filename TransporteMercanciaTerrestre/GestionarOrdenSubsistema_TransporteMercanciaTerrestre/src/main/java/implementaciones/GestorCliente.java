/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;
import BOs.IClienteBO;
import DTOS.ClienteDTO;
import BOs.FabricaBO;
import java.util.List;

/**
 *
 * @author tolan
 */
public class GestorCliente {
    
    private IClienteBO clienteBO;

    public GestorCliente() {
        this.clienteBO = FabricaBO.crearClienteBO();
    }

    public List<ClienteDTO> consultarClientes() {
        return clienteBO.consultarTodos();
    }

    public List<ClienteDTO> buscarClientes(String texto) {
        return clienteBO.buscar(texto);
    }

    public ClienteDTO registrarCliente(ClienteDTO cliente) {
        return clienteBO.guardar(cliente);
    }

    public boolean actualizarCliente(ClienteDTO cliente) {
        return clienteBO.actualizar(cliente);
    }
}
