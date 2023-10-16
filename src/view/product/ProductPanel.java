package view.product;

import controller.product.ProductController;
import model.product.Product;
import view.core.TableModel;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {
    public DefaultTableModel model = null;
    public TableModel model_ = null;
    public JTable table_ = null;
    JTable table = null;
    public ProductPanel(){
        /*this.setPreferredSize(new DimensionUIResource(768,576));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);*/
        this.setLayout(new GridBagLayout());

        ProductController p = new ProductController(this);
        this.table_ = new JTable();
        p.loadDataAsync();
        this.DrawControls();
        //

    }

    public void DrawControls() {
        //model = new DefaultTableModel();
        //table = new JTable(model);
        //model.addColumn("Nombre del Producto");

        JScrollPane listScrollPane = new JScrollPane(table_);
        listScrollPane.setPreferredSize(new Dimension(500, 500));
        JButton button = new JButton("Haz Click");
        JButton button2 = new JButton("Haz Click1");
        JTextField txtBuscador = new JTextField();
        txtBuscador.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(button, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(txtBuscador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(listScrollPane, gbc);
    }



    public void update() {

    }
}
