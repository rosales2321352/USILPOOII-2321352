public class CalculadoraImpuestos {
    private List<Impuesto> listaImpuestos;

    public CalculadoraImpuestos() {
        listaImpuestos = new ArrayList<>();
    }

    public double calcularImpuestos(Factura factura) {
        double impuestos = 0.0;
        for (Impuesto impuesto : listaImpuestos) {
            impuestos += factura.getTotal() * impuesto.getTasa();
        }
        return impuestos;
    }
}
