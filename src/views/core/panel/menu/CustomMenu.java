package views.core.panel.menu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;

public class CustomMenu extends JMenu {

    protected JFrame main;

    public CustomMenu(String title){
        super(title);

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
