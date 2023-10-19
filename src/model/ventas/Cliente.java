package model.ventas;

public class Cliente {
    private int clienteId;
    private String nombre;
    private String direccion;
    private String telefono;

    public Cliente(int clienteId, String nombre, String direccion, String telefono) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        if (clienteId > 0) {
            this.clienteId = clienteId;
        } else {
            throw new IllegalArgumentException("El ID de cliente debe ser un valor positivo.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion != null && !direccion.isEmpty()) {
            this.direccion = direccion;
        } else {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El número de teléfono no puede estar vacío.");
        }
    }

    // Otros métodos y lógica relacionados al cliente
}
