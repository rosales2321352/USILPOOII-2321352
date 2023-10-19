package model.ventas;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraImpuestos {
    private List<Impuesto> listaImpuestos;

    public CalculadoraImpuestos() {
        this.listaImpuestos = new ArrayList<>();
    }

    public List<Impuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<Impuesto> listaImpuestos) {
        if (listaImpuestos != null) {
            this.listaImpuestos = listaImpuestos;
        } else {
            throw new IllegalArgumentException("La lista de impuestos no puede ser nula.");
        }
    }

    public void agregarImpuesto(Impuesto impuesto) {
        if (impuesto != null) {
            listaImpuestos.add(impuesto);
        } else {
            throw new IllegalArgumentException("El impuesto no puede ser nulo.");
        }
    }

    public double calcularImpuestos(Factura factura) {
        double impuestos = 0.0;
        for (Impuesto impuesto : listaImpuestos) {
            impuestos += impuesto.calcularImpuesto(factura);
        }
        return impuestos;
    }

    // Otros métodos relacionados al cálculo de impuestos
}
