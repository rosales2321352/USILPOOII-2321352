public class GestionClientes {
    private List<Cliente> listaClientes;

    public GestionClientes() {
        listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void editarCliente(Cliente clienteExistente, Cliente nuevoCliente) {
        int index = listaClientes.indexOf(clienteExistente);
        if (index != -1) {
            listaClientes.set(index, nuevoCliente);
        }
    }

    public void eliminarCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }
}
