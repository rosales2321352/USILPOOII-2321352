import classes.db.Db;
import model.product.Product;
import view.product.ProductPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(768, 576);
            window.setMinimumSize(new Dimension(768,576));
            window.setResizable(true);
            window.setTitle("Sistema de Ventas");

            ProductPanel panel = new ProductPanel();

            window.add(panel);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }

}
