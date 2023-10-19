package model.ventas;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int facturaId;
    private Cliente cliente;
    private List<FacturaItem> items;

    public Factura(int facturaId, Cliente cliente) {
        this.facturaId = facturaId;
        this.cliente = cliente;
        this.items = new ArrayList<>();
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        if (facturaId > 0) {
            this.facturaId = facturaId;
        } else {
            throw new IllegalArgumentException("El ID de factura debe ser un valor positivo.");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
    }

    public List<FacturaItem> getItems() {
        return items;
    }

    public void setItems(List<FacturaItem> items) {
        if (items != null) {
            this.items = items;
        } else {
            throw new IllegalArgumentException("La lista de ítems no puede ser nula.");
        }
    }

    public void agregarItem(FacturaItem item) {
        if (item != null) {
            items.add(item);
        } else {
            throw new IllegalArgumentException("El ítem no puede ser nulo.");
        }
    }

    public double calcularTotal() {
        double total = 0.0;
        for (FacturaItem item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

}
