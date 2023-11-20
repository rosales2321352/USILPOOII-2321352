package views.core.panel.menu.partials;

import views.billing.JPBillingView;
import views.core.panel.menu.CustomMenu;
import views.core.panel.menu.CustomMenuItem;

import javax.swing.*;

public class SaleMenu extends CustomMenu {

    private JMenuItem billingMenuItem;
    private JFrame main;
    public SaleMenu(String title, JFrame main){
        super(title);
        this.main = main;
        makeSubMenu();
        makeMenuItem();
        makeActions();
    }

    private void makeSubMenu(){

    }
    private void makeMenuItem(){
        billingMenuItem = new CustomMenuItem("Facturación - Ventas");
        add(billingMenuItem);
    }
    private void makeActions(){
        billingMenuItem.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                run(new JPBillingView(), "Facturación-Ventas",770,576,true);
            });
        });
    }

}
