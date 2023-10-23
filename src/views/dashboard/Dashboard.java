package views.dashboard;

import views.product.ProductView;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    JPanel panel3;
    public Dashboard(){
        SwingUtilities.invokeLater(() -> {

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(768, 576);
            //this.setMinimumSize(new Dimension(768,576));
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(screenSize);
            this.setLocation(0, 0);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //this.setUndecorated(true);

            this.setResizable(true);
            this.setTitle("Sistema de Ventas");

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel3 = new JPanel();



            this.add(panel1,BorderLayout.PAGE_START);
            this.add(panel2,BorderLayout.LINE_START);
            this.add(panel3,BorderLayout.CENTER);
            //this.add(panel);
            this.setLocationRelativeTo(null);
            this.setVisible(true);

            JMenuBar menuBar = getjMenuBar();

            // Asignar la barra de menú al JFrame
            this.setJMenuBar(menuBar);

            this.setVisible(true);

        });
    }

    private JMenuBar getjMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // Crear un menú
        JMenu fileMenu = new JMenu("Archivo");
        // Crear elementos del menú
        JMenuItem newItem = new JMenuItem("Nuevo");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Guardar");
        JMenuItem exitItem = new JMenuItem("Salir");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Separador
        fileMenu.add(exitItem);

        newItem.addActionListener((e) -> {
            final JFrame parentFrame = this;
            SwingUtilities.invokeLater(() -> {
                /*ProductPanel panel3 = new ProductPanel();*/
                ProductView productView = new ProductView();
                JDialog dialog = new JDialog(parentFrame, "Administración de Productos", true); // El "true" indica que es modal
                dialog.setMinimumSize(new Dimension(768, 576));
                dialog.setLocationRelativeTo(parentFrame);
                dialog.add(productView);
                dialog.setVisible(true);
            });
        });
        // Agregar acciones a los elementos del menú
        exitItem.addActionListener((e) -> {
            System.exit(0); // Salir de la aplicación
        });

        // Agregar el menú "Archivo" a la barra de menú
        menuBar.add(fileMenu);
        return menuBar;
    }


}
