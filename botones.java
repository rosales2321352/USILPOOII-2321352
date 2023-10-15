import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaVentas extends JFrame {

    private double total = 0.0;
    private JTextArea textAreaFactura;
    private Connection conn;
    private List<String> facturaItems;

    public VentanaVentas() {
        setTitle("Sistema de Ventas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelProducto = new JLabel("Producto:");
        JTextField textFieldProducto = new JTextField(20);

        JLabel labelPrecio = new JLabel("Precio:");
        JTextField textFieldPrecio = new JTextField(10);

        JButton botonAgregar = new JButton("Agregar a la factura");
        textAreaFactura = new JTextArea(20, 50);
        JButton botonGuardar = new JButton("Guardar en la base de datos");
        JButton botonLimpiar = new JButton("Limpiar factura");

        JPanel panelBotones = new JPanel(new GridLayout(4, 3));
        JButton btnCotizaciones = new JButton("Cotizaciones");
        JButton btnComprobantes = new JButton("Comprobantes");
        JButton btnNotasSalida = new JButton("Notas de Salida");
        JButton btnResumenes = new JButton("Resúmenes");
        JButton btnGuiasRemision = aJButton("Guías de Remisión");
        JButton btnProductos = new JButton("Productos");
        JButton btnClientesProveedores = new JButton("Clientes y Proveedores");
        JButton btnCompras = new JButton("Compras");
        JButton btnAnulaciones = new JButton("Anulaciones");
        JButton btnLocalesSeries = new JButton("Locales y Series");
        JButton btnConfiguracion = new JButton("Configuración Principal");

        facturaItems = new ArrayList<>();

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(labelProducto, BorderLayout.NORTH);
        container.add(textFieldProducto, BorderLayout.NORTH);
        container.add(labelPrecio, BorderLayout.NORTH);
        container.add(textFieldPrecio, BorderLayout.NORTH);
        container.add(botonAgregar, BorderLayout.NORTH);
        container.add(new JScrollPane(textAreaFactura), BorderLayout.CENTER);

        JPanel panelBotonesSur = new JPanel();
        panelBotonesSur.add(botonGuardar);
        panelBotonesSur.add(botonLimpiar);
        container.add(panelBotonesSur, BorderLayout.SOUTH);
        container.add(panelBotones, BorderLayout.EAST);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String producto = textFieldProducto.getText();
                double precio = Double.parseDouble(textFieldPrecio.getText());
                total += precio;
                facturaItems.add(producto + ": $" + precio);
                actualizarTextAreaFactura();
                textFieldProducto.setText("");
                textFieldPrecio.setText("");
            }
        });

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarFacturaEnBaseDeDatos();
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facturaItems.clear();
                total = 0.0;
                actualizarTextAreaFactura();
            }
        });

        conectarABaseDeDatos();
    }

    private void actualizarTextAreaFactura() {
        textAreaFactura.setText("");
        for (String item : facturaItems) {
            textAreaFactura.append(item + "\n");
        }
        textAreaFactura.append("Total: $" + total + "\n");
    }

    private void conectarABaseDeDatos() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:ventas.db");
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    private void guardarFacturaEnBaseDeDatos() {
        try {
            if (conn == null) {
                conectarABaseDeDatos();
            }

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO facturas (total) VALUES (?)");
            pstmt.setDouble(1, total);
            pstmt.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Factura guardada en la base de datos.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura en la base de datos: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaVentas ventana = new VentanaVentas();
                ventana.setVisible(true);
            }
        });
    }
}
