package views.core.panel.menu.partials;

import views.core.panel.menu.CustomMenu;
import views.core.panel.menu.CustomMenuItem;

import javax.swing.*;

public class FileMenu extends CustomMenu {
    private JMenuItem exitMenuItem;
    public FileMenu(String title){
        super(title);
        makeMenuItem();
        makeActions();
    }
    public void makeMenuItem(){
        exitMenuItem = new CustomMenuItem("Salir");
        add(exitMenuItem);
    }
    public void makeActions(){
        exitMenuItem.addActionListener((e) -> {
            System.exit(0);
        });
    }
}
