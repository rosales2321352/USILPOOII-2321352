/*package model.ventas;

import java.util.ArrayList;
import java.util.List;

public class GestionFacturas {
    private List<Factura> listaFacturas;

    public GestionFacturas() {
        this.listaFacturas = new ArrayList<>();
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        if (listaFacturas != null) {
            this.listaFacturas = listaFacturas;
        } else {
            throw new IllegalArgumentException("La lista de facturas no puede ser nula.");
        }
    }

    public void agregarFactura(Factura factura) {
        if (factura != null) {
            listaFacturas.add(factura);
        } else {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }
    }

    public Factura buscarFacturaPorId(int facturaId) {
        for (Factura factura : listaFacturas) {
            if (factura.getFacturaId() == facturaId) {
                return factura;
            }
        }
        return null;
    }

}

*/