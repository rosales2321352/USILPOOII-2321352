package views.core.panel.menu;

import views.category.CategoryView;
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

        JMenuItem categories = new JMenuItem("Categorias");
        productPrice.add(categories);

        JMenuItem productSunat = new JMenuItem("Código SUNAT");
        productPrice.add(productSunat);

        JMenuItem product = new JMenuItem("Productos");
        productPrice.add(product);

        exitItem.addActionListener((e) -> {
            System.exit(0);
        });

        categories.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new CategoryView(),"Administración de Categorías",400, 400);
            });
        });

        product.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new ProductView(),"Administración de Productos",768, 576);
            });
        });

    }

    public void run(JPanel panel,String title,int width,int height){
        JDialog dialog = new JDialog(this.main, title, true);
        dialog.setMinimumSize(new Dimension(width, height));
        dialog.setLocationRelativeTo(this.main);
        dialog.add(panel);
        dialog.setVisible(true);
    }

}
