public class GeneradorFacturas {
    private List<Factura> listaFacturas;
    private int numeroFacturaActual;

    public GeneradorFacturas() {
        listaFacturas = new ArrayList<>();
        numeroFacturaActual = 1;
    }

    public Factura crearFactura(Cliente cliente, List<FacturaItem> items) {
        Factura factura = new Factura(numeroFacturaActual++, cliente, items);
        listaFacturas.add(factura);
        return factura;
    }
}
