package views.core.panel.menu;


import views.core.panel.menu.partials.FileMenu;
import views.core.panel.menu.partials.MaintenanceMenu;
import views.core.panel.menu.partials.SaleMenu;
import views.documents.DocumentsView;
import views.person.PersonView;


import views.user.UserView;


import javax.swing.*;


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
