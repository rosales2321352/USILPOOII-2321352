package views.dashboard;

import classes.db.classes.MysqlConnection;
import views.core.panel.menu.Menu;
import views.product.ProductView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dashboard extends JFrame {
    JPanel panel3;
    public Dashboard(){
        SwingUtilities.invokeLater(() -> {

            try {
                Image icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/main.png")));
                this.setIconImage(new ImageIcon(icon).getImage());
            }catch (Exception e){
                Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE,e.getMessage(),e);
            }
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(768, 576);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(screenSize);
            this.setLocation(0, 0);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

            this.setJMenuBar(new Menu(this));
            this.setVisible(true);
        });
    }

}
