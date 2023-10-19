package model.ventas;

import java.util.ArrayList;
import java.util.List;

public class GestionClientes {
    private List<Cliente> listaClientes;

    public GestionClientes() {
        this.listaClientes = new ArrayList<>();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        if (listaClientes != null) {
            this.listaClientes = listaClientes;
        } else {
            throw new IllegalArgumentException("La lista de clientes no puede ser nula.");
        }
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            listaClientes.add(cliente);
        } else {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
    }

    public Cliente buscarClientePorId(int clienteId) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getClienteId() == clienteId) {
                return cliente;
            }
        }
        return null;
    }

    // Métodos para editar y eliminar clientes
}
