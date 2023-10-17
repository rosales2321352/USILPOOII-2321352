public class GestionProductos {
    private List<Producto> listaProductos;

    public GestionProductos() {
        listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void editarProducto(Producto productoExistente, Producto nuevoProducto) {
        int index = listaProductos.indexOf(productoExistente);
        if (index != -1) {
            listaProductos.set(index, nuevoProducto);
        }
    }

    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }
}
