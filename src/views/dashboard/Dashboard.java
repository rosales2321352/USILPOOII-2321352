package views.dashboard;

import models.db.classes.MysqlConnection;
import views.core.panel.menu.Menu;

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
                setIconImage(new ImageIcon(icon).getImage());
            }catch (Exception e){
                Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE,e.getMessage(),e);
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(768, 576);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize);
            setLocation(0, 0);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setResizable(true);
            setTitle("Sistema de Ventas");

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel3 = new JPanel();

            add(panel1,BorderLayout.PAGE_START);
            add(panel2,BorderLayout.LINE_START);
            add(panel3,BorderLayout.CENTER);
            setLocationRelativeTo(null);
            setVisible(true);

            setJMenuBar(new Menu(this));
            setVisible(true);
        });
    }

}
