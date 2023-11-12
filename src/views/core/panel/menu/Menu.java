package views.core.panel.menu;

import views.admin.EmployeeAdminView;
import views.category.CategoryView;
import views.person.PersonView;
import views.product.ProductView;
import views.tax.TaxView;
import views.typeAffectation.TypeAffectationView;
import views.unity.UnityView;

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

        JMenu userperson = new JMenu("Personas y Usuarios");
        maintenance.add(userperson);

        JMenuItem persona = new JMenuItem("Personas");
        userperson.add(persona);

        JMenuItem user = new JMenuItem("Usuarios");
        userperson.add(user);

        JMenu productPrice = new JMenu("Productos y Precios");
        maintenance.add(productPrice);

        JMenuItem categories = new JMenuItem("Categorias");
        productPrice.add(categories);

        JMenuItem productSunat = new JMenuItem("Código SUNAT");
        productPrice.add(productSunat);

        JMenuItem product = new JMenuItem("Productos");
        productPrice.add(product);

        /**/
        JMenuItem unity = new JMenuItem("Unidades de Medida");
        maintenance.add(unity);

        JMenuItem typeAffectation = new JMenuItem("Tip. Afectación");
        maintenance.add(typeAffectation);

        JMenu otros = new JMenu("Otros");
        maintenance.add(otros);

        JMenuItem movementType = new JMenuItem("Tipos de Movimientos");
        otros.add(movementType);

        JMenuItem tax = new JMenuItem("Impuestos");
        otros.add(tax);

        exitItem.addActionListener((e) -> {
            System.exit(0);
        });

        persona.addActionListener((e) -> {

            SwingUtilities.invokeLater(() -> {
                run(new PersonView(),"Administración de Personas",500, 576,false);
            });
        });



        categories.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new CategoryView(),"Administración de Categorías",500, 576,false);
            });
        });

        product.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new ProductView(),"Administración de Productos",768, 576,true);
            });
        });

        unity.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new UnityView(),"Administración de Unidades de Medida",500,576,false);
            });
        });

        typeAffectation.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new TypeAffectationView(),"Administración de tipos de afectación",500,576,false);
            });
        });

        tax.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new TaxView(),"Administración de impuestos",500,576,false);
            });
        });

    }

    public void run(JPanel panel,String title,int width,int height,boolean resizable ){
        JDialog dialog = new JDialog(this.main, title, true);
        dialog.setMinimumSize(new Dimension(width, height));
        dialog.setResizable(resizable);
        dialog.setLocationRelativeTo(this.main);
        dialog.add(panel);
        dialog.setVisible(true);
    }

}
