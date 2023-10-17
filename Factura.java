public class Factura {
    private int numeroFactura;
    private Cliente cliente;
    private List<FacturaItem> items;

    public Factura(int numeroFactura, Cliente cliente, List<FacturaItem> items) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.items = items;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<FacturaItem> getItems() {
        return items;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (FacturaItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
