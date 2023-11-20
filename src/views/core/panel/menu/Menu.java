package views.core.panel.menu;

import views.category.JPCategoryView;
import views.core.panel.menu.partials.FileMenu;
import views.core.panel.menu.partials.MaintenanceMenu;
import views.core.panel.menu.partials.SaleMenu;
import views.documents.DocumentsView;
import views.person.PersonView;
import views.product.JPProductView;
import views.tax.JPTaxView;
import views.typeAffectation.JPTypeAffectationView;
import views.unity.JPUnityView;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {
    JFrame main;
    public Menu(JFrame main){
        this.main = main;
        createMenu();
    }
    public void createMenu(){
        add(new FileMenu("Archivo"));
        add(new SaleMenu("Ventas",main));
        add(new MaintenanceMenu("Mantenimiento",main));
    }

}
