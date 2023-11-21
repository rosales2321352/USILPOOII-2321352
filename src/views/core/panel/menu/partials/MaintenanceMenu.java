package views.core.panel.menu.partials;


import views.Currency.CurrencyView;
import views.CurrencyConversion.CurrencyConversionView;

import views.category.JPCategoryView;
import views.core.panel.menu.CustomMenu;
import views.core.panel.menu.CustomMenuItem;
import views.customer.JPCustomerView;
import views.documents.DocumentsView;
import views.person.PersonView;
import views.product.JPProductView;
import views.typeAffectation.JPTypeAffectationView;
import views.unity.JPUnityView;

import javax.swing.*;


public class MaintenanceMenu extends CustomMenu {
    private JMenu personUserMenu;
    private JMenuItem documentTypeMenuItem;
    private JMenuItem personMenuItem;
    private JMenuItem customerMenuItem;

    private JMenu productPriceMenuItem;
    private JMenuItem categoriesMenuItem;
    private JMenuItem productSunatMenuItem;
    private JMenuItem productMenuItem;

    private JMenuItem userMenuItem;
    private JMenuItem unityMenuItem;
    private JMenuItem typeAffectationMenuItem;
    private JMenu othersMenuItem;
    private JMenuItem movementTypeMenuItem;
    private JMenuItem taxMenuItem;
     private JMenuItem currencyMenuItem;
    private JMenuItem currencyConversionMenuItem;

    public MaintenanceMenu(String title, JFrame main) {
        super(title);
        this.main = main;
        makeSubMenu();
        makeMenuItem();
        makeActions();
    }
    public void makeSubMenu(){
        personUserMenu = new CustomMenu("Personas y Usuarios");
        add(personUserMenu);
        productPriceMenuItem = new CustomMenu("Productos y Precios");
        add(productPriceMenuItem);
        othersMenuItem = new JMenu("Otros");
        add(othersMenuItem);
    }
    public void makeMenuItem(){
        documentTypeMenuItem = new CustomMenuItem("Tipo documentos indentidad");
        personUserMenu.add(documentTypeMenuItem);

        customerMenuItem = new CustomMenuItem("Clientes");
        personUserMenu.add(customerMenuItem);

        personMenuItem = new CustomMenuItem("Personas");
        personUserMenu.add(personMenuItem);

        userMenuItem = new CustomMenuItem("Usuarios");
        personUserMenu.add(userMenuItem);

        unityMenuItem = new CustomMenuItem("Unidades de Medida");
        add(unityMenuItem);

        typeAffectationMenuItem = new CustomMenuItem("Tip. Afectación");
        add(typeAffectationMenuItem);

        categoriesMenuItem = new CustomMenuItem("Categorias");
        productPriceMenuItem.add(categoriesMenuItem);

        productSunatMenuItem = new CustomMenuItem("Código SUNAT");
        productPriceMenuItem.add(productSunatMenuItem);

        productMenuItem = new CustomMenuItem("Productos");
        productPriceMenuItem.add(productMenuItem);

        movementTypeMenuItem = new CustomMenuItem("Tipos de Movimientos");
        othersMenuItem.add(movementTypeMenuItem);

        taxMenuItem = new CustomMenuItem("Impuestos");
        othersMenuItem.add(taxMenuItem);

        currencyMenuItem = new CustomMenuItem("Divisas");
        othersMenuItem.add(currencyMenuItem);

        currencyConversionMenuItem = new CustomMenuItem("Tipo de cambio");
        othersMenuItem.add(currencyConversionMenuItem);
    }

    public void makeActions(){
        documentTypeMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new DocumentsView(), "Tipo de Documentos", 500, 576, false);
            });
        });

        customerMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPCustomerView(),"Administración de Clientes",770, 576,false);
            });
        });

        personMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new PersonView(),"Administración de Personas",900, 676,false);
            });
        });

        categoriesMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPCategoryView(),"Administración de Categorías",500, 576,false);
            });
        });

        productMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPProductView(),"Administración de Productos",770, 576,true);
            });
        });

        unityMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPUnityView(),"Administración de Unidades de Medida",500,576,false);
            });
        });

        typeAffectationMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPTypeAffectationView(),"Administración de tipos de afectación",500,576,false);
            });
        });

        currencyMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new CurrencyView(),"Monedas",500, 576,false);
            });
        });

        currencyConversionMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new CurrencyConversionView(),"Tipo de Cambio",500, 576,false);
            });
        });

    }
}
