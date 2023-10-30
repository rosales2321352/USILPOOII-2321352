package views.core.panel.menu;

import views.product.ProductView;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {
    JFrame main;
    JMenu fileMenu;
    JMenu maintenance;
    public Menu(JFrame main){
        this.main = main;
        fileMenu = new JMenu("Archivo");
        maintenance = new JMenu("Mantenimiento");
        createItems();
        this.add(fileMenu);
        this.add(maintenance);
    }

    public void createItems(){
        JMenuItem exitItem = new JMenuItem("Salir");
        fileMenu.add(exitItem);

        JMenu productPrice = new JMenu("Productos y Precios");
        maintenance.add(productPrice);

        JMenuItem product = new JMenuItem("Productos");
        productPrice.add(product);

        exitItem.addActionListener((e) -> {
            System.exit(0);
        });

        product.addActionListener((e) -> {
            final JFrame parentFrame = this.main;
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
    }

}
