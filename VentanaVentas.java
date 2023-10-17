import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaVentas extends JFrame {
    private double total = 0.0;
    private JTextArea textAreaFactura;
    private List<FacturaItem> facturaItems;

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
        JButton botonLimpiar = new JButton("Limpiar factura");

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
        panelBotonesSur.add(botonLimpiar);
        container.add(panelBotonesSur, BorderLayout.SOUTH);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String producto = textFieldProducto.getText();
                double precio = Double.parseDouble(textFieldPrecio.getText());
                FacturaItem item = new FacturaItem(producto, precio);
                total += precio;
                facturaItems.add(item);
                actualizarTextAreaFactura();
                textFieldProducto.setText("");
                textFieldPrecio.setText("");
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

        setVisible(true); // Mostrar la ventana
    }

    private void actualizarTextAreaFactura() {
        textAreaFactura.setText("");
        for (FacturaItem item : facturaItems) {
            textAreaFactura.append(item.getProducto() + ": $" + item.getPrecio() + "\n");
        }
        textAreaFactura.append("Total: $" + total + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaVentas ventana = new VentanaVentas();
            }
        });
    }
}
