package model.ventas;

import java.util.ArrayList;
import java.util.List;

public class GestionProductos {
    private List<Producto> listaProductos;

    public GestionProductos() {
        this.listaProductos = new ArrayList<>();
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        if (listaProductos != null) {
            this.listaProductos = listaProductos;
        } else {
            throw new IllegalArgumentException("La lista de productos no puede ser nula.");
        }
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            listaProductos.add(producto);
        } else {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
    }

    public Producto buscarProductoPorId(int productoId) {
        for (Producto producto : listaProductos) {
            if (producto.getProductoId() == productoId) {
                return producto;
            }
        }
        return null;
    }

}
